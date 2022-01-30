package com.parlantos.guild.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GuildInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public MemberIdInterceptor memberIdInterceptor() {
        return new MemberIdInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberIdInterceptor());
    }
}
