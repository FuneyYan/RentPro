package com.lz.controller;

import com.lz.exception.NotFoundException;
import com.lz.pojo.Role;
import com.lz.pojo.User;
import com.lz.service.UserService;
import com.lz.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String findById(@PathVariable Integer id){
        User user=userService.findById(id);
        System.out.println(user);
        return "user/list";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String findAll(@RequestParam(required = false,defaultValue = "1") Integer p,
                          @RequestParam(required = false,defaultValue = "",name = "q_name") String queryName,
                          @RequestParam(required = false,defaultValue = "",name = "q_role") String queryRole,
                          Model model) throws UnsupportedEncodingException {
        if(StringUtils.isNotEmpty(queryName)){
            queryName=new String(queryName.getBytes("ISO8859-1"),"UTF-8");
        }
        Page<User> page=userService.findUserByPageNoAndSearchParam(p,queryName,queryRole);
//        List<User> userList=userService.findAll();
        List<Role> roleList=userService.findAllRole();
        model.addAttribute("page",page);
        model.addAttribute("roleList",roleList);
        model.addAttribute("queryRole",queryRole);
        model.addAttribute("queryName",queryName);
        return "user/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<Role> roleList=userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user,Integer[] roleIds, RedirectAttributes redirectAttributes){
        userService.saveNewUser(user,roleIds);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String del(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        userService.del(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model){
        User user=userService.findById(id);
        if(user==null){
            throw new NotFoundException();
        }
//        找出所有角色
        List<Role> roleList=userService.findAllRole();
        model.addAttribute("roleList",roleList);
        model.addAttribute("user",user);
        return "user/update";
    }

    @RequestMapping(value ="/{id:\\d+}/edit",method = RequestMethod.POST)
    public String update(User user,Integer[] roleIds,RedirectAttributes redirectAttributes){
        userService.update(user,roleIds);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/user";
    }




}
