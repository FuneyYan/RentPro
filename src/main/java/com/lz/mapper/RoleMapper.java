package com.lz.mapper;

import com.lz.pojo.Role;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RoleMapper {
    List<Role> findAllRole();

    Role findById(Integer id);

    void saveUserRole(Integer userId, Integer roleId);

    void delByUserId(Integer id);

    List<Role> findByUserId(Integer userId);
}
