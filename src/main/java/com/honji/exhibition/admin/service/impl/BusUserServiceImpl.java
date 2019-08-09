package com.honji.exhibition.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.BusUser;
import com.honji.exhibition.admin.mapper.BusUserMapper;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.UserVO;
import com.honji.exhibition.admin.service.IBusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-08-07
 */
@Service
public class BusUserServiceImpl extends ServiceImpl<BusUserMapper, BusUser> implements IBusUserService {

    @Autowired
    private BusUserMapper busUserMapper;

    @Override
    public int getTotalUser(long busScheduleId) {
        return busUserMapper.seletcTotalUser(busScheduleId);
    }

    @Override
    public List<UserVO> getPickUpUsers(Page<UserVO> page, int station) {
        return busUserMapper.selectPickUpUsers(page, station);
    }

    @Override
    public List<AssignedUserVO> getUsers(Page<AssignedUserVO> page, int station) {
        return busUserMapper.selectUsers(page, station);
    }
}
