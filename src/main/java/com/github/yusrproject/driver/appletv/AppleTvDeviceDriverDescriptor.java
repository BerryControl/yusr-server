package com.github.yusrproject.driver.appletv;

import com.github.yusrproject.driver.appletv.pojo.*;
import com.github.yusrproject.driver.base.YusrDeviceDriverDescriptor;
import com.github.yusrproject.driver.base.exception.YusrDeviceDriverException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AppleTvDeviceDriverDescriptor extends YusrDeviceDriverDescriptor
    <
        AppleTvDeviceDriver,
        AppleTvDeviceInfo,
        AppleTvDeviceCommand
        > {

    private final RestTemplate restTemplate;

    public AppleTvDeviceDriverDescriptor() {
        super(
            UUID.fromString("2f5d7022-a442-4b5c-abae-94ac57ff3998"),
            "AppleTV",
            "Control Apple TV devices");

        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<AppleTvDeviceInfo> getDevices() throws YusrDeviceDriverException {
        try {
            ResponseEntity<RawAppleTvDeviceInfoResponse[]> response = restTemplate
                .getForEntity("http://localhost:8080/scan", RawAppleTvDeviceInfoResponse[].class);

            return Arrays
                .stream(
                    Optional
                        .ofNullable(response.getBody())
                        .orElseGet(() -> new RawAppleTvDeviceInfoResponse[0]))
                .map(d -> new AppleTvDeviceInfo(d.getName(), d.getIdentifier(), true, d))
                .collect(Collectors.toList());
        } catch(Exception e) {
            throw new YusrDeviceDriverException("Error while loading devices.", e);
        }
    }

    @Override
    public AuthenticationMethod authenticationMethod() {
        return AuthenticationMethod.PIN;
    }

    @Override
    public String startPairing(AppleTvDeviceInfo deviceInfo, String remoteName) throws YusrDeviceDriverException {
        try {
            StartPairingRequest request = new StartPairingRequest(remoteName);
            String url = String.format("http://localhost:8080/start_pairing/%s", deviceInfo.getDeviceId());

            ResponseEntity<StartPairingResponse> response = restTemplate
                .postForEntity(url, request, StartPairingResponse.class);

            return response.getBody().getPairingRequest();
        } catch(Exception e) {
            throw new YusrDeviceDriverException("Error while starting paring process.", e);
        }
    }

    @Override
    public boolean finalizePairing(String pairingRequest, String pin) throws YusrDeviceDriverException {
        try {
            FinalizePairingRequest request = new FinalizePairingRequest(pin, true);
            String url = String.format("http://localhost:8080/finalize_pairing/%s", pairingRequest);

            ResponseEntity<FinalizePairingResponse> response = restTemplate
                .postForEntity(url, request, FinalizePairingResponse.class);

            return response.getBody().isDeviceHasPaired();
        } catch(Exception e) {
            throw new YusrDeviceDriverException("Error while starting paring process.", e);
        }
    }

    @Override
    public AppleTvDeviceDriver createDriverInstance(String deviceId) {
        return new AppleTvDeviceDriver(deviceId);
    }
}
