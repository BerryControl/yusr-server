package com.github.yusrproject.driver.appletv.command;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand;
import com.github.yusrproject.driver.appletv.AppleTvDeviceInfo;

public class AppleTvPlayPauseCommand extends AppleTvDeviceCommand {
    public AppleTvPlayPauseCommand() {
        super(Command.PLAY_PAUSE.ordinal(), "Play/Pause");
    }

    @Override
    public String getIconResourcePath() {
        return "images/drivers/appletv/play_pause.png";
    }

    @Override
    public void execute(String deviceId) {
        execute("play_pause", deviceId);
    }
}
