package com.lz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/labour/dispatch")
public class LabourDispatchController {
    @RequestMapping(method = RequestMethod.GET)
    public String list(){
        return "labour/dispatch/list";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/add")
    public String add(){
        return "labour/dispatch/add";
    }
}
