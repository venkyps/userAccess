package com.user.access.entity;
import javax.persistence.*;

@Entity
@Table(name = "TEAMHIERARCHY")
public class TeamHierarchy {

    @Id
    @Column(name = "EMPID")
    private String  empID;

    @Column(name = "MANAGERID", nullable = false)
    private String  managerID;

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
}
