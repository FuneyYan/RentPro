package com.lz.pojo;

import java.io.Serializable;

public class Role implements Serializable{
    private Integer id;
    private String rolename;
    private String viewname;

    public Role() {
    }

    public Role(String rolename, String viewname) {
        this.rolename = rolename;
        this.viewname = viewname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", viewname='" + viewname + '\'' +
                '}';
    }
}
