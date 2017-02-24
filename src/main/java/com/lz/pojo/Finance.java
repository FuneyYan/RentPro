package com.lz.pojo;

public class Finance {

//    type
    public static final String INCOME="收入";
    public static final String EXPEND="支出";
//    state
    public static final String PAY_YET="已确认";
    public static final String PAY_NOT="未确认";
//    remark
    public static final String PRECOST="预付款";
    public static final String LASTCOST="尾款";
//    module
    public static final String DEVICERENT_MODULE="设备租赁合同";
    public static final String WORKRENT_MODULE="劳务派遣合同";


    private Integer id;
    private String serialnumber;
    private String type;
    private Float money;
    private String state;
    private String module;
    private String createdate;
    private String createuser;
    private String confirmuser;
    private String confirmdate;
    private String remark;
    private String rentserialnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getConfirmuser() {
        return confirmuser;
    }

    public void setConfirmuser(String confirmuser) {
        this.confirmuser = confirmuser;
    }

    public String getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRentserialnumber() {
        return rentserialnumber;
    }

    public void setRentserialnumber(String rentserialnumber) {
        this.rentserialnumber = rentserialnumber;
    }
}
