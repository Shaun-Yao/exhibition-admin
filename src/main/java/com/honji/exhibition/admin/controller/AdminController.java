package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honji.exhibition.admin.entity.Admin;
import com.honji.exhibition.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("/toLogin")
    public String toLogin() {

        return "login";
    }

    @GetMapping("/adminlte")
    public String adminlte() {

        return "adminlte";
    }


    @GetMapping("/layout")
    public String layout() {

        return "layout";
    }


    @GetMapping("/table")
    public String table() {

        return "table";
    }


    @GetMapping("/another")
    public String another() {

        return "another";
    }


    @PostMapping("/login")
    public String login(@RequestParam String account, @RequestParam String password, Model model) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        Admin admin = adminService.getOne(queryWrapper);
        if (admin != null) {
            if (password.equals(admin.getPassword())) {
                return "index";
            }
        }

        model.addAttribute("wrong", true);
        return "login";
    }
}
