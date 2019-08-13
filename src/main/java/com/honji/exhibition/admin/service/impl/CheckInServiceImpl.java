package com.honji.exhibition.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.CheckIn;
import com.honji.exhibition.admin.mapper.CheckInMapper;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.UserVO;
import com.honji.exhibition.admin.service.ICheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-08-10
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements ICheckInService {

    @Autowired
    private CheckInMapper checkInMapper;

    @Override
    public List<UserVO> getUnsignedUsers(Page<UserVO> page, String shopCode) {
        return checkInMapper.selectUnsignedUsers(page, shopCode);
    }

    @Override
    public List<AssignedUserVO> getSignedUsers(Page<AssignedUserVO> page, String shopCode) {
        return checkInMapper.selectSignedUsers(page, shopCode);
    }
}
