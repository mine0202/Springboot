package com.example.jpacustomerexam.repository.exam07;

//Querydsl 용 Custom 인터페이스

import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.model.exam04.Employee;

import java.util.List;

public interface Emp07RepositoryCustom {

    List<Employee> querydslByEname(String ename);
    List<Employee> querydslByEnameAndJob(String ename, String job);

}
