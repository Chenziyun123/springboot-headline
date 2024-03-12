package com.czy.test;

import com.czy.service.HeadlineService;
import com.czy.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: com.czy.test.HeadlineTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/9 16:55
 * @Version 1.0  
 */
@SpringBootTest
public class HeadlineTest {
    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test01(){
        //生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        //解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        //校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }
}
