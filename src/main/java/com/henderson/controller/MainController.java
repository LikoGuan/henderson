package com.henderson.controller;

//import com.roskill.common.model.Employee;
//import com.roskill.common.service.DemoDubboService;
import com.guan.common.DemoDubboService;
import com.guan.common.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by likoguan on 11/03/18.
 */
@Controller
public class MainController {
//    @Autowired
//    private DemoDubboService demoDubboService;
    @Autowired
    private DemoDubboService demoDubboService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
//        Employee employee = demoDubboService.getEmployeeById(1L);
        Employee employee = demoDubboService.getEmployeeById(1L);
        return "hello";
    }
}
