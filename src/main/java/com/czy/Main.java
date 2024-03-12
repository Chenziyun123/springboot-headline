package com.czy;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.czy.utils.JwtHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: ${NAME}
 * Package: com.czy
 * Description:
 *
 * @Author Ziyun Chen
 * @Create ${DATE} ${TIME}
 * @Version 1.0  
 */
@SpringBootApplication
@MapperScan("com.czy.mapper")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // 配置mybatis-plus插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));// 分页
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());// 乐观锁
        mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());// 防止全局修改和删除
        return mybatisPlusInterceptor;
    }
}