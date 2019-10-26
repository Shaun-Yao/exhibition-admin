package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IParticipantService;
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
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    private IParticipantService participantService;


    @GetMapping("/get")
    public Participant get(@RequestParam Long id) {
        return participantService.getById(id);
    }

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size,
                                     @RequestParam String name) {
        IPage<Participant> participantPage = new Page<>(page, size);
        QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return new EasyUIDataGridResult(participantService.page(participantPage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Participant participant) {
        return participantService.saveOrUpdate(participant);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return participantService.removeByIds(resultList);

    }


}
