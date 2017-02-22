package com.lz.controller;

import com.lz.dto.RenderJson;
import com.lz.pojo.Disk;
import com.lz.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

@Controller
@RequestMapping("/netdisk")
public class NetDiskController {

    @Autowired
    private DiskService diskService;
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "0") Integer path
            , Model model){
        List<Disk> diskList=diskService.findFileByFid(path);
        model.addAttribute("diskList",diskList);
        model.addAttribute("fid",path);
        return "/disk/list";
    }

    @RequestMapping("/folder/new")
    @ResponseBody
    public RenderJson addFolder(Disk disk){
        diskService.save(disk);
        return new RenderJson(RenderJson.SUCCESS);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/upload")
    @ResponseBody
    public RenderJson upload(Integer fid,MultipartFile file){
        try{
            diskService.saveNewFile(fid,file);
            return new RenderJson(RenderJson.SUCCESS);
        }catch (RuntimeException e){
            return new RenderJson(RenderJson.ERROR,e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws FileNotFoundException {
        InputStream inputStream=diskService.downloadFile(id);
        Disk disk=diskService.findFileById(id);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachement",disk.getSourcename(), Charset.forName("UTF-8"));
        return new ResponseEntity<>(new InputStreamResource(inputStream),httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/del/{id:\\d+}")
    @ResponseBody
    public RenderJson del(@PathVariable Integer id){
        diskService.del(id);
        return new RenderJson(RenderJson.SUCCESS);
    }
}
