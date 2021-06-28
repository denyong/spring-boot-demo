package com.duqi.constant;

import lombok.Getter;

@Getter
public enum Status {
    INSTALLING("installing", "安装中"),
    INSTALL_FAIL("install_fail", "安装失败"),
    RUNNING("running", "运行中"),
    UNINSTALLING("uninstalling", "卸载中"),
    UNINSTALL_FAIL("uninstall_fail", "卸载失败"),
    UNINSTALLED("uninstalled", "已卸载");

    private String key;
    private String value;

    Status(String key, String value) {
        this.key = key;
        this.value = value;
    }
}