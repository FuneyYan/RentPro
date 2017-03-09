package com.lz.controller;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.Maps;
import com.lz.dto.DataTablesResult;
import com.lz.dto.RenderJson;
import com.lz.dto.WorkRentDto;
import com.lz.exception.NotFoundException;
import com.lz.pojo.*;
import com.lz.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/labour/dispatch")
public class LabourDispatchController {

    @Autowired
    private WorkService workService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "labour/dispatch/list";
    }

    @GetMapping("/load")
    @ResponseBody
    public DataTablesResult load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);

        List<WorkRent> deviceRentList = workService.findDeviceRentByQueryParam(queryParam);
        Long count = workService.countOfDeviceRent();

        return new DataTablesResult(draw,count,count,deviceRentList);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String add(Model model) {
        //        获取工种
        List<WorkType> workTypes = workService.findAll();
        model.addAttribute("workTypeList", workTypes);
        return "labour/dispatch/add";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/render.json")
    @ResponseBody
    public RenderJson getJson(Integer id) {
        WorkType workType = workService.findWorkById(id);
        if (workType == null) {
            return new RenderJson(RenderJson.ERROR, "工种不存在");
        } else {
            return new RenderJson(workType);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    @ResponseBody
    public RenderJson newWorkRent(@RequestBody WorkRentDto workRentDto) {
        try {
            String serialNumber = workService.saveRent(workRentDto);
            return new RenderJson(serialNumber);
        }catch (RuntimeException e){
            return new RenderJson(RenderJson.ERROR,e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/rent/{serialNumber:\\d+}")
    public String showDeviceRent(@PathVariable String serialNumber, Model model) {
        //1.查询合同对象
        WorkRent workRent = workService.findDeviceRentBySerialNumber(serialNumber);
        if(workRent == null) {
            throw new NotFoundException();
        } else {
            //2.查询合同详情列表
            List<WorkRentDetail> detailList = workService.findDeviceRentDetailListByRentId(workRent.getId());
            //3.查询合同文件列表
            List<WorkRentDoc> docList = workService.findDeviceRentDocListByRentId(workRent.getId());

            model.addAttribute("rent",workRent);
            model.addAttribute("detailList",detailList);
            model.addAttribute("docList",docList);
            return "/labour/dispatch/show";
        }
    }



    @RequestMapping(value = "/doc",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        InputStream inputStream = workService.downloadFile(id);
        if(inputStream == null) {
            throw new NotFoundException();
        } else {
            WorkRentDoc doc = workService.findDeviceRentDocById(id);
            String fileName = doc.getSourcename();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));
            return new ResponseEntity<>(new InputStreamResource(inputStream),headers, HttpStatus.OK);
        }
    }

    /**
     * 合同文件的打包下载
     * @param id
     */
    @RequestMapping("/doc/zip")
    public void downloadZipFile(Integer id,HttpServletResponse response) throws IOException {
        WorkRent rent = workService.findDeviceRentById(id);
        if(rent == null) {
            throw new NotFoundException();
        } else {
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = rent.getCompanyname()+".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            workService.downloadZipFile(rent,zipOutputStream);
        }
    }

    @PostMapping("/state/change")
    @ResponseBody
    public RenderJson changeRentState(Integer id) {
        workService.changeRentState(id);
        return new RenderJson(RenderJson.SUCCESS);
    }
}
