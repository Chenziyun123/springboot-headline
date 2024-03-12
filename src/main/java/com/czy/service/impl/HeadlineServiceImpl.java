package com.czy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czy.pojo.Headline;
import com.czy.service.HeadlineService;
import com.czy.mapper.HeadlineMapper;
import com.czy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-09 16:47:59
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result publish(Headline headline) {
        headline.setVersion(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);

        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("headline", headline);
        return Result.ok(hashMap);
    }

    @Override
    public Result update(Headline headline) {
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}




