package com.lz.controller;

import com.google.common.collect.Maps;
import com.lz.pojo.Device;
import com.lz.service.DeviceService;
import org.apache.ibatis.javassist.compiler.MemberResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.util.resources.cldr.mg.LocaleNames_mg;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/setting/device")
public class SettingDeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public String allDevice(Model model){
        return "setting/device/list";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/load")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String orderIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumn = request.getParameter("columns["+orderIndex+"][name]");
        String deviceName = request.getParameter("deviceName");

        Map<String,Object> searchParam = Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);
        searchParam.put("orderType",orderType);
        searchParam.put("orderColumn",orderColumn);
        searchParam.put("deviceName",deviceName);

        List<Device> deviceList=deviceService.findDeviceByParam(searchParam);
        Long count=deviceService.count();
        Long filterCount=deviceService.filterCount(searchParam);

        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("draw",draw);
        resultMap.put("recordsTotal",count); //总记录数
        resultMap.put("recordsFiltered",filterCount); //过滤后的总记录数
        resultMap.put("data",deviceList);

        return resultMap;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/add")
    public String add(){
        return "setting/device/add";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public String add(Device device,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","添加设备成功");
        deviceService.addNewDevice(device);
        return "redirect:/setting/device";
    }

}
