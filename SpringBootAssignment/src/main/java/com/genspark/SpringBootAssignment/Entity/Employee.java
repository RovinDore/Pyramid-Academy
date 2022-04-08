package com.genspark.SpringBootAssignment.Entity;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String name;

    public Employee() {
    }

    
    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
