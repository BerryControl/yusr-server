package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvVolumeUpCommand extends AppleTvDeviceCommand {
    public AppleTvVolumeUpCommand() {
        super(Command.VOLUME_UP.ordinal(), "Volume Up");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/volume_up.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("volume_up", deviceId);
    }
}
