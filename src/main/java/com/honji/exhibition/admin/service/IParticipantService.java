package com.honji.exhibition.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.Participant;

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

    List<Participant> getByArea(Long userId);

    List<Participant> getLeft();

    List<Participant> getChildren();

    List<Participant> listByRoom(Long roomId);
}
