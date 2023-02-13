package com.hp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MJP
 * @Date: 2023/1/13 - 01 - 13 - 14:39
 * @Description: com.hp.dto
 * @version: 1.0
 */
@Data
public class DeptDto implements Serializable {
    //tree 结构类
    private Integer id;
    private String title;
    private List<DeptDto> children = new ArrayList<>();
}
