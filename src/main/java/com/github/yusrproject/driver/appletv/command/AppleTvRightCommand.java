package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvRightCommand extends AppleTvDeviceCommand {
    public AppleTvRightCommand() {
        super(Command.RIGHT.ordinal(), "Right");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/right.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("right", deviceId);
    }
}
