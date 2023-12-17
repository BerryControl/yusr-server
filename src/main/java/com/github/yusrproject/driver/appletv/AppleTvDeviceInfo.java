package com.github.yusrproject.driver.appletv;

import com.github.yusrproject.driver.base.YusrDeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AppleTvDeviceInfo implements YusrDeviceInfo {
    @Getter
    private final String name;
    @Getter
    private final String deviceId;
    private final boolean requiresPairing;
    @Getter
    private final Object rawDeviceInfo;

    public boolean requiresPairing() {
        return this.requiresPairing;
    }
}
