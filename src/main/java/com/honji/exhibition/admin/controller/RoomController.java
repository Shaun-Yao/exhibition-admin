package com.honji.exhibition.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.entity.Room;
import com.honji.exhibition.admin.entity.RoomParticipant;
import com.honji.exhibition.admin.model.EasyUIDataGridResult;
import com.honji.exhibition.admin.model.RoomVO;
import com.honji.exhibition.admin.service.IParticipantService;
import com.honji.exhibition.admin.service.IRoomParticipantService;
import com.honji.exhibition.admin.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomParticipantService roomParticipantService;

    @Autowired
    private IParticipantService participantService;

    @GetMapping("/get")
    public RoomVO get(@RequestParam Long id) {
        Room room = roomService.getById(id);
        RoomVO roomVO = new RoomVO(room.getId(), room.getUserId(), room.getType());
        QueryWrapper<RoomParticipant> wrapper = new QueryWrapper<>();
        wrapper.eq("room_id", room.getId());
        List<RoomParticipant> roomParticipants = roomParticipantService.list(wrapper);
        List<Long> ids = new ArrayList<>();
        for (RoomParticipant roomParticipant : roomParticipants) {
            ids.add(roomParticipant.getParticipantId());
        }
        List<Participant> participants = new ArrayList(participantService.listByIds(ids));
        roomVO.parse(participants);
        return roomVO;
    }

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size,
                                     @RequestParam String userId) {
        IPage<Room> roomPage = new Page<>(page, size);
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_id", userId);
        queryWrapper.orderByAsc("user_id");
        roomPage = roomService.page(roomPage, queryWrapper);
        Page<RoomVO> roomVOPage = new Page<>(page, size, roomPage.getTotal());
        List<Room> rooms = roomPage.getRecords();
        List<RoomVO> roomVOS = new ArrayList<>(rooms.size());
        roomVOPage.setRecords(roomVOS);
        for (Room room : rooms) {
            RoomVO roomVO = new RoomVO(room.getId(), room.getUserId(), room.getType());
            roomVOS.add(roomVO);
            QueryWrapper<RoomParticipant> wrapper = new QueryWrapper<>();
            wrapper.eq("room_id", room.getId());
            List<RoomParticipant> roomParticipants = roomParticipantService.list(wrapper);
            List<Long> ids = new ArrayList<>();
            for (RoomParticipant roomParticipant : roomParticipants) {
                ids.add(roomParticipant.getParticipantId());
            }
            List<Participant> participants = new ArrayList(participantService.listByIds(ids));
            roomVO.parse(participants);
        }
        return  new EasyUIDataGridResult(roomVOPage);

    }


    @PostMapping("/save")
    public void save(@ModelAttribute Room room) {
        if (room.getId() == null) {
            roomService.add(room);
        } else {
            roomService.merge(room);
        }
    }

    @PostMapping("/remove")
    public void remove(@RequestParam String[] ids) {
        roomService.delete(ids);
    }


}
