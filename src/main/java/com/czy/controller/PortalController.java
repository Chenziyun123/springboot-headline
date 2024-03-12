package com.czy.controller;

import com.czy.pojo.po.PortalVo;
import com.czy.service.TypeService;
import com.czy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: PortalController
 * Package: com.czy.controller
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/11 12:49
 * @Version 1.0  
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        return typeService.findAllTypes();
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        return typeService.findNewsPage(portalVo);
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        return typeService.showHeadlineDetail(hid);
    }

}
