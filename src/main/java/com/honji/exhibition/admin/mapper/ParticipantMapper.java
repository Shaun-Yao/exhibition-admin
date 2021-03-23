package com.honji.exhibition.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.model.ParticipantVO;
import org.apache.ibatis.annotations.Param;
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


    @Select({"<script>",
            "SELECT participant.id, participant.user_id as userId, participant.name, ",
            "participant.mobile, participant.sex, participant.attend_training as attendTraining, participant.attend_hot_spring as attendHotSpring, ",
            "shop.type as shopType, shop.`code` as shopCode, shop.`name` as shopName, shop.area, shop.small_area as smallArea FROM participant",
            "LEFT JOIN `user` ON participant.user_id = `user`.id",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE 1=1 ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "<if test='name!=null and name!=\"\"'>",
            "AND participant.name like CONCAT('%', #{name}, '%')",
            "</if>",
            "</script>"})
    IPage<ParticipantVO> selectForIndex(Page<Participant> page, @Param("shopType") String shopType, @Param("name") String name);

    /**
     * 查询指定区域所有未分配的房间的参与人
     * @param shopType
     * @return
     */
    @Select({"<script>",
            "SELECT participant.id, participant.name, participant.sex FROM participant",
            "LEFT JOIN `user` ON participant.user_id = `user`.id",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE participant.id not in (SELECT participant_id FROM room_participant) ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "</script>"})
    List<Participant> selectAvailable(@Param("shopType") String shopType);

    /**
     * 查询指定区域所有未分配的房间的儿童
     * @param shopType
     * @return
     */
    @Select({"<script>",
            "SELECT participant.id, participant.name FROM participant",
            "LEFT JOIN `user` ON participant.user_id = `user`.id",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE sex = 3",
            "AND participant.id not in (SELECT participant_id FROM room_participant) ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "</script>"})
    List<Participant> selectAvailableChildren(@Param("shopType") String shopType);

    /**
     * 查找该用户下面已经分配房间的参与人
     * @param shopType
     * @return
     */
    @Select({"<script>",
            "SELECT participant.id, participant.name FROM participant",
            "LEFT JOIN `user` ON participant.user_id = `user`.id",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE 1=1 ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "</script>"})
    List<Participant> selectUnAvailable(@Param("shopType") String shopType);

    @Select("SELECT *  FROM participant WHERE user_id IN (SELECT id FROM `user` " +
            " WHERE shop_id IN ( SELECT id FROM shop WHERE area IN ( SELECT area FROM shop WHERE id = ( SELECT shop_id FROM `user` WHERE id = #{userId} ) ) ))" +
            " AND id not in (SELECT participant_id FROM room_participant)")
    List<Participant> selectByArea(Long userId);

    @Select("SELECT * FROM participant WHERE id not in (SELECT participant_id FROM room_participant)")
    List<Participant> selectLeft();

    @Select("SELECT * FROM participant WHERE sex = 3 and id not in (SELECT participant_id FROM room_participant)")
    List<Participant> selectChildren();

    @Select("SELECT participant.* FROM participant  " +
            "LEFT JOIN room_participant rp ON participant.id = rp.participant_id " +
            "LEFT JOIN room ON rp.room_id = room.id WHERE room.id = #{roomId}")
    List<Participant> selectByRoom(Long roomId);

}
