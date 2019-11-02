package com.honji.exhibition.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.Room;
import com.honji.exhibition.admin.model.OccupiedRoomVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
public interface IRoomService extends IService<Room> {

    void add(Room room);
    void merge(Room room);
    void delete(String[] ids);

    OccupiedRoomVO getOccupiedRoom(Long userId);
}
