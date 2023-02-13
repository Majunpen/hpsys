package com.hp.controller;


import com.hp.pojo.HolidayType;
import com.hp.service.IHolidayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/holidayType")
public class HolidayTypeController {
    @Autowired
    private IHolidayTypeService holidayTypeService;
    @RequestMapping("list")
    public List<HolidayType> findAll(){
        return holidayTypeService.list();
    }
}
