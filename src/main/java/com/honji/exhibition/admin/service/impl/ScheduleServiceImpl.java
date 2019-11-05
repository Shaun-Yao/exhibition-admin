package com.honji.exhibition.admin.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.Schedule;
import com.honji.exhibition.admin.mapper.ScheduleMapper;
import com.honji.exhibition.admin.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-20
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public IPage<Schedule> getForIndex(IPage<Schedule> page, String shopType, String userId) {
        return scheduleMapper.selectForIndex(page, shopType, userId);
    }
}
