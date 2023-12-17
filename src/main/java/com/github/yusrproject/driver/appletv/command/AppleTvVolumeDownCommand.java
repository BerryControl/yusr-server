package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvVolumeDownCommand extends AppleTvDeviceCommand {
    public AppleTvVolumeDownCommand() {
        super(Command.VOLUME_DOWN.ordinal(), "Volume Down");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/volume_down.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("volume_down", deviceId);
    }
}
