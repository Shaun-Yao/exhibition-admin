package com.honji.exhibition.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.BusUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honji.exhibition.admin.model.AssignedUserVO;
import com.honji.exhibition.admin.model.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2019-08-07
 */
public interface BusUserMapper extends BaseMapper<BusUser> {

    @Select("SELECT IFNULL(SUM(total), 0) FROM bus_user WHERE bus_schedule_id = #{busScheduleId}")
    int seletcTotalUser(long busScheduleId);

    @Select("SELECT `schedule`.user_id as id, shop.`code` as shopCode, shop.`name` as shopName, \n" +
            "`schedule`.arrived_pick_up_station as station FROM `schedule` \n" +
            "LEFT JOIN `user` on `schedule`.user_id = `user`.id\n" +
            "LEFT JOIN shop on `user`.shop_id = shop.id\n" +
            "WHERE `schedule`.arrived_pick_up_station = #{station}\n" +
            "and `schedule`.user_id not in\n" +
            "(SELECT user_id FROM bus_user)")
    List<UserVO> selectPickUpUsers(Page<UserVO> page, @Param("station") int station);

    @Select("SELECT `user`.id as userId, bus_user.id as busUserId, bus_schedule.`name` as busName, shop.`code` as shopCode, shop.`name` as shopName,\n" +
            "`schedule`.arrived_pick_up_station as station\n" +
            "FROM bus_user\n" +
            "LEFT JOIN `user` on bus_user.user_id = `user`.id\n" +
            "LEFT JOIN shop on `user`.shop_id = shop.id\n" +
            "LEFT JOIN `schedule` ON `user`.id = `schedule`.user_id\n" +
            "LEFT JOIN bus_schedule ON bus_user.bus_schedule_id = bus_schedule.id\n" +
            "WHERE `schedule`.arrived_pick_up_station = #{station}")
    List<AssignedUserVO> selectUsers(Page<AssignedUserVO> page, @Param("station") int station);

}
