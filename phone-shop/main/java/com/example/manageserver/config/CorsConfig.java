package com.example.manageserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许的源，这里是前端应用的地址
        config.addAllowedOriginPattern("http://localhost:5173");
        config.addAllowedOriginPattern("https://localhost:5173");
        config.addAllowedHeader("*"); // 允许任何头信息
        config.addExposedHeader("Authorization");
        config.addAllowedMethod("*"); // 允许任何方法（POST、GET等）
        config.setAllowCredentials(true); // 允许携带凭证（如 cookies）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有接口都有效

        return new CorsFilter(source);
    }
}