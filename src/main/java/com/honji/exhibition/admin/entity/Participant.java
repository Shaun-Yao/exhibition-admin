package com.honji.exhibition.admin.entity;

import com.honji.exhibition.admin.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-07-07
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Participant extends BaseEntity {


    private Long userId;

    private String name;

    private String mobile;

    //@EnumValue
    private SexEnum sex;

    private boolean attendTraining;

    private boolean attendHotSpring;

}
