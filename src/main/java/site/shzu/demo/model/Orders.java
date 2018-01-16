package site.shzu.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Orders {
    private Integer id;

    private String name;

    private String address;

    private Double fee;

    @JSONField(format="yyyy-MM-dd")
    private Date dates;

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
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}