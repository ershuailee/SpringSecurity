package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p> 跨域问题配置类 </P>
 *
 * @author liershuai
 * @since 2023/3/27 13:19
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 项目中的所有接口都支持跨域
                .addMapping("/**")
                // 所有地址都可以访问，也可以配置具体地址
                .allowedOriginPatterns("*")
                //4.允许凭证
                .allowCredentials(true)
                //"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}