package com.czy.controller;

import com.czy.pojo.User;
import com.czy.service.UserService;
import com.czy.utils.JwtHelper;
import com.czy.utils.Result;
import com.czy.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.czy.controller
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/11 10:40
 * @Version 1.0  
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("login")
    public Result login(@RequestBody User user){
        // 调用service层，查询结果并返回
        return userService.loginUser(user.getUsername(), user.getUserPwd());
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token", required = false) String token){
        return userService.queryUserInfo(token);
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam("username") String username){
        return userService.checkUserName(username);
    }

    @PostMapping("regist")
    public Result regist(String username, String userPwd, String nickName){
        return userService.regist(username, userPwd, nickName);
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader("token") String token){
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}
