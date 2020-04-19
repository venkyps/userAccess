package com.user.access.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "EMPID")
    private String  empID;

    @Column(name = "ACCESSKEY", nullable = false)
    private String  accessKey;

    @Column(name = "COUNTRY", nullable = false)
    private String  country;

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

}
