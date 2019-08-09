package com.honji.exhibition.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.BusUser;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-08-07
 */
public interface IBusUserService extends IService<BusUser> {
    int getTotalUser(long busScheduleId);
    List<UserVO> getPickUpUsers(Page<UserVO> page, int station);
    List<AssignedUserVO> getUsers(Page<AssignedUserVO> page, int station);
}
