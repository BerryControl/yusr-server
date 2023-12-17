package com.github.yusrproject.persistence.repository;

import com.github.yusrproject.persistence.model.PairedDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PairedDevicesRepository extends JpaRepository<PairedDevice, Long> {
    Optional<PairedDevice> findByDriverIdAndDeviceId(String driverId, String deviceId);
}
