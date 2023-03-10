package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl {
    @Autowired
    EmployeeDaoImpl employeeDaoImpl;
    public Employee signUp(Employee employee){
        return employeeDaoImpl.signUp(employee);
    }
    public boolean signIn(String empEmailId,String empPassword){
        boolean flag=false;
        for(Employee employee:getAllData()){
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag=true;
            }
        }
        return flag;
    }
    @Cacheable(value = "empId")
    public Optional<Employee> getDataById(int empId){
        log.info("fetching Employee Data from DB");
        return employeeDaoImpl.getDataById(empId);
    }
    public List<Employee> getAllData(){
        return employeeDaoImpl.getAllData();
    }
    public Employee updateData(Employee employee){
        return employeeDaoImpl.updateData(employee);
    }
    public void deleteDataById(int empId){
        employeeDaoImpl.deleteDataById(empId);
    }
    public  void deleteAllData(){
        employeeDaoImpl.deleteAllData();
    }
}


