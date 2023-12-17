package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvTurnOffCommand extends AppleTvDeviceCommand {
    public AppleTvTurnOffCommand() {
        super(Command.OFF.ordinal(), "Turn Off");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/turn_off.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("turn_off", deviceId);
    }
}
