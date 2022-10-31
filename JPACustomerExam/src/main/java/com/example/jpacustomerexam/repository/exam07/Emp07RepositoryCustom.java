package com.example.jpacustomerexam.repository.exam07;

//Querydsl 용 Custom 인터페이스

import com.example.jpacustomerexam.dto.querydsl.EmpGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.model.exam04.Employee;

import java.util.List;

public interface Emp07RepositoryCustom {

    List<Employee> querydslByEname(String ename);
    List<Employee> querydslByEnameAndJob(String ename, String job);

    //    사원테이블의 count, sum, avg , max, min 값을 출력하세요
    //     EmpGroupQDTO 만들어서 위의 속성값들을 출력하는 함수를 만드세요.'
    List<EmpGroupQueryDto> querydslBySalary();

    //    커미션이 500이상인 사원정보를 출력하는 함수를 작성하세요
    List<Employee> querydslByCommission(int commission);

    //    1982년도에 입사한 사원 출력하기
    List<Employee> querydslByHiredate(String start, String end);

    List<EmpGroupQueryDto> querydslByDnoJobSalary();

}
