package com.genspark.SpringBootAssignment.Service;

import com.genspark.SpringBootAssignment.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
    List<Employee> employeeList;

    public EmployeeServiceImplementation() {
        this.employeeList = new ArrayList<>();
        employeeList.add(new Employee(0, "John"));
        employeeList.add(new Employee(1, "Jane"));
        employeeList.add(new Employee(2, "Marry"));
        employeeList.add(new Employee(3, "Jake"));
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @Override
    public Employee getEmployee(int id) {
        Employee e = null;
        for(Employee person: employeeList){
            if(person.getEmployeeId() == id){
                e = person;
                break;
            }
        }
        return e;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee editThis = getEmployee(employee.getEmployeeId());
        editThis.setName(employee.getName());
        return editThis;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    @Override
    public String deleteEmployee(int id) {
        Employee removeThis = getEmployee(id);
        employeeList.remove(removeThis);
        return "Employee Deleted!";
    }
}
