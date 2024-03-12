package com.czy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czy.pojo.User;
import com.czy.service.UserService;
import com.czy.mapper.UserMapper;
import com.czy.utils.JwtHelper;
import com.czy.utils.MD5Util;
import com.czy.utils.Result;
import com.czy.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-09 16:47:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result loginUser(String username, String userPwd) {
        Result result = null;
        // 1.查询用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user1 = userMapper.selectOne(wrapper);
        if (user1 == null){
            // 1.2 不存在，返回用户不在Result对象
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }else{
            // 1.1 存在，查询用户密码
            if (!MD5Util.encrypt(userPwd).equals(user1.getUserPwd())){
                // 2.查询用户密码是否正确
                // 2.1 密码错误，返回错误信息
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }else {
                // 2.2 密码正确，返回token信息
                String token = jwtHelper.createToken(user1.getUid().longValue());
                HashMap<String, String> map = new HashMap<>();
                map.put("token", token);
                result = Result.ok(map);
            }

        }
        return result;
    }

    @Override
    public Result queryUserInfo(String token) {
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if (token == null){
        }

         // 1.判断token是否过期，过期直接返回Result
        if (!jwtHelper.isExpiration(token)){
            // 2.未过期，解析token，获得uid
            Long userId = jwtHelper.getUserId(token);
            // 3.根据uid查询数据库中的用户信息，返回查询到的结果
            User user = userMapper.selectById(userId);
            if (user != null){
                HashMap<String, User> map = new HashMap<>();
                map.put("loginUser", user);
                return Result.ok(map);
            }
        }


        return result;
    }

    @Override
    public Result checkUserName(String username) {
        // 根据用户名查询数据库中，是否有一条该数据
        Result result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (user != null){
            result = Result.ok(null);
        }

        return result;
    }

    @Override
    public Result regist(String username, String userPwd, String nickName) {
        Result result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        // 1.查询用户名是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (user == null){
            // 2.不存在，插入数据
            User user1 = new User();
            user1.setUsername(username); user1.setUserPwd(userPwd); user1.setNickName(nickName);

            int rows = userMapper.insert(user1);
            if (rows > 0){
                // 3.成功，返回结果
                return Result.ok(null);
            }
        }

        return result;
    }
}




