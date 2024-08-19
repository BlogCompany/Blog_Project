package com.example.blog;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://niceblog.myvnc.com:81")
                .allowedMethods("*") // 允許的方法
                .allowCredentials(true)
                .allowedHeaders("Authorization", "*");
    }


}
