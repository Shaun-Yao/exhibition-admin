package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Admin;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.entity.User;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.model.UserVO;
import com.honji.exhibition.admin.service.IParticipantService;
import com.honji.exhibition.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  UserController前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-10-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get")
    public User get(@RequestParam Long id) {
        return userService.getById(id);
    }


    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size,@RequestParam String shopCode) {

        Admin admin = (Admin) session.getAttribute("admin");
        Page<UserVO> userPage = new Page<>(page, size);
        List<UserVO> userVOS = userService.getUsers(userPage, admin.getType(), shopCode);
        userPage.setRecords(userVOS);
        for (UserVO user : userVOS) {
            Long userId = user.getId();
            QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            List<Participant> participants = participantService.list(queryWrapper);
            user.initParticipants(participants);
        }
        return  new EasyUIDataGridResult(userPage);

    }

    @PostMapping("/remove")
    public void remove(@RequestParam Long id) {
        userService.delete(id);
    }
}
