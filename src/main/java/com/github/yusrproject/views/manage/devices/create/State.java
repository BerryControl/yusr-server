package com.github.yusrproject.views.manage.devices.create;

import com.github.yusrproject.driver.base.YusrDeviceCommand;
import com.github.yusrproject.driver.base.YusrDeviceDriver;
import com.github.yusrproject.driver.base.YusrDeviceDriverDescriptor;
import com.github.yusrproject.driver.base.YusrDeviceInfo;
import com.github.yusrproject.driver.base.exception.YusrDeviceDriverException;
import com.github.yusrproject.persistence.model.PairedDevice;
import com.github.yusrproject.persistence.repository.PairedDevicesRepository;
import com.github.yusrproject.views.manage.devices.ManageDevices;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.github.yusrproject.views.NotificationDisplay.showError;

public class State {

    public enum Step {
        SELECT_DRIVER(1),
        SELECT_DEVICE(2),
        PAIR_DEVICE(3),
        PAIRING_STARTED(4),
        ADD_DEVICE(5);

        private final int value;

        Step(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    private final CreateDeviceView view;
    private final PairedDevicesRepository pairedDevicesRepository;

    @Getter
    private Step step = Step.SELECT_DRIVER;

    @Getter
    private YusrDeviceDriverDescriptor<
        YusrDeviceDriver<YusrDeviceCommand>, YusrDeviceInfo, YusrDeviceCommand
        > selectedDriver = null;

    private List<YusrDeviceInfo> devices = null;
    @Getter
    private YusrDeviceInfo selectedDevice = null;
    private String pairingRequest;

    State(CreateDeviceView view, PairedDevicesRepository pairedDevicesRepository) {
        this.view = view;
        this.pairedDevicesRepository = pairedDevicesRepository;
    }

    public void setSelectedDriver(YusrDeviceDriverDescriptor<YusrDeviceDriver<YusrDeviceCommand>, YusrDeviceInfo, YusrDeviceCommand> selectedDriver) {
        if (!Objects.equals(this.getSelectedDriver(), selectedDriver) || selectedDevice == null) {
            this.devices = null;
            this.setSelectedDevice(null);
        }
        this.selectedDriver = selectedDriver;
        this.step = this.getSelectedDriver() == null ? Step.SELECT_DRIVER : Step.SELECT_DEVICE;
        updateView();
    }

    public List<YusrDeviceInfo> getDevices() throws YusrDeviceDriverException {
        if (this.getSelectedDriver() != null) {
            if (devices == null) {
                //noinspection unchecked
                this.devices = (List<YusrDeviceInfo>) this.getSelectedDriver().getDevices();
            }

            return this.devices;
        }

        return List.of();
    }

    public void setSelectedDevice(YusrDeviceInfo selectedDevice) {
        this.selectedDevice = selectedDevice;
        this.step = this.needsAuthentication() ? Step.PAIR_DEVICE : Step.ADD_DEVICE;
        updateView();
    }

    public boolean needsAuthentication() {
        return this.getSelectedDriver() != null && this.getSelectedDriver().needsAuthentication();
    }

    public void startPairing() {
        try {
            this.pairingRequest = this.selectedDriver.startPairing(this.selectedDevice, "Y.U.S.R");
            this.step = Step.PAIRING_STARTED;
            this.updateView();
        } catch (YusrDeviceDriverException e) {
            showError("Failed to start pairing process.");
        }
    }

    public void finalizePairing(String pin) {
        try {
            this.selectedDriver.finalizePairing(this.pairingRequest, pin);
            this.step = Step.ADD_DEVICE;
            this.updateView();
            this.pairedDevicesRepository
                .save(
                    PairedDevice
                        .builder()
                        .id(UUID.randomUUID())
                        .driverId(this.selectedDriver.getPluginId().toString())
                        .deviceId(this.selectedDevice.getDeviceId())
                        .deviceName(this.selectedDevice.getName())
                        .build());
            view.getUI().ifPresent(ui -> ui.navigate(ManageDevices.ManageDevicesView.ROUTE));
        } catch (YusrDeviceDriverException e) {
            showError("Failed to finalize pairing process.");
        }
    }

    private void updateView() {
        view.renderView();
    }
}
