package com.honji.exhibition.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honji.exhibition.admin.entity.Schedule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2019-07-20
 */
public interface ScheduleMapper extends BaseMapper<Schedule> {


    @Select({"<script>",
            "SELECT schedule.* FROM schedule ",
            "LEFT JOIN `user` ON schedule.user_id = `user`.id ",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE 1=1 ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "<if test='userId!=null and userId!=\"\"'>",
            "AND schedule.user_id like CONCAT('%', #{userId}, '%')",
            "</if>",
            "</script>"})
    IPage<Schedule> selectForIndex(IPage<Schedule> page, @Param("shopType") String shopType, @Param("userId") String userId);


}
