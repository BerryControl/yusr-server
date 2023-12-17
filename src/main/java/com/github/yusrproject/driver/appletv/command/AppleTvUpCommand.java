package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvUpCommand extends AppleTvDeviceCommand {
    public AppleTvUpCommand() {
        super(Command.UP.ordinal(), "Up");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/up.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("up", deviceId);
    }
}
