package com.hp.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MJP
 * @Date: 2023/1/13 - 01 - 13 - 22:34
 * @Description: com.hp.dto
 * @version: 1.0
 */
@Data
public class TreeDto implements Serializable {
    private Integer id; //菜单id
    private Integer pId; //parentId
    private String name; //菜单名
    private Boolean checked;//是否拥有改角色
}
