package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Shop;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IShopService;
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
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;


    @GetMapping("/get")
    public Shop get(@RequestParam Long id) {
        return shopService.getById(id);
    }

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size,
                                     @RequestParam String code) {
        IPage<Shop> shopPage = new Page<>(page, size);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("code", code);
        return new EasyUIDataGridResult(shopService.page(shopPage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Shop shop) {
        return shopService.saveOrUpdate(shop);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return shopService.removeByIds(resultList);
    }


}
