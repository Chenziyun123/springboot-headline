package com.czy.interceptor;

import com.czy.utils.JwtHelper;
import com.czy.utils.Result;
import com.czy.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: LoginProtectInterceptot
 * Package: com.czy.interceptor
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/11 15:16
 * @Version 1.0  
 */
// 所有/headline开头都需要检查登陆
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null && !jwtHelper.isExpiration(token)){
            // 放行
            return true;
        }

        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);
        //拦截
        return false;
    }
}
