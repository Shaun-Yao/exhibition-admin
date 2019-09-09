package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Bus;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private IBusService busService;


/*

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int rows) {
        IPage<Bus> busPage = new Page<>(page, rows);
        return  new EasyUIDataGridResult(busService.page(busPage));

    }
*/



    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size
            ) {
        IPage<Bus> busPage = new Page<>(page, size);
        return  new EasyUIDataGridResult(busService.page(busPage));

    }



    @GetMapping("/all")
    public List<Bus> all() {
        List<Bus> buses = busService.list();
        return buses;
    }


    @PostMapping("/add")
    public boolean add(@ModelAttribute Bus bus) {

        return busService.save(bus);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam Long id) {

        return busService.removeById(id);
    }


}
