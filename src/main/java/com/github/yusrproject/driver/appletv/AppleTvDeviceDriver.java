package com.github.yusrproject.driver.appletv;

import com.github.yusrproject.driver.appletv.AppleTvDeviceCommand.Command;
import com.github.yusrproject.driver.appletv.command.AppleTvBackCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvDownCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvHomeCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvLeftCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvPlayPauseCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvRightCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvSelectCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvTurnOffCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvTurnOnCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvUpCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvVolumeDownCommand;
import com.github.yusrproject.driver.appletv.command.AppleTvVolumeUpCommand;
import com.github.yusrproject.driver.base.YusrDeviceDriver;

import java.util.List;

public class AppleTvDeviceDriver implements YusrDeviceDriver<AppleTvDeviceCommand> {
    private final String deviceId;
    private final List<AppleTvDeviceCommand> commands = List.of(
        new AppleTvTurnOnCommand(),
        new AppleTvTurnOffCommand(),
        new AppleTvBackCommand(),
        new AppleTvDownCommand(),
        new AppleTvHomeCommand(),
        new AppleTvLeftCommand(),
        new AppleTvPlayPauseCommand(),
        new AppleTvRightCommand(),
        new AppleTvSelectCommand(),
        new AppleTvUpCommand(),
        new AppleTvVolumeDownCommand(),
        new AppleTvVolumeUpCommand()
    );

    public AppleTvDeviceDriver(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public List<AppleTvDeviceCommand> getCommands() {
        return commands;
    }

    @Override
    public int getRemoteLayoutHeight() {
        return 8;
    }

    @Override
    public int getRemoteLayoutWidth() {
        return 3;
    }

    @Override
    public int[][] getRemoteLayout() {
        return new int[][] {
            {Command.ON.ordinal(), -1, Command.OFF.ordinal()},
            {-1, -1, -1},
            {-1, Command.UP.ordinal(), -1},
            {Command.LEFT.ordinal(), Command.SELECT.ordinal(), Command.RIGHT.ordinal()},
            {-1, Command.DOWN.ordinal(), -1},
            {Command.HOME.ordinal(), -1, Command.VOLUME_UP.ordinal()},
            {Command.BACK.ordinal(), -1, Command.VOLUME_DOWN.ordinal()},
            {-1, Command.PLAY_PAUSE.ordinal(), -1}
        };
    }

    @Override
    public void execute(AppleTvDeviceCommand command) {
        command.execute(this.deviceId);
    }
}
