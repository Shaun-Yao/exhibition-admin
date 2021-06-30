package com.honji.exhibition.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.model.ParticipantVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-07
 */
public interface IParticipantService extends IService<Participant> {

    IPage<ParticipantVO> getForIndex(Page<Participant> page, String shopType, String search);

    List<Participant> getAvailable(String shopType);
    List<Participant> getAvailableChildren(String shopType);
    List<Participant> getUnAvailable(String shopType);
    List<Participant> getByArea(Long userId);

    List<Participant> getLeft();

    List<Participant> getChildren();

    List<Participant> listByRoom(Long roomId);
}
