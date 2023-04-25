package com.example.springsecurity.config;

import com.example.springsecurity.filter.JWTAuthenticationFilter;
import com.example.springsecurity.properties.AuthProperties;
import com.example.springsecurity.service.JWTService;
import com.example.springsecurity.service.impl.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

/**
 * WebSecurityConfig
 *
 * @author 李二帅
 * @since 2023/4/25 11:00
 */
@EnableWebSecurity
@EnableConfigurationProperties(AuthProperties.class)
public class WebSecurityConfig {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private AuthProperties authProperties;
    @Lazy
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JWTService jwtService;
    @Resource
    private CacheManager cacheManager;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 下面开始设置权限
                .authorizeRequests(authorize -> authorize
                        .antMatchers(authProperties.getPermitStatic().toArray(new String[0])).permitAll()
                        .antMatchers(authProperties.getPermitMethod().toArray(new String[0])).permitAll()
                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated())
                .addFilter(new JWTAuthenticationFilter(authenticationManager, sysUserService, jwtService, userCache()))
                .exceptionHandling().authenticationEntryPoint(new UserAuthenticationEntryPoint()).and()
                // 认证用户时用户信息加载配置，注入springAuthUserService
                .userDetailsService(sysUserService).build();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration authenticationConfiguration
     * @return date
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * 密码明文加密方式配置（使用国密SM4）
     *
     * @return 加密后密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UserCache userCache() {
        Cache ca = cacheManager.getCache("userCache");
        return new SpringCacheBasedUserCache(ca);
    }
}