package com.example.jpacustomerexam.repository.exam07;


import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.model.exam04.QDepartment;
import com.example.jpacustomerexam.model.exam04.QEmployee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//Impl 만 붙여서 클래스 만듬
public class Dept07RepositoryCustomImpl implements Dept07RepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

//    Querydsl 을 위한 Q 객체 가져오기
//    Querydsl 은  Q 객체로 쿼리를 사용함
    private QDepartment department = QDepartment.department;

    private QEmployee employee = QEmployee.employee;

//    dname like 검색하는 함수정의
//    체이닝 이용  .함수().함수()
    @Override
    public List<Department> querydslByDname(String dname){

        List<Department> list = queryFactory
                .selectFrom(department)
                .where(
                        department.dname.contains(dname)
                )
                .fetch();  // 마지막에 항상 실행(조회 실행)
        return list;
    }

//    dname & loc 로 like 검색하는 함수
//    select * from tb_department where dname like '%%' and loc like '%%' ;
//     querydsl 사용법:
//          queryFactory.select( DTO(컬럼1, 컬럼2,...)) == select 컬럼1, 컬럼2, ... DTO 에 담아서
//                      .selectFrom == select *
//                      .where (조건) ==where
//                      조건 -> department.속성.연산자(값)  == where 컬럼 연산자 ( in, like, =, > 등 )
//                      연산자() : in(), gt() == 보다크다 > , eq() 같다 , lt() 보다작다 <
//                      .orderBy() == order by
//                      .groupBy() == group by
//       나머지 구글 검색
    @Override
    public List<Department> querydslByDnameAndLoc(String dname, String loc) {
        List<Department> list = queryFactory.selectFrom(department)
                .where(
                        department.dname.contains(dname)
                                .and(department.loc.contains(loc))
                )
                .fetch();
        return list;
    }
}
