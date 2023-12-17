package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvHomeCommand extends AppleTvDeviceCommand {
    public AppleTvHomeCommand() {
        super(Command.HOME.ordinal(), "Home");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/home.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("home", deviceId);
    }
}
