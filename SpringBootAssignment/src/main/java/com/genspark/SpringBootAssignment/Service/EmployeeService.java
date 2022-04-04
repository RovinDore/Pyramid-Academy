package com.genspark.SpringBootAssignment.Service;

import com.genspark.SpringBootAssignment.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee getEmployee(int id);
    Employee updateEmployee(Employee employee);
    Employee addEmployee(Employee employee);
    String deleteEmployee(int id);
}
