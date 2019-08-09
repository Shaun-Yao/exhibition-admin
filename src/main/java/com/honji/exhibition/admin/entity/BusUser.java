package com.honji.exhibition.admin.entity;

import com.honji.exhibition.admin.entity.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class BusUser extends IdEntity {


    private Long busScheduleId;

    private Long userId;

    private short total;


    public BusUser(Long busScheduleId, Long userId, short total) {
        this.busScheduleId = busScheduleId;
        this.userId = userId;
        this.total = total;
    }
}
