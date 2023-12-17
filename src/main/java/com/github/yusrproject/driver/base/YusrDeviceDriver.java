/*
 *    Copyright 2023 Thomas Bonk <thomas@meandmymac.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.yusrproject.driver.base;

import java.util.List;
import java.util.Optional;

public interface YusrDeviceDriver<Command extends YusrDeviceCommand> {
    /**
     * List of commands that are supported
     */
    List<Command> getCommands();

    default Optional<Command> getCommand(int id) {
        return getCommands().stream().filter(cmd -> cmd.getId() == id).findFirst();
    }

    int getRemoteLayoutHeight();

    int getRemoteLayoutWidth();

    int[][] getRemoteLayout();

    /**
     * Excutes a command.
     *
     * @param command the command that shall be executed
     */
    void execute(Command command);
}
