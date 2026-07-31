/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.csm.model;
import jakarta.persistence.*;
@Entity @Table(name="csm_customer_portfolio") public class CustomerPortfolio extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String code; @Column(nullable=false,length=80) private String name;
    @Column(nullable=false,length=60) private String workshop; @Column(nullable=false) private int plannedCapacity; @Column(nullable=false) private boolean active=true;
    protected CustomerPortfolio(){} public CustomerPortfolio(String code,String name,String workshop,int plannedCapacity){this.code=code;this.name=name;this.workshop=workshop;this.plannedCapacity=plannedCapacity;}
    public String getCode(){return code;} public String getName(){return name;} public String getWorkshop(){return workshop;} public int getPlannedCapacity(){return plannedCapacity;} public boolean isActive(){return active;}
}
