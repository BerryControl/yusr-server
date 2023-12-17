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

/**
 * Information for a device.
 */
public interface YusrDeviceInfo {
    /**
     * Name of the device.
     *
     * @return the device name
     */
    String getName();

    /**
     * Device ID.
     *
     * @return the device ID
     */
    String getDeviceId();

    /**
     * Indicates whether the device requires pairing.
     *
     * @return if the device requires pairing `true`, otherwise `false`
     */
    boolean requiresPairing();
}
