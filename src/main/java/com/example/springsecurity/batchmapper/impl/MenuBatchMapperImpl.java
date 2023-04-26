package com.example.springsecurity.batchmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.batchmapper.MenuBatchMapper;
import com.example.springsecurity.entity.user.MenuEntity;
import com.example.springsecurity.mapper.MenuMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 菜单表 存储实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-26
 */
@Component
public class MenuBatchMapperImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuBatchMapper {

}
