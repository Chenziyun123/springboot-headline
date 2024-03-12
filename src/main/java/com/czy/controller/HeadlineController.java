package com.czy.controller;

import com.czy.pojo.Headline;
import com.czy.service.HeadlineService;
import com.czy.utils.JwtHelper;
import com.czy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: HeadlineController
 * Package: com.czy.controller
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/11 15:24
 * @Version 1.0  
 */
@RestController
@CrossOrigin
@RequestMapping("headline")
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader("token") String token){
        Long userId = jwtHelper.getUserId(token);
        headline.setPublisher(userId.intValue());

        return headlineService.publish(headline);
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        return headlineService.findHeadlineByHid(hid);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        return headlineService.update(headline);
    }

    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
