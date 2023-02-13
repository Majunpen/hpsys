package com.hp.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MJP
 * @Date: 2023/1/12 - 01 - 12 - 16:02
 * @Description: com.hp.req
 * @version: 1.0
 */
@Data
public class BaseQuery implements Serializable {
    private Integer page; //当前页码
    private Integer limit;//每页显示信息条数
}
