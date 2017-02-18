package com.lz.pojo;

public class DeviceRentDoc {

    private Integer id;
    private String sourcename;
    private String filename;
    private Integer rentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getRentid() {
        return rentid;
    }

    public void setRentid(Integer rentid) {
        this.rentid = rentid;
    }

    @Override
    public String toString() {
        return "DeviceRentDoc{" +
                "id=" + id +
                ", sourcename='" + sourcename + '\'' +
                ", filename='" + filename + '\'' +
                ", rentid=" + rentid +
                '}';
    }
}
