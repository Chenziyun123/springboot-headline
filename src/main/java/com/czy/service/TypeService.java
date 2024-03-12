package com.czy.service;

import com.czy.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czy.pojo.po.PortalVo;
import com.czy.utils.Result;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-03-09 16:47:59
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);
}
