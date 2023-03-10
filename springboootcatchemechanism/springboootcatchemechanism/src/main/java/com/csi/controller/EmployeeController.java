package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// By Priyanka
@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee>signUp(@RequestBody Employee employee ){
        log .info("#######Trying to signUp  for Employee:"+employee.getEmpName());
        return  ResponseEntity.ok(employeeServiceImpl.signUp(employee));

    }
    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean>signIn(@PathVariable String empEmailId,@PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId,empPassword));

    }
    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyid/{empId}")
    public  ResponseEntity<Optional<Employee>>getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));

    }
    @PutMapping("/updatedata/{empId}")
    public  ResponseEntity<Employee>updateData(@PathVariable int empId,@RequestBody Employee employee){
        Employee employee1=employeeServiceImpl.getDataById(empId).orElseThrow(()->  new RecordNotFoundException("Id Does Not Exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return ResponseEntity.ok(employeeServiceImpl.updateData(employee1));
    }
    @DeleteMapping("/deletedatabyid/{empId}")
    public  ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }
    @DeleteMapping("/deletealldata")
    public  ResponseEntity<String>deleteAllData(){
   employeeServiceImpl.deleteAllData();
   return ResponseEntity.ok("All Data Deleted Successfully");
    }
}
