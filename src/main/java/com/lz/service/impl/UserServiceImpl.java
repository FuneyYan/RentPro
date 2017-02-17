package com.lz.service.impl;

import com.lz.mapper.RoleMapper;
import com.lz.mapper.UserMapper;
import com.lz.pojo.Role;
import com.lz.pojo.User;
import com.lz.service.UserService;
import com.lz.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    @Transactional
    public void update(User user,Integer[] roleIds) {
        roleMapper.delByUserId(user.getId());
        addUserRole(user,roleIds);
        userMapper.update(user);
    }

    private void addUserRole(User user, Integer[] roleIds) {
        if(roleIds!=null){
            for(Integer roleid:roleIds){
                Role role=roleMapper.findById(roleid);
                if(role!=null){
                    roleMapper.saveUserRole(user.getId(),roleid);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    @Transactional
    public void saveNewUser(User user, Integer[] roleIds) {
//        保存用户
        save(user);
        addUserRole(user,roleIds);//保存用户角色
    }

    @Override
    @Transactional
    public void del(Integer id) {
//        删除用户必须先删除对应的关系表中的数据
        roleMapper.delByUserId(id);
        userMapper.del(id);
    }

    /**
     * 根据用户名查找账户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        User user=userMapper.findByUserName(username);
        return user;
    }

    @Override
    public List<Role> findAllRole() {
        List<Role> roleList=roleMapper.findAllRole();
        return roleList;
    }

    @Override
    public Page<User> findUserByPageNoAndSearchParam(Integer p, String queryName, String queryRole) {
        int total=userMapper.countByParam(queryName,queryRole).intValue();
        Page<User> page=new Page<>(total,p);
        List<User> list=userMapper.findByPageAndParam(page.getStart(),page.getPageSize(),queryName,queryRole);
        page.setItems(list);
        return page;
    }
}
