package com.czy.service;

import com.czy.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czy.utils.Result;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-09 16:47:59
*/
public interface UserService extends IService<User> {

    Result loginUser(String username, String userPwd);

    Result queryUserInfo(String token);

    Result checkUserName(String username);

    Result regist(String username, String userPwd, String nickName);
}
