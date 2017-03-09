package com.lz.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lz.dto.DataTablesResult;
import com.lz.dto.RenderJson;
import com.lz.exception.NotFoundException;
import com.lz.pojo.Finance;
import com.lz.service.FinanceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @RequestMapping(method = RequestMethod.GET,value = "/day")
    public String day(){
        return "finance/day";
    }

    /**
     * 财务记录列表
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/day/load")
    @ResponseBody
    public DataTablesResult load(HttpServletRequest request){
        String draw=request.getParameter("draw");
        String start=request.getParameter("start");
        String length=request.getParameter("length");
        String day=request.getParameter("day");

        Map<String,Object> queryParam= Maps.newHashMap();
        queryParam.put("length",length);
        queryParam.put("start",start);
        queryParam.put("day",day);

        List<Finance> financeList=financeService.findFinanceByParam(queryParam);
        Long count=financeService.count();
        Long filterCount=financeService.filterCount(queryParam);
        return new DataTablesResult(draw,count,filterCount,financeList);
    }

    /**
     * 修改财务记录的状态(是否确认付款)
     * @param id 记录id
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/confirm/{id:\\d+}")
    @ResponseBody
    public RenderJson updateState(@PathVariable Integer id){
        try {
            financeService.updateState(id);
            return new RenderJson(RenderJson.SUCCESS);
        }catch (NotFoundException e){
            return new RenderJson(RenderJson.ERROR,"该财务记录不存在");
        }
    }


    /**
     * 将显示的数据导出为Excel文件
     * @param date
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/day/{date}/data.xls")
    public void exportExcel(@PathVariable String date, HttpServletResponse response) throws IOException {
        List<Finance> financeList= financeService.findByCreateDate(date);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename=\""+date+".xls\"");

        Workbook workbook=new HSSFWorkbook();

        Sheet sheet=workbook.createSheet(date+"财务流水");
        Row row=sheet.createRow(0);
        Cell cell1=row.createCell(0);
        cell1.setCellValue("业务流水号");
        String titleArr[]={"创建日期","类型","金额","业务模块","业务流水号",
                "状态","备注","创建人","确认人","确认日期"};
        for (int i = 1; i <=titleArr.length; i++) {
            row.createCell(i).setCellValue(titleArr[i-1]);
        }

        for (int i = 0; i < financeList.size(); i++) {
            Finance f=financeList.get(i);
            Row r=sheet.createRow(i+1);
            r.createCell(0).setCellValue(f.getSerialnumber());
            r.createCell(1).setCellValue(f.getCreatedate());
            r.createCell(2).setCellValue(f.getType());
            r.createCell(3).setCellValue(f.getMoney());
            r.createCell(4).setCellValue(f.getModule());
            r.createCell(5).setCellValue(f.getRentserialnumber());
            r.createCell(6).setCellValue(f.getState());
            r.createCell(7).setCellValue(f.getRemark());
            r.createCell(8).setCellValue(f.getCreateuser());
            r.createCell(9).setCellValue(f.getConfirmuser());
            r.createCell(10).setCellValue(f.getConfirmdate());
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(10);

        OutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/month")
    public String month(Model model){

        return "finance/month";
    }
}
