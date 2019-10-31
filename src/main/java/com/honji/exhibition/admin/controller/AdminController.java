package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honji.exhibition.admin.entity.Admin;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.entity.ScheduleTimeConfig;
import com.honji.exhibition.admin.entity.SignUpSwitch;
import com.honji.exhibition.admin.enums.SexEnum;
import com.honji.exhibition.admin.service.IAdminService;
import com.honji.exhibition.admin.service.IParticipantService;
import com.honji.exhibition.admin.service.IScheduleTimeConfigService;
import com.honji.exhibition.admin.service.ISignUpSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Autowired
    private ISignUpSwitchService signUpSwitchService;

    @Autowired
    private IScheduleTimeConfigService scheduleTimeConfigService;

    @Autowired
    private IParticipantService participantService;

    @GetMapping("/config")
    public String config(Model model) {
        List<SignUpSwitch> switches = signUpSwitchService.list();
        List<ScheduleTimeConfig> timeConfigs = scheduleTimeConfigService.list();
        model.addAttribute("switches", switches);
        model.addAttribute("timeConfigs", timeConfigs);
        return "config";
    }

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

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("/participant")
    public String participant() {
        return "participant";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
    }

    @GetMapping("/room")
    public String room(Model model) {
        List<Participant> participants = participantService.list();
        QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", SexEnum.CHILD);
        List<Participant> children = participantService.list(queryWrapper);
        model.addAttribute("participants", participants);
        model.addAttribute("children", children);
        return "room";
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
