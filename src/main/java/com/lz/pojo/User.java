package com.lz.pojo;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private List<Role> roleList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }


    public String getRoleNames(){
        List<String> viewNames= Lists.newArrayList(Collections2.transform(getRoleList(), new Function<Role,String>() {
            @Override
            public String apply(Role role) {
                return role.getViewname();
            }
        }));
        StringBuilder sb=new StringBuilder();
        for(String s:viewNames){
            sb.append(s).append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
