package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.CheckIn;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.model.UserVO;
import com.honji.exhibition.admin.service.ICheckInService;
import com.honji.exhibition.admin.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-10
 */
@Controller
@RequestMapping("/check-in")
public class CheckInController {

    @Autowired
    private ICheckInService checkInService;


    @Autowired
    private IParticipantService participantService;


    @GetMapping("/index")
    public String index() {

        return "check-in";
    }

    @GetMapping("/cancel")
    public String cancel() {

        return "check-in-cancel";
    }


    @ResponseBody
    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int rows,
                                     @RequestParam(required = false) String shopCode ) {

        if (shopCode == null) {
            shopCode = "";
        }
        Page<UserVO> userPage = new Page<>(page, rows);
        List<UserVO> userVOS = checkInService.getUnsignedUsers(userPage, shopCode);
        userPage.setRecords(userVOS);
        /*for (UserVO user : userVOS) {
            Long userId = user.getId();
            QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            List<Participant> participants = participantService.list(queryWrapper);
            user.initParticipants(participants);
        }*/
        return  new EasyUIDataGridResult(userPage.getTotal(), userVOS);
    }

    @ResponseBody
    @GetMapping("/listSigned")
    public EasyUIDataGridResult listSigned(@RequestParam int page, @RequestParam int rows,
                                     @RequestParam(required = false) String shopCode ) {

        if (shopCode == null) {
            shopCode = "";
        }
        Page<AssignedUserVO> userPage = new Page<>(page, rows);
        List<AssignedUserVO> userVOS = checkInService.getSignedUsers(userPage, shopCode);
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
    @PostMapping("/sign")
    public boolean sign(@RequestParam String[] ids) {
        List<CheckIn> checkIns = new ArrayList<>(ids.length);
        for (String id : ids) {
            CheckIn checkIn = new CheckIn();
            checkIn.setUserId(Long.valueOf(id));
            checkIns.add(checkIn);
        }
        return checkInService.saveBatch(checkIns);
    }


    @ResponseBody
    @PostMapping("/remove")
    public boolean remove(@RequestParam Long[] ids) {
        /*List<CheckIn> checkIns = new ArrayList<>(ids.length);
        for (String id : ids) {
            CheckIn checkIn = new CheckIn();
            checkIn.setUserId(Long.valueOf(id));
            checkIns.add(checkIn);
        }*/

        List<Long> idList= new ArrayList<>(Arrays.asList(ids));
        return checkInService.removeByIds(idList);
    }


}
