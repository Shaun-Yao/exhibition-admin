package com.honji.exhibition.admin.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.User;
import com.honji.exhibition.admin.model.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-03-01
 */
public interface IUserService extends IService<User> {

    void insert(User user);

    List<UserVO> getUsers(Page<UserVO> page, String shopCode);

    void delete(Long id);
}
