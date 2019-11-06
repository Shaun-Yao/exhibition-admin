package com.honji.exhibition.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.Room;
import com.honji.exhibition.admin.model.OccupiedRoomVO;
import com.honji.exhibition.admin.model.RoomVO;

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

    IPage<RoomVO> getForIndex(IPage<RoomVO> page, String shopType, String userId);

}
