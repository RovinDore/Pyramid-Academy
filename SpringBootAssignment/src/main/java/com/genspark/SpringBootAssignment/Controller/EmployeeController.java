package com.genspark.SpringBootAssignment.Controller;

import com.genspark.SpringBootAssignment.Entity.Employee;
import com.genspark.SpringBootAssignment.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService person;

    @GetMapping("/")
    public String home(@RequestParam(value = "name", defaultValue = "Hello") String name){
        return  "<html><h1>Hello World</hi></html>";
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return this.person.getEmployees();
    }

    @GetMapping("/employees/{empID}")
    public Employee getEmployees(@PathVariable String empID){
        return this.person.getEmployee(Integer.parseInt(empID));
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee person){
        return this.person.addEmployee(person);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee person){
        return this.person.updateEmployee(person);
    }

    @DeleteMapping("/employees/{empID}")
    public String deleteEmployee(@PathVariable String empID){
        return this.person.deleteEmployee(Integer.parseInt(empID));
    }
}
