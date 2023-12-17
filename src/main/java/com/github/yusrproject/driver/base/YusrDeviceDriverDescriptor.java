package com.github.yusrproject.driver.base;

import com.github.yusrproject.driver.base.exception.YusrDeviceDriverException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

/**
 * This class provides the descriptor for a device driver.
 */
@Getter
@AllArgsConstructor
public abstract class YusrDeviceDriverDescriptor
    <
        DeviceDriver extends YusrDeviceDriver<DeviceCommand>,
        DeviceInfo extends YusrDeviceInfo,
        DeviceCommand extends YusrDeviceCommand
        > {

    public enum AuthenticationMethod {
        NONE,
        PIN,
        PASSWORD,
        USER_AND_PASSWORD
    }

    /**
     * The ID of the plugin. This ID must be constant!
     */
    private final UUID pluginId;

    /**
     * Display name of the plugin.
     */
    private final String displayName;

    /**
     * Description of the plugin
     */
    private final String description;

    /**
     * All devices that are supported or are available.
     *
     * @return the devices
     */
    public abstract List<DeviceInfo> getDevices() throws YusrDeviceDriverException;

    public abstract AuthenticationMethod authenticationMethod();

    public boolean needsAuthentication() {
        return !this.authenticationMethod().equals(AuthenticationMethod.NONE);
    }

    /**
     * Start pairing with the given device.
     *
     * @param deviceInfo the device info of the device to pair with
     * @param remoteName the name of the remote
     * @return pairing request ID
     */
    public abstract String startPairing(DeviceInfo deviceInfo, String remoteName) throws YusrDeviceDriverException;

    /**
     * Finalize the pairing process.
     *
     * @param pairingRequest the pairing request id
     * @param pin            the pin for pairing with the device
     */
    public abstract boolean finalizePairing(String pairingRequest, String pin) throws YusrDeviceDriverException;

    /**
     * Create a device driver instance for the device with the given ID.
     *
     * @param deviceId the device id for the device instance to be created
     * @return the instance of the device
     */
    public abstract DeviceDriver createDriverInstance(String deviceId);
}
