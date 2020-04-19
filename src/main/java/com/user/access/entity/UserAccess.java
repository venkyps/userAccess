package com.user.access.entity;

import javax.persistence.*;

@Entity
@Table(name = "USERACCESS")
public class UserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMPID")
    private String  empID;

    @Column(name = "ACCESSKEY")
    private String  accessKey;

    @Column(name = "COUNTRY")
    private String  country;

    @Column(name = "SUBUSER")
    private String  subuser;

    @Column(name = "SUBUSERACCESSKEY")
    private String  subUserAccessKey;

    @Column(name = "SUBUSERCOUNTRY")
    private String  subUsercountry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubuser() {
        return subuser;
    }

    public void setSubuser(String subuser) {
        this.subuser = subuser;
    }

    public String getSubUserAccessKey() {
        return subUserAccessKey;
    }

    public void setSubUserAccessKey(String subUserAccessKey) {
        this.subUserAccessKey = subUserAccessKey;
    }

    public String getSubUsercountry() {
        return subUsercountry;
    }

    public void setSubUsercountry(String subUsercountry) {
        this.subUsercountry = subUsercountry;
    }
}
