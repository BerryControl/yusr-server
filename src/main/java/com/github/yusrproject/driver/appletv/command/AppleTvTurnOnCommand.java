package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;
import org.springframework.http.ResponseEntity;

public class AppleTvTurnOnCommand extends AppleTvDeviceCommand {

    public AppleTvTurnOnCommand() {
        super(Command.ON.ordinal(), "Turn On");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/turn_on.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("turn_on", deviceId);
    }
}
