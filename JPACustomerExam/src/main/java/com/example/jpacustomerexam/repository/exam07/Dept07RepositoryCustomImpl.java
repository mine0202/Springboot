package com.example.jpacustomerexam.repository.exam07;


import com.example.jpacustomerexam.dto.DeptEmpClassDto;
import com.example.jpacustomerexam.dto.querydsl.DeptGroupQueryDto;
import com.example.jpacustomerexam.model.QDept;
import com.example.jpacustomerexam.model.QEmp;
import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.model.exam04.QDepartment;
import com.example.jpacustomerexam.model.exam04.QEmployee;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
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
//    sql 기본내장함수를 사용하는 함수를 작성하세요
//    대소문자 바꾸기 : UPPER()/ LOWER()
//    문자열 자르기 : SUBSTR()

//    DTO 를 사용
//     Querydsl DTO 사용법:
//          1) 속성에 바로 저장 : Projections.fields(DTO 클래스, 속성...)  , .as("DTO 에서 속성명 ")
//          2) 생성자를 이용해 저장 : new DTO클래스( 속성... )

//    substr ( 인덱스번호, 길이 )
//    substring(처음인덱스번호, 끝인덱스번호)
    @Override
        public List<DeptGroupQueryDto> querydslByBasicFunc(){

        List<DeptGroupQueryDto> list = queryFactory
                .select(
                        Projections.fields(
                                DeptGroupQueryDto.class,
                                department.dname.upper().as("upperVar"),
                                department.dname.lower().as("lowerVar"),
                                department.dname.substring(1,2).as("substrVar")
                        )
                )
                .from(department)
                .fetch();
        return list;
    }

//    case when 예제
    @Override
    public List<DeptGroupQueryDto> querydslByCasewhen(){

        List<DeptGroupQueryDto> list = queryFactory
                .select(
                        Projections.fields(
                                DeptGroupQueryDto.class,
                                department.dno,
//                                case when  부분 작성 :new CaseBuilder().when(조건식1).then(실행1)
//                                                                   .when(조건식2).then(실행2)
//                                                                   .otherwise(실행3).as(별칭)
                                new CaseBuilder().when(department.dno.lt(20)).then("인센티브 100%")
                                            .when(department.dno.gt(20)).then("인센티브 200%")
                                            .otherwise("인센티브 없음").as("caseString")
                        )
                )
                .from(department)
                .fetch();
        return list;
    }

//    그룹함수를 이용 group by , 부서번호가 10,20에 해당하는 부서번호별 총수를 출력
//    querydsl  경우 count() return 값으 자료형은 long 을 사용
    @Override
    public List<DeptGroupQueryDto> querydslByDnoGroup(long countDno){


        List<DeptGroupQueryDto> list = queryFactory
                .select(
                        Projections.fields(
                                DeptGroupQueryDto.class,
                                department.dno.as("dno"),
                                department.dno.count().as("dnoCount")
                        )
                )
                .from(department)
                .where(
                        department.dno.in(10,20)
                )
                .groupBy(department.dno)
//                아래 참고
//                select dno, count(dno)
//                from tb_department
//                where dno in (10,20)
//                group by dno
//                having count(dno) = 10;
                .having(department.dno.count().eq(countDno))
                .fetch();  // 마지막에 항상 실행(조회 실행)
        return list;

    }

//    join : equal join 과  leftJoin 같다
//      select e.eno , e.ename, e.job , d.dname , d.loc
//      from tb_employee e, tb_department d
//      where e.dno = d.dno
//      and d.dname like '%%';
    @Override
    public List<DeptEmpClassDto> querydslByDnameJoin(String dname){

        List<DeptEmpClassDto> list = queryFactory
                .select(
                        Projections.fields(
                                DeptEmpClassDto.class,
                                employee.eno.as("eno"),
                                employee.ename.as("ename"),
                                employee.job.as("job"),
                                employee.hiredate.as("hiredate"),
                                employee.salary.as("salary"),
                                employee.department.dname.as("dname"),
                                employee.department.loc.as("loc")
                        )
                )
                .from(employee)
//                leftJoin( employee 의 @ManyToOne 이 달린 속성명, 조인될 객체명)
                .leftJoin(employee.department, department)
                .where(
                        department.dname.contains(dname)
                )
                .fetch();
        return list;
    }

// 연관 관계 없는 조인 ( 모델에 @ManyToOne, @OneToMany  가 없는 조인)
//
//    Querydsl 을 위한 Q 객체 가져오기
//    Querydsl 은  Q 객체로 쿼리를 사용함
    private QDept dept = QDept.dept;

    private QEmp emp = QEmp.emp;


    @Override
    public List<DeptEmpClassDto> querydslByDnameJoin2(String dname){

        List<DeptEmpClassDto> list = queryFactory
                .select(
                        Projections.fields(
                                DeptEmpClassDto.class,
                                emp.eno.as("eno"),
                                emp.ename.as("ename"),
                                emp.job.as("job"),
                                emp.hiredate.as("hiredate"),
                                emp.salary.as("salary"),
                                dept.dname.as("dname"),
                                dept.loc.as("loc")
                        )
                )
                .from(emp)
//                .join(조인객체명).on(이퀄조인조건: 객체.공통속성.eq(객체2.공통속성명))
                .join(dept).on(emp.dno.eq(dept.dno))
                .where(
                        dept.dname.contains(dname)
                )
                .fetch();
        return list;
    }

//    서브쿼리
//    sql 문
//    select * from tb_dept
//    where dno <= ( select avg(dno) from tb_dept);


    @Override
    public List<Department> querydslByDnoSub(){

//  subquery 를 위한 Q 객체 따로 생성
//        사용법  : Q클래스명 객체명 - new Q생성자(별명);
//                 JPAExpressions.select() 함수 호출

        QDepartment subDept = new QDepartment("subDept");

        List<Department> list = queryFactory
                .selectFrom(department)
                .where(
                        department.dno.loe(
                                JPAExpressions.select(subDept.dno.avg())
                                        .from(subDept)
                        )
                )
                .fetch();
        return list;
    }

}
