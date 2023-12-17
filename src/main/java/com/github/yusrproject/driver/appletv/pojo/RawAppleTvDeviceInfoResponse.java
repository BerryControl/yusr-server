package com.github.yusrproject.driver.appletv.pojo;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class RawAppleTvDeviceInfoResponse implements Serializable {
    String address;
    String name;
    String identifier;
}
