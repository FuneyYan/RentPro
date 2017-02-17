package com.lz.pojo;

public class Device {
    private Integer id;
    private String name;
    private String unit;
    private Integer totalnum;
    private Integer currentnum;
    private Float price;


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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getCurrentnum() {
        return currentnum;
    }

    public void setCurrentnum(Integer currentnum) {
        this.currentnum = currentnum;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", totalnum=" + totalnum +
                ", currentnum=" + currentnum +
                ", price=" + price +
                '}';
    }
}
