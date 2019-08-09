package com.honji.exhibition.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.honji.exhibition.admin.entity.IdEntity;
import java.time.LocalDateTime;

import com.honji.exhibition.admin.enums.StationEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BusSchedule extends IdEntity {


    private Long busId;

    @TableField(exist = false)
    private Bus bus;

    private StationEnum station;

    private String name;

    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime endTime;


}
