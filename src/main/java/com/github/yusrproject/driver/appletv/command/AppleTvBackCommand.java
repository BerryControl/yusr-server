package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvBackCommand extends AppleTvDeviceCommand {
    public AppleTvBackCommand() {
        super(Command.BACK.ordinal(), "Back");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/back.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("back", deviceId);
    }
}
