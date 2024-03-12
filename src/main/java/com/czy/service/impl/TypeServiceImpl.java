package com.czy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czy.mapper.HeadlineMapper;
import com.czy.pojo.Headline;
import com.czy.pojo.Type;
import com.czy.pojo.User;
import com.czy.pojo.po.PortalVo;
import com.czy.service.TypeService;
import com.czy.mapper.TypeMapper;
import com.czy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-03-09 16:47:59
*/
@Service
@Slf4j
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private HeadlineMapper headlineMapper;
    @Override
    public Result findAllTypes() {
        // 获取所以tname和tid信息
        List<Type> list = typeMapper.selectList(null);
        return Result.ok(list);
    }

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        // 1.开启分页查询
        Page<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());


        // 2.根据关键词以及新闻类型查询新闻
        QueryWrapper<Headline> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()),
                "title", portalVo.getKeyWords())
                .eq(portalVo.getType() != 0,
                        "type", portalVo.getType());

        // 3.返回查询结果
        headlineMapper.selectPageMap(page, portalVo);

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageData", page.getRecords());
        map.put("pageNum", page.getCurrent());
        map.put("pageSize", page.getSize());
        map.put("totalPage", page.getPages());
        map.put("totalSize", page.getTotal());

        HashMap<Object, Object> data = new HashMap<>();
        data.put("pageInfo", map);

        return Result.ok(data);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map map = headlineMapper.selectHeadlineDetailByHid(hid);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("headline", map);

        // 2.拼接头条对象(阅读量和version)进行数据更新
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer)map.get("pageViews") + 1);
        headline.setVersion((Integer) map.get("version") + 1);
        headlineMapper.updateById(headline);

        return Result.ok(map1);
    }
}




