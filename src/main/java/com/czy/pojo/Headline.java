package com.czy.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;

/**
 * @TableName news_headline
 */
@Data
public class Headline implements Serializable {
    @TableId
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer isDeleted;

    @Version
    private static final long serialVersionUID = 1L;
}