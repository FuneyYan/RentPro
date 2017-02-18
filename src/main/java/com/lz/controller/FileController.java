package com.lz.controller;

import com.lz.dto.RenderJson;
import com.lz.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public RenderJson uploadFile(MultipartFile file){
        try {
            String newFileName=fileService.uploadFile(file.getOriginalFilename(),file.getContentType(),file.getInputStream());
            Map<String,String> map=new HashMap<>();
            map.put("newFileName",newFileName);
            map.put("sourceName",file.getOriginalFilename());
            return new RenderJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new RenderJson(RenderJson.ERROR,"上传文件错误");
        }
    }

}
