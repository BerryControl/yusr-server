package com.github.yusrproject.driver.appletv.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FinalizePairingRequest implements Serializable {
    private String pin;
    private boolean deviceProvidesPin;
}
