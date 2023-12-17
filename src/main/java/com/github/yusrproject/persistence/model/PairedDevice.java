package com.github.yusrproject.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paired_devices")
public class PairedDevice {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "driver_id", nullable = false)
    private String driverId;
    @Column(name = "device_id", nullable = false)
    private String deviceId;
    @Column(name = "device_name", nullable = false)
    private String deviceName;
}
