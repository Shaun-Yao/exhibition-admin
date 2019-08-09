package com.honji.exhibition.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honji.exhibition.admin.entity.Participant;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2019-07-07
 */
public interface ParticipantMapper extends BaseMapper<Participant> {

    @Select("SELECT *  FROM participant WHERE user_id IN (SELECT id FROM `user` " +
            " WHERE shop_id IN ( SELECT id FROM shop WHERE area IN ( SELECT area FROM shop WHERE id = ( SELECT shop_id FROM `user` WHERE id = #{userId} ) ) ))" +
            " AND id not in (SELECT participant_id FROM room_participant)")
    List<Participant> selectByArea(Long userId);

    @Select("SELECT *  FROM participant WHERE user_id IN (SELECT id FROM `user` " +
            " WHERE shop_id IN ( SELECT id FROM shop WHERE area IN ( SELECT area FROM shop WHERE id = ( SELECT shop_id FROM `user` WHERE id = #{userId} ) ) ))" +
            " AND sex = 3" +
            " AND id not in (SELECT participant_id FROM room_participant)")
    List<Participant> selectChildren(Long userId);

    @Select("SELECT * FROM participant WHERE id in " +
            "(SELECT participant_id FROM room_participant WHERE room_id = #{roomId} ORDER BY id)")
    List<Participant> selectByRoom(Long roomId);
}
