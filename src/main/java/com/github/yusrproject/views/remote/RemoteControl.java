package com.github.yusrproject.views.remote;

import com.github.yusrproject.driver.base.YusrDeviceCommand;
import com.github.yusrproject.driver.base.YusrDeviceDriver;
import com.github.yusrproject.driver.base.YusrDeviceDriverDescriptor;
import com.github.yusrproject.driver.base.YusrDeviceInfo;
import com.github.yusrproject.persistence.model.PairedDevice;
import com.github.yusrproject.persistence.repository.PairedDevicesRepository;
import com.github.yusrproject.registry.DeviceRegistry;
import com.github.yusrproject.views.MainLayout;
import com.vaadin.componentfactory.gridlayout.GridLayout;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.yusrproject.views.NotificationDisplay.showError;

public class RemoteControl extends SideNavItem {
    public static final String TITLE = "Remote Control";

    public RemoteControl() {
        super(TITLE, RemoteControlView.class, LineAwesomeIcon.WIFI_SOLID.create());
    }

    @PageTitle(TITLE)
    @Route(value = RemoteControlView.ROUTE, layout = MainLayout.class)
    @RouteAlias(value = "", layout = MainLayout.class)
    public static class RemoteControlView extends VerticalLayout {
        public static final String ROUTE = "remote_control";

        private final DeviceRegistry registry;
        private final PairedDevicesRepository devicesRepository;

        private PairedDevice selectedDevice = null;
        private YusrDeviceDriver<YusrDeviceCommand> deviceDriverInstance = null;

        public RemoteControlView(DeviceRegistry registry, PairedDevicesRepository devicesRepository) {
            this.registry = registry;
            this.devicesRepository = devicesRepository;

            renderView();
        }

        private void renderView() {
            this.removeAll();

            if (devicesRepository.count() == 0) {
                add(new Text("There are no paired devices."));
            } else {
                renderDeviceSelection();
            }
        }

        private void renderDeviceSelection() {
            List<PairedDevice> devices = devicesRepository.findAll();
            Select<PairedDevice> devicesSelector = new Select<>();
            devicesSelector.setItems(devices);
            devicesSelector.setItemLabelGenerator(this::labelForDevice);
            if (selectedDevice == null && !devices.isEmpty()) {
                this.selectedDevice = devices.getFirst();
            }
            devicesSelector.setValue(selectedDevice);
            devicesSelector.addValueChangeListener(this::deviceSelected);
            devicesSelector.setWidth(50, Unit.PERCENTAGE);

            this.add(devicesSelector);

            Component remoteControlView = renderRemoteControlView();
            this.add(remoteControlView);
            this.setHorizontalComponentAlignment(Alignment.CENTER, devicesSelector, remoteControlView);
        }

        private Component renderRemoteControlView() {
            if (this.selectedDevice != null) {
                Optional<
                    YusrDeviceDriverDescriptor<
                        YusrDeviceDriver<YusrDeviceCommand>,
                        YusrDeviceInfo,
                        YusrDeviceCommand>
                    > driverDescriptor = registry
                    .getDriverDescriptors()
                    .stream()
                    .filter(d -> d.getPluginId().toString().equals(this.selectedDevice.getDriverId()))
                    .findFirst();

                if (driverDescriptor.isPresent()) {
                    YusrDeviceDriver<YusrDeviceCommand> device = this.getOrCreateDeviceInstance(driverDescriptor.get());
                    GridLayout grid = new GridLayout(device.getRemoteLayoutWidth(), device.getRemoteLayoutHeight());
                    int[][] remoteLayout = device.getRemoteLayout();

                    for (int[] row : remoteLayout) {
                        for (int commandId : row) {
                            if (commandId == -1) {
                                grid.addComponent(new Text(""));
                            } else {
                                grid.addComponent(buttonForCommandId(device, commandId));
                            }
                        }
                    }

                    grid.setMargin(false);
                    grid.setWidth(150, Unit.PIXELS);
                    grid.setHeight(300, Unit.PIXELS);
                    return grid;
                }
            }
            return new GridLayout(1, 1);
        }

        private YusrDeviceDriver<YusrDeviceCommand> getOrCreateDeviceInstance(YusrDeviceDriverDescriptor<YusrDeviceDriver<YusrDeviceCommand>, YusrDeviceInfo, YusrDeviceCommand> driverDescriptor) {
            if (this.deviceDriverInstance == null && this.selectedDevice != null) {
                this.deviceDriverInstance = driverDescriptor.createDriverInstance(this.selectedDevice.getDeviceId());
            }

            return this.deviceDriverInstance;
        }

        private Component buttonForCommandId(YusrDeviceDriver<YusrDeviceCommand> device, int commandId) {
            YusrDeviceCommand command = device.getCommand(commandId).get();
            Button button = new Button(convertToImage(command), ev -> {
                try {
                    device.execute(command);
                } catch (Exception e) {
                    showError(String.format("Failed to execute command '%s'", command.getTitle()));
                }
            });
            button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

            return button;
        }

        private Image convertToImage(YusrDeviceCommand command) {
            String name = UUID.randomUUID().toString(); //command.getTitle().replace(" ", "");
            StreamResource streamResource = new StreamResource(name, (InputStreamFactory) () -> new ByteArrayInputStream(command.getIcon()));

            return new Image(streamResource, UUID.randomUUID().toString());
        }

        private void deviceSelected(AbstractField.ComponentValueChangeEvent<Select<PairedDevice>, PairedDevice> valueChangeEvent) {
            this.selectedDevice = valueChangeEvent.getValue();
            this.deviceDriverInstance = null;

            renderView();
        }

        private String labelForDevice(PairedDevice pairedDevice) {
            String driverName = registry
                .getDriverDescriptors()
                .stream()
                .filter(d -> d.getPluginId().toString().equals(pairedDevice.getDriverId()))
                .findFirst()
                .get()
                .getDisplayName();

            return String.format("%s (%s)", pairedDevice.getDeviceName(), driverName);
        }
    }
}
