package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Bus;
import com.honji.exhibition.admin.entity.BusSchedule;
import com.honji.exhibition.admin.entity.BusUser;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.model.UserVO;
import com.honji.exhibition.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @since 2019-08-07
 */
@Controller
@RequestMapping("/bus-user")
public class BusUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IBusUserService busUserService;

    @Autowired
    private IBusService busService;

    @Autowired
    private IBusScheduleService busScheduleService;

    @GetMapping("/index")
    public String index() {

        return "bus-user";
    }

    @GetMapping("/cancel")
    public String cancel() {

        return "bus-user-cancel";
    }

    @ResponseBody
    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int rows, @RequestParam int station) {
        Page<UserVO> userPage = new Page<>(page, rows);
        //IPage<User> resultPage = userService.page(userPage);
        List<UserVO> userVOS = busUserService.getPickUpUsers(userPage, station);
        userPage.setRecords(userVOS);
        for (UserVO user : userVOS) {
            Long userId = user.getId();
            QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            List<Participant> participants = participantService.list(queryWrapper);
            user.initParticipants(participants);
        }
        return  new EasyUIDataGridResult(userPage.getTotal(), userVOS);
    }

    @ResponseBody
    @GetMapping("/listAssigned")
    public EasyUIDataGridResult listAssigned(@RequestParam int page, @RequestParam int rows, @RequestParam int station) {
        Page<AssignedUserVO> userPage = new Page<>(page, rows);
        List<AssignedUserVO> userVOS = busUserService.getUsers(userPage, station);
        userPage.setRecords(userVOS);
        for (AssignedUserVO user : userVOS) {
            Long userId = user.getUserId();
            QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            List<Participant> participants = participantService.list(queryWrapper);
            user.initParticipants(participants);
        }
        return  new EasyUIDataGridResult(userPage.getTotal(), userVOS);
    }


    @ResponseBody
    @PostMapping("add")
    public boolean add(@RequestParam Long busScheduleId, @RequestParam String[] userIds, @RequestParam String[] totals) {
        List<BusUser> busUsers = new ArrayList<>();
        for(int i = 0; i < userIds.length; i++) {
            BusUser busUser = new BusUser(busScheduleId, Long.valueOf(userIds[i]),
                    Short.valueOf(totals[i]));
            busUsers.add(busUser);
        }

        return busUserService.saveBatch(busUsers);
    }

    @ResponseBody
    @PostMapping("remove")
    public boolean remove(@RequestParam String[] busUserIds) {
        List<String> resultList = new ArrayList<>(busUserIds.length);
        Collections.addAll(resultList, busUserIds);
        return busUserService.removeByIds(resultList);
    }

    @ResponseBody
    @GetMapping("/getLeftSeats")
    public int getLeftSeats(@RequestParam Long busScheduleId) {
        BusSchedule busSchedule = busScheduleService.getById(busScheduleId);
        Bus bus = busService.getById(busSchedule.getBusId());
        int loadNum = bus.getLoadNum();
        int total = busUserService.getTotalUser(busScheduleId);
        return loadNum - total;
    }
}
