package com.czy.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName news_type
 */
@Data
public class Type implements Serializable {
    @TableId
    private Integer tid;

    private String tname;

    private Integer version;

    private Integer isDeleted;

    @Version
    private static final long serialVersionUID = 1L;
}