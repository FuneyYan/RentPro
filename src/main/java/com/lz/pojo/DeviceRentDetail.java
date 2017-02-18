package com.lz.pojo;


public class DeviceRentDetail {
    private Integer id;
    private String devicename;
    private String deviceunit;
    private Float deviceprice;
    private Integer num;
    private Float totalprice;
    private Integer rentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDeviceunit() {
        return deviceunit;
    }

    public void setDeviceunit(String deviceunit) {
        this.deviceunit = deviceunit;
    }

    public Float getDeviceprice() {
        return deviceprice;
    }

    public void setDeviceprice(Float deviceprice) {
        this.deviceprice = deviceprice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    @Override
    public String toString() {
        return "DeviceRentDetail{" +
                "id=" + id +
                ", devicename='" + devicename + '\'' +
                ", deviceunit='" + deviceunit + '\'' +
                ", deviceprice=" + deviceprice +
                ", num=" + num +
                ", totalprice=" + totalprice +
                ", rentid=" + rentid +
                '}';
    }
}
