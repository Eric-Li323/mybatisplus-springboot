package com.itcast.mp.mybatisplusspringboot;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.itcast.mp.mybatisplusspringboot.mapper")
public class MybatisPlusConfig {
      //方法一：直接定义配置类
   //配置分页插件的拦截器
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


      //方法二：使用xml配置，在mybatis-config.xml中
}
