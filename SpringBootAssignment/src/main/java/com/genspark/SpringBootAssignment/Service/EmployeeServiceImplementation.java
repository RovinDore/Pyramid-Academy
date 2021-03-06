package com.genspark.SpringBootAssignment.Service;

import com.genspark.SpringBootAssignment.Dao.EmployeeDao;
import com.genspark.SpringBootAssignment.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmailService emailService;

//    public EmployeeServiceImplementation() {
//        this.employeeList = new ArrayList<>();
//        employeeList.add(new Employee(0, "John"));
//        employeeList.add(new Employee(1, "Jane"));
//        employeeList.add(new Employee(2, "Marry"));
//        employeeList.add(new Employee(3, "Jake"));
//    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeDao.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        Employee e = null;
        Optional<Employee> ee = this.employeeDao.findById(id);
        if(ee.isPresent()) e = ee.get();
        return e;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        this.emailService.sendMail(employee.getEmail(), "Profile Updated", "Your profile have been updated!");
        return this.employeeDao.save(employee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        this.emailService.sendMail(employee.getEmail(), "Your hired!", "Welcome to our company!");
        return this.employeeDao.save(employee);
    }

    @Override
    public String deleteEmployee(int id) {
        this.emailService.sendMail(getEmployee(id).getEmail(), "Fired!", "Your fired!");
        this.employeeDao.deleteById(id);
        return "Employee Deleted!";
    }
}
