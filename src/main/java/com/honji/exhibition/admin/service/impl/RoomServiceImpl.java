package com.honji.exhibition.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.entity.Room;
import com.honji.exhibition.admin.entity.RoomParticipant;
import com.honji.exhibition.admin.mapper.RoomMapper;
import com.honji.exhibition.admin.mapper.RoomParticipantMapper;
import com.honji.exhibition.admin.model.OccupiedRoomVO;
import com.honji.exhibition.admin.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomParticipantMapper roomParticipantMapper;

    @Override
    @Transactional
    public void add(Room room) {
        //List<RoomParticipant> roomParticipants = new ArrayList<>();
        roomMapper.insert(room);

        for (Participant participant : room.getParticipants()) {
            Long participantId = participant.getId();
            if (participantId == null) {
                continue;
            }
            RoomParticipant roomParticipant = new RoomParticipant();
            roomParticipant.setRoomId(room.getId());
            roomParticipant.setParticipantId(participant.getId());
            //roomParticipants.add(roomParticipant);
            roomParticipantMapper.insert(roomParticipant);
        }
    }

    @Override
    @Transactional
    public void merge(Room room) {

        QueryWrapper<RoomParticipant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", room.getId());
        //List<RoomParticipant> roomParticipants = roomParticipantMapper.selectList(queryWrapper);
        roomParticipantMapper.delete(queryWrapper);
        roomMapper.updateById(room);

        for (Participant participant : room.getParticipants()) {
            Long participantId = participant.getId();
            if (participantId == null) {
                continue;
            }
            RoomParticipant roomParticipant = new RoomParticipant();
            roomParticipant.setRoomId(room.getId());
            roomParticipant.setParticipantId(participant.getId());
            roomParticipantMapper.insert(roomParticipant);
        }
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        for (String id : ids) {
            QueryWrapper<RoomParticipant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("room_id", id);
            roomParticipantMapper.delete(queryWrapper);
            roomMapper.deleteById(id);
        }

    }

    @Override
    public OccupiedRoomVO getOccupiedRoom(Long userId) {
        return roomMapper.selectOccupiedRoom(userId);
    }
}
