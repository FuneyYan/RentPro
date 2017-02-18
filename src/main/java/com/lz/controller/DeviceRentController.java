package com.lz.controller;

import com.lz.dto.DeviceRentDto;
import com.lz.dto.RenderJson;
import com.lz.exception.NotFoundException;
import com.lz.pojo.Device;
import com.lz.pojo.DeviceRent;
import com.lz.pojo.DeviceRentDetail;
import com.lz.pojo.DeviceRentDoc;
import com.lz.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST,value = "/new")
    @ResponseBody
    public RenderJson saveRent(@RequestBody DeviceRentDto deviceRentDto){
        String serialNumber=deviceService.saveRent(deviceRentDto);

        RenderJson renderJson=new RenderJson(serialNumber);
        return renderJson;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{serialNumber:\\d+}")
    public String showDeviceRent(@PathVariable String serialNumber,Model model){
        DeviceRent deviceRent=deviceService.findDeviceRentBySerialNumber(serialNumber);
        if(deviceRent==null){
            throw new NotFoundException();
        }else{
            List<DeviceRentDetail> rentDetailList=deviceService.findDeviceDetailListByRentId(deviceRent.getId());
            List<DeviceRentDoc> rentDocList=deviceService.findDeviceDocListByRentId(deviceRent.getId());

            model.addAttribute("rentDetailList",rentDetailList);
            model.addAttribute("rentDocList",rentDocList);
            model.addAttribute("deviceRent",deviceRent);
            return "device/rent/show";
        }
    }

}
