package com.lz.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class WorkRentDto {


    /**
     * workArray : [{"id":"1","name":"挖掘工","price":200,"num":2,"totalprice":200,"total":400},{"id":"2","name":"铲车工","price":150,"num":1,"totalprice":150}]
     * fileArray : [{"newFileName":"6a5b953e-049d-4766-bbeb-27e54a8e560b.jpeg","sourceName":"1.jpeg"},{"newFileName":"4085670d-4f3f-4c27-86e4-b0c689e382a7.jpeg","sourceName":"2.jpeg"}]
     * companyName : google
     * tel : 8989
     * companyTel : 8899
     * linkMan : jamse
     * cardNum : 767666
     * address : jz
     * brokerage : 550
     * preCost : 165
     * lastCost : 385
     */

    private String companyName;
    private String tel;
    private String companyTel;
    private String linkMan;
    private String cardNum;
    private String address;
    private Float brokerage;
    private Float preCost;
    private Float lastCost;
    private List<WorkArrayBean> workArray;
    private List<FileArrayBean> fileArray;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(Float brokerage) {
        this.brokerage = brokerage;
    }

    public Float getPreCost() {
        return preCost;
    }

    public void setPreCost(Float preCost) {
        this.preCost = preCost;
    }

    public Float getLastCost() {
        return lastCost;
    }

    public void setLastCost(Float lastCost) {
        this.lastCost = lastCost;
    }

    public List<WorkArrayBean> getWorkArray() {
        return workArray;
    }

    public void setWorkArray(List<WorkArrayBean> workArray) {
        this.workArray = workArray;
    }

    public List<FileArrayBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<FileArrayBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class WorkArrayBean {
        /**
         * id : 1
         * name : 挖掘工
         * price : 200
         * num : 2
         * totalprice : 200
         * total : 400
         */

        private Integer id;
        private String name;
        private Float price;
        private int num;
        private Float totalprice;
        private Float total;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Float getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(Float totalprice) {
            this.totalprice = totalprice;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }
    }

    public static class FileArrayBean {
        /**
         * newFileName : 6a5b953e-049d-4766-bbeb-27e54a8e560b.jpeg
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
    }
}
