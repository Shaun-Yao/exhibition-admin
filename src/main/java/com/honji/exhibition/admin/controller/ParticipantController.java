package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Admin;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.entity.RoomParticipant;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.service.IParticipantService;
import com.honji.exhibition.admin.service.IRoomParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private IRoomParticipantService roomParticipantService;

    @Autowired
    private HttpSession session;


    @GetMapping("/get")
    public Participant get(@RequestParam Long id) {
        return participantService.getById(id);
    }

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam(defaultValue = "0") int offset, @RequestParam int limit,
                                     @RequestParam String search) {
        Admin admin = (Admin) session.getAttribute("admin");
        Page<Participant> participantPage = new Page<>(offset / limit + 1, limit);

        return new EasyUIDataGridResult(participantService.getForIndex(participantPage, admin.getType(), search));

    }

    @GetMapping("/listAvailable")
    public List<Participant> listAvailable() {
        Admin admin = (Admin) session.getAttribute("admin");
        return participantService.getAvailable(admin.getType());
    }

    @GetMapping("/listForEdit")
    public List<Participant> listForEdit(@RequestParam Long roomId) {
        Admin admin = (Admin) session.getAttribute("admin");
        List<Participant> participants = participantService.getAvailable(admin.getType());
        List<Participant> roomMembers = participantService.listByRoom(roomId);
        participants.addAll(roomMembers);
        System.out.println(participants);
        return participants;
    }

    @PostMapping("/save")
    public boolean save(@ModelAttribute Participant participant) {
        return participantService.saveOrUpdate(participant);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        //TODO 使用一条sql完成
        for (String id : ids) {
            QueryWrapper<RoomParticipant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("participant_id", id);
            RoomParticipant roomParticipant = roomParticipantService.getOne(queryWrapper);
            if (roomParticipant != null) {//已经被选入同住人不能删除
                return false;
            }
        }
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return participantService.removeByIds(resultList);

    }


}
