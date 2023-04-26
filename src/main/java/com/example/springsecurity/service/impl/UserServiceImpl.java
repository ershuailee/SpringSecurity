package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.batchmapper.UserBatchMapper;
import com.example.springsecurity.dto.AuthRequestDTO;
import com.example.springsecurity.dto.UserRegisterDTO;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
import com.example.springsecurity.service.JWTService;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.vo.AuthResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2023-04-01
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserBatchMapper userBatchMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JWTService jwtService;

    /**
     * 用户注册
     *
     * @param user 用户注册信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterDTO user) {
        if (StringUtils.isEmpty(user.getUsername()) || null != this.getUserByUsername(user.getUsername())) {
            throw new BusinessException(BusinessErrorCodes.USER_NAME_HAS_EXISTED);
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword(encoder.encode(user.getPassword()));

        userEntity.setCreateId(1L);
        userEntity.setCreateTime(LocalDateTime.now());
        if (!userBatchMapper.save(userEntity)) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 用户登录
     *
     * @param requestVO 请求参数
     * @return 登录数据
     */
    @Override
    public AuthResponseVO login(AuthRequestDTO requestVO) {

        String username = requestVO.getUsername();
        String password = requestVO.getPassword();

        // 进行用户认证
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            authentication.getPrincipal();
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCodes.PASSWORD_ERROR);
        }
        UserEntity user = getUserByUsername(username);

        // 认证通过生成token
        String token = jwtService.generateToken(username);

        AuthResponseVO responseVO = new AuthResponseVO();
        responseVO.setUserId(user.getId());
        responseVO.setUsername(username);
        responseVO.setToken(token);

        return responseVO;
    }

    /**
     * 通过用户名查询用户数据
     *
     * @param username 用户名
     * @return 用户数据
     */
    public UserEntity getUserByUsername(String username) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(username), UserEntity::getUsername, username);

        return userBatchMapper.getOne(wrapper);
    }

}
