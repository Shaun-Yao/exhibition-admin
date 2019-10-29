package com.honji.exhibition.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TravelModeEnum {
    PLANE(1, "飞机"),
    HIGH_SPEED_RAIL(2, "高铁"),
    TRAIN(3, "火车"),
    SELF_DRIVING(4, "自驾"),
    BUS(5, "大巴");


    @EnumValue
    private final int code;
    private final String desc;

    TravelModeEnum(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }

}
