package com.example.jpacustomerexam.service.exam07;

import com.example.jpacustomerexam.dto.querydsl.EmpGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Employee;
import com.example.jpacustomerexam.repository.exam07.Emp07Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Emp07Service {

    @Autowired
    Emp07Repository empRepository;

    public List<Employee> querydslByEname(String ename){
        List<Employee> list = empRepository.querydslByEname(ename);
        return list;
    }

    public List<Employee> querydslByEnameAndJob(String ename, String job){
        List<Employee> list = empRepository.querydslByEnameAndJob(ename, job);
        return list;
    }

    public List<EmpGroupQueryDto> querydslBySalary(){
        List<EmpGroupQueryDto> list = empRepository.querydslBySalary();
        return list;
    }


    public List<Employee> querydslByCommission(int commission){
        List<Employee> list = empRepository.querydslByCommission(commission);
        return list;
    }
    public List<Employee> querydslByHiredate(String start, String end){
        List<Employee> list = empRepository.querydslByHiredate(start, end);
        return list;
    }
}
