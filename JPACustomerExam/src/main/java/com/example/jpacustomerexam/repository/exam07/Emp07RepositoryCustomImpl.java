package com.example.jpacustomerexam.repository.exam07;


import com.example.jpacustomerexam.dto.querydsl.EmpGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Employee;
import com.example.jpacustomerexam.model.exam04.QDepartment;
import com.example.jpacustomerexam.model.exam04.QEmployee;
import com.querydsl.core.types.Projections;
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

//    사원테이블의 count, sum, avg , max, min 값을 출력하세요
//     EmpGroupQDTO 만들어서 위의 속성값들을 출력하는 함수를 만드세요.'
    @Override
    public List<EmpGroupQueryDto> querydslBySalary() {
        List<EmpGroupQueryDto> list = queryFactory.select(
                        Projections.fields(
                                EmpGroupQueryDto.class,
                                employee.salary.count().as("countVar"),
                                employee.salary.sum().as("sumVar"),
                                employee.salary.avg().as("avgVar"),
                                employee.salary.max().as("maxVar"),
                                employee.salary.min().as("minVar")
                        )
                ).from(employee)
                .fetch();
        return list;
    }

//    커미션이 500이상인 사원정보를 출력하는 함수를 작성하세요
    @Override
    public List<Employee> querydslByCommission(int commission) {
        List<Employee> list = queryFactory.selectFrom(employee)
                .where(
                        employee.commission.gt(commission)

                ).fetch();
        return list;
    }

    @Override
    public List<Employee> querydslByHiredate(String start, String end) {
        List<Employee> list = queryFactory.selectFrom(employee)
                .where(
                        employee.hiredate.between(start, end)
                ).fetch();
        return list;
    }

//    dno, job 별 월급여를 출력하는 함수
//    힌트, groupBy(속성1, 속성2...)  employee 와 department 가 join 되어있으므로
//     demployee.department.dno 를 부르면됨
    @Override
    public List<EmpGroupQueryDto> querydslByDnoJobSalary() {
        List<EmpGroupQueryDto> list = queryFactory.select(
                        Projections.fields(
                                EmpGroupQueryDto.class,
                                department.dno.as("depDno"),
                                employee.job.as("job"),
                                employee.salary.sum().as("sumVar")
                        )
                ).from(employee)
                .groupBy(employee.department.dno, employee.job)
                .orderBy(department.dno.asc())
                .fetch();
        return list;
    }

}
