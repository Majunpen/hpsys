package com.hp.service.impl;

import com.hp.pojo.HolidayType;
import com.hp.mapper.HolidayTypeMapper;
import com.hp.service.IHolidayTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-02-01
 */
@Service
public class HolidayTypeServiceImpl extends ServiceImpl<HolidayTypeMapper, HolidayType> implements IHolidayTypeService {

}
