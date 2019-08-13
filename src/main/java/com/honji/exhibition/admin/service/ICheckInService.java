package com.honji.exhibition.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.CheckIn;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-08-10
 */
public interface ICheckInService extends IService<CheckIn> {

    List<UserVO> getUnsignedUsers(Page<UserVO> page, String shopCode);
    List<AssignedUserVO> getSignedUsers(Page<AssignedUserVO> page, String shopCode);
}
