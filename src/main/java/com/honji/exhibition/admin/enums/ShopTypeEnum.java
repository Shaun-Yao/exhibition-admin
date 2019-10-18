package com.honji.exhibition.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ShopTypeEnum {


    DIRECT(1, "直营"),
    PROXY_FRANCHISE(2, "代理加盟"),
    JOINT(3, "联营");

    ShopTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final int code;
    private final String desc;

    public int getCode() {
        return code;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }


}
