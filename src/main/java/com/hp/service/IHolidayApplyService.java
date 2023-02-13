package com.hp.service;

import com.hp.dto.HolidayApplyQuery;
import com.hp.pojo.HolidayApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-02-01
 */
public interface IHolidayApplyService extends IService<HolidayApply> {

    //添加请假单
    void saveHolidayApply(HolidayApply holidayApply);

    Map<String, Object> queryMyHolidayApply(HolidayApplyQuery holidayApplyQuery);
}
