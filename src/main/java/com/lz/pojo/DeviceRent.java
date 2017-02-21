package com.lz.pojo;


import java.sql.Timestamp;

public class DeviceRent {

    private Integer id;
    private String companyname;
    private String linkman;
    private String cardnum;
    private String tel;
    private String address;
    private String fax;
    private String rentdate;
    private String backdate;
    private Integer totalday;
    private Float totalprice;
    private Float precost;
    private Float lastcost;
    private Timestamp createtime;
    private String createuser;
    private String serialnumber;
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRentdate() {
        return rentdate;
    }

    public void setRentdate(String rentdate) {
        this.rentdate = rentdate;
    }

    public String getBackdate() {
        return backdate;
    }

    public void setBackdate(String backdate) {
        this.backdate = backdate;
    }

    public Integer getTotalday() {
        return totalday;
    }

    public void setTotalday(Integer totalday) {
        this.totalday = totalday;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public Float getPrecost() {
        return precost;
    }

    public void setPrecost(Float precost) {
        this.precost = precost;
    }

    public Float getLastcost() {
        return lastcost;
    }

    public void setLastcost(Float lastcost) {
        this.lastcost = lastcost;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DeviceRent{" +
                "id=" + id +
                ", companyname='" + companyname + '\'' +
                ", linkman='" + linkman + '\'' +
                ", cardnum='" + cardnum + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", fax='" + fax + '\'' +
                ", rentdate='" + rentdate + '\'' +
                ", backdate='" + backdate + '\'' +
                ", totalday='" + totalday + '\'' +
                ", totalprice=" + totalprice +
                ", precost=" + precost +
                ", lastcost=" + lastcost +
                ", createtime=" + createtime +
                ", createuser='" + createuser + '\'' +
                ", serialnumber='" + serialnumber + '\'' +
                '}';
    }
}
