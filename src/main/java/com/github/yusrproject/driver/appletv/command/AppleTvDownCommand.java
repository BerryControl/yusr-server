package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvDownCommand extends AppleTvDeviceCommand {
    public AppleTvDownCommand() {
        super(Command.DOWN.ordinal(), "Down");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/down.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("down", deviceId);
    }
}
