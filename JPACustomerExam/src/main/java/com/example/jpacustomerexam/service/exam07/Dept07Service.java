package com.example.jpacustomerexam.service.exam07;

import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.repository.exam07.Dept07Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dept07Service {


    @Autowired
    Dept07Repository deptRepository;

    public List<Department> querydslByDname(String dname){
        List<Department> list = deptRepository.querydslByDname(dname);
        return list;
    }

    public List<Department> querydslByDnameAndLoc(String dname, String loc){
        List<Department> list = deptRepository.querydslByDnameAndLoc(dname, loc);
        return list;
    }

}
