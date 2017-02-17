package com.lz.controller;

import com.lz.dto.RenderJson;
import com.lz.pojo.Device;
import com.lz.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(){
        return "device/rent/list";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/add")
    public String addRent(Model model){
        List<Device> deviceList=deviceService.findAll();
        model.addAttribute("deviceList",deviceList);
        return "device/rent/new";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/render.json")
    @ResponseBody
    public RenderJson renderJson(Integer id){
        Device device=deviceService.findDeviceById(id);
        if(device!=null){
            return new RenderJson(device);
        }else{
            return new RenderJson(RenderJson.ERROR,"找不到该设备");
        }
    }

}
