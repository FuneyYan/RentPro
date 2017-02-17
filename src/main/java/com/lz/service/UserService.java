package com.lz.service;

import com.lz.pojo.Role;
import com.lz.pojo.User;
import com.lz.util.Page;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    void update(User user,Integer[] roleids);
    void save(User user);
    void saveNewUser(User user,Integer[] roleIds);
    void del(Integer id);
    User findByUserName(String username);

    List<Role> findAllRole();

    Page<User> findUserByPageNoAndSearchParam(Integer p, String queryName, String queryRole);

}
