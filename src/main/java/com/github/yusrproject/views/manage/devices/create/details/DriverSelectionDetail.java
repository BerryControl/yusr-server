package com.github.yusrproject.views.manage.devices.create.details;

import com.github.yusrproject.driver.base.YusrDeviceCommand;
import com.github.yusrproject.driver.base.YusrDeviceDriver;
import com.github.yusrproject.driver.base.YusrDeviceDriverDescriptor;
import com.github.yusrproject.driver.base.YusrDeviceInfo;
import com.github.yusrproject.registry.DeviceRegistry;
import com.github.yusrproject.views.manage.devices.create.State;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DriverSelectionDetail extends Details {
    private final State state;
    private final DeviceRegistry deviceRegistry;

    public DriverSelectionDetail(State state, DeviceRegistry deviceRegistry) {
        super("Select Driver");
        this.state = state;
        this.deviceRegistry = deviceRegistry;
    }

    public DriverSelectionDetail render() {
        this.removeAll();

        boolean enabled = state.getStep().value() >= State.Step.SELECT_DRIVER.value();

        ComboBox<YusrDeviceDriverDescriptor<YusrDeviceDriver<YusrDeviceCommand>, YusrDeviceInfo, YusrDeviceCommand>> driversComboBox = new ComboBox<>("Driver");
        driversComboBox.setItems(deviceRegistry.getDriverDescriptors());
        driversComboBox.setAllowCustomValue(false);
        driversComboBox.setItemLabelGenerator(YusrDeviceDriverDescriptor::getDisplayName);
        driversComboBox.setValue(state.getSelectedDriver());
        driversComboBox.addValueChangeListener(e -> state.setSelectedDriver(e.getValue()));

        VerticalLayout content = new VerticalLayout(driversComboBox);
        content.setSpacing(false);
        content.setPadding(false);

        this.add(content);
        this.setOpened(enabled);
        this.setEnabled(enabled);

        return this;
    }
}
