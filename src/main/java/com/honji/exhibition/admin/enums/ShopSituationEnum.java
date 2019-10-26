package com.honji.exhibition.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum ShopSituationEnum {


    ZHI_YING("Z", "正常营业"),
    DAI_LI("D", "异常店铺");

    ShopSituationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final String code;
    private final String desc;


    public String getCode() {
        return code;
    }

    //@JsonValue
    public String getDesc() {
        return desc;
    }


}
