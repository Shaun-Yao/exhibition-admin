package com.honji.exhibition.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.entity.User;
import com.honji.exhibition.admin.mapper.ParticipantMapper;
import com.honji.exhibition.admin.mapper.UserMapper;
import com.honji.exhibition.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ParticipantMapper participantMapper;

    public void insert(User user) {
        userMapper.insert(user);
        List<Participant> participants = user.getParticipants();
        for (Participant participant : participants) {

            participant.setUserId(user.getId());
            participantMapper.insert(participant);
        }
    }
}
