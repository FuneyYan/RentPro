package com.lz.controller;

import com.google.common.primitives.Chars;
import com.lz.dto.DeviceRentDto;
import com.lz.dto.RenderJson;
import com.lz.exception.NotFoundException;
import com.lz.pojo.Device;
import com.lz.pojo.DeviceRent;
import com.lz.pojo.DeviceRentDetail;
import com.lz.pojo.DeviceRentDoc;
import com.lz.service.DeviceService;
import org.apache.commons.lang3.CharSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipOutputStream;

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

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    @ResponseBody
    public RenderJson saveRent(@RequestBody DeviceRentDto deviceRentDto){
        try{
            String serialNumber=deviceService.saveRent(deviceRentDto);
            RenderJson renderJson=new RenderJson(serialNumber);
            return renderJson;
        }catch(Exception e){
            return new RenderJson(RenderJson.ERROR,e.getMessage());
        }
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

    @RequestMapping(method = RequestMethod.GET,value = "/doc")
    @ResponseBody
    public ResponseEntity<InputStreamSource> downloadFile(Integer id) throws IOException{
        InputStream inputStream=deviceService.downloadFile(id);
        if(inputStream!=null){
            //SpringMVC方式下载
            DeviceRentDoc doc=deviceService.findDeviceDocById(id);
            String fileName=doc.getSourcename();
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));
            return new ResponseEntity<InputStreamSource>(new InputStreamResource(inputStream),httpHeaders, HttpStatus.OK);
        }
        throw new NotFoundException();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/doc/zip")
    public void downloadZipFile(Integer id, HttpServletResponse response) throws IOException {
        DeviceRent rent=deviceService.findDeviceRentById(id);
        if(rent==null){
            throw new NotFoundException();
        }else{
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            String fileName=rent.getCompanyname()+".zip";
            fileName=new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
            OutputStream outputStream=response.getOutputStream();
            ZipOutputStream zipOutputStream=new ZipOutputStream(outputStream);
            deviceService.downloadZipFile(rent,zipOutputStream);
        }
    }

}
