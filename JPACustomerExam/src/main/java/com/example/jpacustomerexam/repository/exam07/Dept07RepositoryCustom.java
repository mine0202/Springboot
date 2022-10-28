package com.example.jpacustomerexam.repository.exam07;

//Querydsl 용 Custom 인터페이스

import com.example.jpacustomerexam.dto.querydsl.DeptGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Department;

import java.util.List;

public interface Dept07RepositoryCustom {

    List<Department> querydslByDname(String dname);

    List<Department> querydslByDnameAndLoc(String dname, String loc);

    List<DeptGroupQueryDto> querydslByGroupfunc();

    //    부서 번호가 20보다 큰 수를 가지는 부서 정보를 출력하는 함수
    List<Department> querydslByDeptGt(int dno);
}
