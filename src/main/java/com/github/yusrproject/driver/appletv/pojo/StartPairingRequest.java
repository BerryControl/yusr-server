package com.github.yusrproject.driver.appletv.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class StartPairingRequest implements Serializable {
    private String remoteName;
}
