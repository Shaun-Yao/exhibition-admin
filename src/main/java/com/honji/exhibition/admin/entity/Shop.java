package com.honji.exhibition.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Shop extends IdEntity {


    private String code;

    private String name;

    private String area;

    private String smallArea;


    private String type;
    private String situation;
    private String mode;
}
