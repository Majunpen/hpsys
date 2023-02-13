package com.hp.dto;

import com.hp.req.BaseQuery;
import lombok.Data;

/**
 * @Author: MJP
 * @Date: 2023/2/3 - 02 - 03 - 19:02
 * @Description: com.hp.dto
 * @version: 1.0
 */
@Data
public class HolidayApplyQuery extends BaseQuery {
    private String title;//请假标题
    private String status;//请假流程状态
    private String startTime;//请假开始时间
    private String endTime;//请假结束时间
    private String userId;//请假人id

}
