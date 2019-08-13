package com.honji.exhibition.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.CheckIn;
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
 * @since 2019-08-10
 */
public interface CheckInMapper extends BaseMapper<CheckIn> {

    @Select("SELECT `user`.id as id, shop.`code` as shopCode, shop.`name` as shopName FROM `user`\n" +
            "LEFT JOIN shop on `user`.shop_id = shop.id\n" +
            "WHERE `user`.id not in (SELECT user_id FROM check_in)\n" +
            "and shop.`code` LIKE CONCAT('%',#{shopCode},'%')")
    List<UserVO> selectUnsignedUsers(Page<UserVO> page, @Param("shopCode") String shopCode);

    @Select("SELECT check_in.id as id , `user`.id as userId, shop.`code` as shopCode, shop.`name` as shopName FROM check_in\n" +
            "LEFT JOIN `user` on check_in.user_id = `user`.id\n" +
            "LEFT JOIN shop on `user`.shop_id = shop.id\n" +
            "WHERE  shop.`code` LIKE CONCAT('%',#{shopCode},'%')")
    List<AssignedUserVO> selectSignedUsers(Page<AssignedUserVO> page, @Param("shopCode") String shopCode);

}
