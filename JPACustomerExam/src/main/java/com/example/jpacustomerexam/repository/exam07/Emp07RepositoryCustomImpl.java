package com.example.jpacustomerexam.repository.exam07;


import com.example.jpacustomerexam.model.exam04.Employee;
import com.example.jpacustomerexam.model.exam04.QDepartment;
import com.example.jpacustomerexam.model.exam04.QEmployee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//Impl 만 붙여서 클래스 만듬
public class Emp07RepositoryCustomImpl implements Emp07RepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

//    Querydsl 을 위한 Q 객체 가져오기
//    Querydsl 은  Q 객체로 쿼리를 사용함
    private QDepartment department = QDepartment.department;

    private QEmployee employee = QEmployee.employee;

    @Override
    public List<Employee> querydslByEname(String ename){

        List<Employee> list = queryFactory
                .selectFrom(employee)
                .where(
                        employee.ename.contains(ename)
                )
                .fetch();  // 마지막에 항상 실행(조회 실행)
        return list;
    }

//     사원명(ename) 과 직위(job) 를 매개변수로 받아 조히하는 함수를 정의하세요
    @Override
    public List<Employee> querydslByEnameAndJob(String ename, String job) {
        List<Employee> list = queryFactory.selectFrom(employee)
                .where(
                        employee.ename.contains(ename)
                                .and(employee.job.contains(job))
                ).fetch();
        return list;
    }
}
