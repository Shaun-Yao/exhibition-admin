package com.honji.exhibition.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.exhibition.admin.entity.User;
import com.honji.exhibition.admin.model.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
//    @Insert("INSERT INTO exhibition_user(user_name) VALUES(#{userName})")
//    void insert(User user);


    @Select("SELECT `user`.id as id, shop.`code` as shopCode, shop.`name` as shopName FROM `user` " +
            "LEFT JOIN shop on `user`.shop_id = shop.id WHERE shop.code like CONCAT('%', #{shopCode}, '%')")
    List<UserVO> selectUsers(Page<UserVO> page, @Param("shopCode") String shopCode);
}
