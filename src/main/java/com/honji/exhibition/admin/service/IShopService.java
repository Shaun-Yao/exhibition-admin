package com.honji.exhibition.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.exhibition.admin.entity.Shop;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
public interface IShopService extends IService<Shop> {
    Shop getByCode(String code);
}
