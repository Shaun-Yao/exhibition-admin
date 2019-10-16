package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Shop;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size
            ) {
        IPage<Shop> shopPage = new Page<>(page, size);
        return new EasyUIDataGridResult(shopService.page(shopPage));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Shop shop) {
        return shopService.saveOrUpdate(shop);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam Long id) {

        return shopService.removeById(id);
    }


}
