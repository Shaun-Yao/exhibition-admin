package com.honji.exhibition.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.exhibition.admin.entity.Shop;
import com.honji.exhibition.admin.mapper.ShopMapper;
import com.honji.exhibition.admin.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Shop getByCode(String code) {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        Shop shop = shopMapper.selectOne(queryWrapper);

        return shop;
    }
}
