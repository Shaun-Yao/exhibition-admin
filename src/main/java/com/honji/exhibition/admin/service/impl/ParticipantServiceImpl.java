package com.honji.exhibition.admin.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.mapper.ParticipantMapper;
import com.honji.exhibition.admin.model.ParticipantVO;
import com.honji.exhibition.admin.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-07
 */
@Service
public class ParticipantServiceImpl extends ServiceImpl<ParticipantMapper, Participant> implements IParticipantService {

    @Autowired
    private ParticipantMapper participantMapper;

    @Override
    public IPage<ParticipantVO> getForIndex(Page<Participant> page, String shopType, String search) {
        return participantMapper.selectForIndex(page, shopType, search);
    }

    @Override
    public List<Participant> getAvailable(String shopType) {
        return participantMapper.selectAvailable(shopType);
    }

    @Override
    public List<Participant> getAvailableChildren(String shopType) {
        return participantMapper.selectAvailableChildren(shopType);
    }

    @Override
    public List<Participant> getUnAvailable(String shopType) {
        return participantMapper.selectUnAvailable(shopType);
    }

    @Override
    public List<Participant> getByArea(Long userId) {
        return participantMapper.selectByArea(userId);
    }

    @Override
    public List<Participant> getLeft() {
        return participantMapper.selectLeft();
    }

    @Override
    public List<Participant> getChildren() {
        return participantMapper.selectChildren();
    }

    @Override
    public List<Participant> listByRoom(Long roomId) {
        return participantMapper.selectByRoom(roomId);
    }


}
