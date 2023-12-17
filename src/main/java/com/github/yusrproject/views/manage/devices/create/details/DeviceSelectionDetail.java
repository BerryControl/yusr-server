package com.github.yusrproject.views.manage.devices.create.details;

import com.github.yusrproject.driver.base.YusrDeviceInfo;
import com.github.yusrproject.driver.base.exception.YusrDeviceDriverException;
import com.github.yusrproject.registry.DeviceRegistry;
import com.github.yusrproject.views.manage.devices.create.State;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

import static com.github.yusrproject.views.NotificationDisplay.showError;

public class DeviceSelectionDetail extends Details {
    private final State state;
    private final DeviceRegistry deviceRegistry;

    public DeviceSelectionDetail(State state, DeviceRegistry deviceRegistry) {
        super("Select Device");
        this.state = state;
        this.deviceRegistry = deviceRegistry;
    }

    public DeviceSelectionDetail render() {
        this.removeAll();
        boolean enabled = state.getStep().value() >= State.Step.SELECT_DEVICE.value();

        ComboBox<YusrDeviceInfo> devicesComboBox = new ComboBox<>("Devices");
        List<YusrDeviceInfo> devices;

        try {
            devices = this.state.getDevices();
        } catch (YusrDeviceDriverException e) {
            showError("Failed to retrieve devices");
            devices = List.of();
        }

        if (this.state.getSelectedDriver() != null) {
            devicesComboBox.setItems(devices);
        }
        devicesComboBox.setItemLabelGenerator(YusrDeviceInfo::getName);
        devicesComboBox.setValue(state.getSelectedDevice());
        devicesComboBox.addValueChangeListener(e -> state.setSelectedDevice(e.getValue()));

        VerticalLayout content = new VerticalLayout(devicesComboBox);
        content.setSpacing(false);
        content.setPadding(false);

        this.add(content);
        this.setOpened(enabled);
        this.setEnabled(enabled);

        return this;
    }
}
