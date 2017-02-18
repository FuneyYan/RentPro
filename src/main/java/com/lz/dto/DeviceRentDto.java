package com.lz.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class DeviceRentDto {


    /**
     * deviceArray : [{"id":"2","devicename":"挖掘机","unit":"辆","price":"200","num":"1","total":1400},{"id":"4","devicename":"小拖车","unit":"辆","price":"50","num":"1","total":350}]
     * fileArray : [{"newFileName":"0f89a5eb-7872-443d-abab-5224d508471e.jpeg","sourceName":"1.jpeg"}]
     * companyname : googel
     * tel : 222
     * linkman : james
     * cardnum : 232222
     * address : usa
     * fax : 43434
     * rentdate : 2017-02-18
     * backdate : 2017-02-25
     * totaldate : 7
     */

    private String companyname;
    private String tel;
    private String linkman;
    private String cardnum;
    private String address;
    private String fax;
    private String rentdate;
    private String backdate;
    private Integer totaldate;
    private List<DeviceArrayBean> deviceArray;
    private List<FileArrayBean> fileArray;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public Integer getTotaldate() {
        return totaldate;
    }

    public void setTotaldate(Integer totaldate) {
        this.totaldate = totaldate;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }

    public List<FileArrayBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<FileArrayBean> fileArray) {
        this.fileArray = fileArray;
    }

    @Override
    public String toString() {
        return "DeviceRentDto{" +
                "companyname='" + companyname + '\'' +
                ", tel='" + tel + '\'' +
                ", linkman='" + linkman + '\'' +
                ", cardnum='" + cardnum + '\'' +
                ", address='" + address + '\'' +
                ", fax='" + fax + '\'' +
                ", rentdate='" + rentdate + '\'' +
                ", backdate='" + backdate + '\'' +
                ", totaldate=" + totaldate +
                ", deviceArray=" + deviceArray +
                ", fileArray=" + fileArray +
                '}';
    }



    public static class DeviceArrayBean {
        /**
         * id : 2
         * devicename : 挖掘机
         * unit : 辆
         * price : 200
         * num : 1
         * total : 1400
         */

        private Integer id;
        private String devicename;
        private String unit;
        private Float price;
        private Integer num;
        private Float total;

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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return "DeviceArrayBean{" +
                    "id=" + id +
                    ", devicename='" + devicename + '\'' +
                    ", unit='" + unit + '\'' +
                    ", price=" + price +
                    ", num=" + num +
                    ", total=" + total +
                    '}';
        }
    }

    public static class FileArrayBean {
        /**
         * newFileName : 0f89a5eb-7872-443d-abab-5224d508471e.jpeg
         * sourceName : 1.jpeg
         */

        private String newFileName;
        private String sourceName;

        public String getNewFileName() {
            return newFileName;
        }

        public void setNewFileName(String newFileName) {
            this.newFileName = newFileName;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        @Override
        public String toString() {
            return "FileArrayBean{" +
                    "newFileName='" + newFileName + '\'' +
                    ", sourceName='" + sourceName + '\'' +
                    '}';
        }
    }
}
