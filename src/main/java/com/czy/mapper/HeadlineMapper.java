package com.czy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czy.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czy.pojo.po.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-03-09 16:47:59
* @Entity com.czy.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectPageMap(Page<Headline> page, @Param("portalVo") PortalVo portalVo);

    Map selectHeadlineDetailByHid(Integer hid);
}




