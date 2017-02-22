package com.lz.pojo;

public class WorkRentDetail {
    private Integer id;
    private String workname;
    private Float workprice;
    private Integer worknum;
    private Float totalprice;
    private Integer rentid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public Float getWorkprice() {
        return workprice;
    }

    public void setWorkprice(Float workprice) {
        this.workprice = workprice;
    }

    public Integer getWorknum() {
        return worknum;
    }

    public void setWorknum(Integer worknum) {
        this.worknum = worknum;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getRentid() {
        return rentid;
    }

    public void setRentid(Integer rentid) {
        this.rentid = rentid;
    }
}
