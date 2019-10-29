package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Schedule;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-03
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;


    @GetMapping("/get")
    public Schedule get(@RequestParam Long id) {
        return scheduleService.getById(id);
    }

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size,
                                     @RequestParam String userId) {
        IPage<Schedule> schedulePage = new Page<>(page, size);
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_id", userId);
        return new EasyUIDataGridResult(scheduleService.page(schedulePage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Schedule schedule) {
        return scheduleService.saveOrUpdate(schedule);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return scheduleService.removeByIds(resultList);

    }


}
