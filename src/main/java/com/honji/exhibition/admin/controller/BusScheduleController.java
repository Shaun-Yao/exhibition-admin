package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Bus;
import com.honji.exhibition.admin.entity.BusSchedule;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IBusScheduleService;
import com.honji.exhibition.admin.service.IBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-07
 */
@Controller
@RequestMapping("/bus-schedule")
public class BusScheduleController {

    @Autowired
    private IBusScheduleService busScheduleService;

    @Autowired
    private IBusService busService;

    @GetMapping("/index")
    public String index() {

        return "schedule";
    }

    @ResponseBody
    @GetMapping("/all")
    public List<BusSchedule> all() {

        return busScheduleService.list();
    }


    @ResponseBody
    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int rows) {
        IPage<BusSchedule> busPage = new Page<>(page, rows);
        busPage = busScheduleService.page(busPage);
        for (BusSchedule busSchedule : busPage.getRecords()) {
            Bus bus = busService.getById(busSchedule.getBusId());
            busSchedule.setBus(bus);
        }
        return new EasyUIDataGridResult(busPage);

    }


    @ResponseBody
    @PostMapping("/add")
    public boolean add(@ModelAttribute BusSchedule busSchedule) {

        return busScheduleService.save(busSchedule);
    }


    @ResponseBody
    @PostMapping("/remove")
    public boolean remove(@RequestParam Long id) {

        return busScheduleService.removeById(id);
    }



}
