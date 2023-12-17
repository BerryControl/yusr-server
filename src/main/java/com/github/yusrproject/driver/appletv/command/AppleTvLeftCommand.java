package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvLeftCommand extends AppleTvDeviceCommand {
    public AppleTvLeftCommand() {
        super(Command.LEFT.ordinal(), "Left");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/left.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("left", deviceId);
    }
}
