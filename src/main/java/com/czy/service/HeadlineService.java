package com.czy.service;

import com.czy.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czy.utils.Result;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-09 16:47:59
*/
public interface HeadlineService extends IService<Headline> {

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result update(Headline headline);
}
