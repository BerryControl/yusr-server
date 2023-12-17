package com.github.yusrproject.registry;

import com.github.yusrproject.driver.base.YusrDeviceCommand;
import com.github.yusrproject.driver.base.YusrDeviceDriver;
import com.github.yusrproject.driver.base.YusrDeviceDriverDescriptor;
import com.github.yusrproject.driver.base.YusrDeviceInfo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceRegistry {
    @Getter
    private final List<YusrDeviceDriverDescriptor<YusrDeviceDriver<YusrDeviceCommand>, YusrDeviceInfo, YusrDeviceCommand>> driverDescriptors;

    public DeviceRegistry(List<YusrDeviceDriverDescriptor<?, ?, ?>> driverDescriptors) {
        //noinspection unchecked
        this.driverDescriptors = driverDescriptors
            .stream()
            .map(e -> (YusrDeviceDriverDescriptor<YusrDeviceDriver<YusrDeviceCommand>, YusrDeviceInfo, YusrDeviceCommand>) e)
            .collect(Collectors.toList());
    }
}
