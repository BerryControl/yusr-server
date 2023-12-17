package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvSelectCommand extends AppleTvDeviceCommand {
    public AppleTvSelectCommand() {
        super(Command.SELECT.ordinal(), "Select");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/select.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("select", deviceId);
    }
}
