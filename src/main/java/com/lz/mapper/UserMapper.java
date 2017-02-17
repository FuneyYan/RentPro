package com.lz.mapper;

import com.lz.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    User findById(Integer id);

    List<User> findAll();

    void update(User user);

    void save(User user);

    void saveNewUser(User user,Integer[] roleIds);

    void del(Integer id);


    User findByUserName(String username);

    Long countByParam(@Param("queryName")String queryName,@Param("queryRole") String queryRole);

    List<User> findByPageAndParam
            (@Param("start")int start, @Param("pageSize")int pageSize,
             @Param("queryName")String queryName, @Param("queryRole") String queryRole);

}
