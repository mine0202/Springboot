package com.example.jpacustomerexam.repository.exam07;


import com.example.jpacustomerexam.dto.querydsl.DeptGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.model.exam04.QDepartment;
import com.example.jpacustomerexam.model.exam04.QEmployee;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

//    부서테이블의 부서번호를 sum, avg, max, min 값을 출력하는 예제

    @Override
    public List<DeptGroupQueryDto> querydslByGroupfunc(){
        List<DeptGroupQueryDto> list = queryFactory.select(
//                DTO 클래스를 이용 , 쿼리dsl은 클래스DTO 를 사용, native쿼리는 DTO 인터페이스를 이용
//                속성 몇개만 출력하고 싶을때 , 가공된 데이터를 출력하고 싶을때 사용
//                1) DTO 를 생성자를 이용해서 속성에 저장 출력하는 방법
//                2) 있는 속성을 이용해서 출력하는 방법 : querydsl 에서 제공 Projection.fields(DTO객체, 속성들..) 를 이용
                        Projections.fields(DeptGroupQueryDto.class ,
                                            department.dno.count().as("countVar"),
                                            department.dno.sum().as("sumVar"),
                                            department.dno.avg().as("avgVar"),
                                            department.dno.max().as("maxVar"),
                                            department.dno.min().as("minVar")
                                )
                ).from(department)
                .fetch();
        return list;
    }


//    부서 번호가 20보다 큰 수를 가지는 부서 정보를 출력하는 함수
    @Override
    public List<Department> querydslByDeptGt(int dno){

        List<Department> list = queryFactory
                .selectFrom(department)
                .where(
                        department.dno.gt(dno)  // 보다 큰 gteat than  : gt  /  보다 작은 less than : lt
                )
                .fetch();  // 마지막에 항상 실행(조회 실행)
        return list;
    }
}
