package com.example.jpacustomerexam.repository.exam07;

//Querydsl 용 Custom 인터페이스

import com.example.jpacustomerexam.model.exam04.Department;

import java.util.List;

public interface Dept07RepositoryCustom {

    List<Department> querydslByDname(String dname);

    List<Department> querydslByDnameAndLoc(String dname, String loc);
}
