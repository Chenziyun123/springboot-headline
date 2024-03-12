package com.czy.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: PortalVo
 * Package: com.czy.pojo.po
 * Description:
 *
 * @Author Ziyun Chen
 * @Create 2024/3/11 13:11
 * @Version 1.0  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalVo {
    private String keyWords;
    private Integer type=0;
    private Integer pageNum = 1;
    private Integer pageSize =10;
}
