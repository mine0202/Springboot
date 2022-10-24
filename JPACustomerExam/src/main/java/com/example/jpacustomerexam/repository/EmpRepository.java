package com.example.jpacustomerexam.repository;

import com.example.jpacustomerexam.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Emp,Integer> {
//    JPQL 함수를 사용할 수 있음 : 응용
//    @Query 쿼리메소드

//    쿼리메소드 연습
//    Eno 속성(컬럼)에 대해서 내림차순으로 전체조회
    List<Emp> findAllByOrderByEnoDesc();

//    Eno 오름차순으로 전체 조회
    List<Emp> findAllByOrderByEnoAsc();

    List<Emp> findAllByOrderByEnameAsc();

//     ename like 검색하여 ename 으로 내림차순 조회
//     select * from emp where ename like'%매개변수%' order by ename desc;
     List<Emp> findAllByEnameContainingOrderByEnameDesc(String ename);


//        emp  테이블에서 job 에 like 검색을 추가하고 job 내림차순으로 정렬하는 함수를 작성하세요
//    select *  from tb_emp where job like '%매개변수%' order by job desc;
    List<Emp> findAllByJobContainingOrderByJobDesc(String job);

//      emp 테이블에서 job이 manager 이고 매개변수로 부서번호 dno 를 전달 받는 함수를 작성하세요
//    select * from tb_emp where job = 'MANAGER' and dno = 20;
    List<Emp> findAllByJobAndDno(String job, Integer dno);

//    emp 테이블에서 salary 가 1000 ~ 1500  사이의 값을 같는 사원을 조회하기위한 함수를 작성
//     Between 사용 , ename 으로 오름차순 정렬
//    select * from tb_emp where salary between 1000 and 1500;
    List<Emp> findAllBySalaryBetweenOrderByEname(int start, int end);

//        Emp 테이블 에서 job 을 매개변수로 받는 함수를 작성해 주세요
//    단, Job 의 매개변수값은 대문자 또는 소문자가 들어올수 있음
//    select * from tb_emp where job = upper('manager');
    List<Emp> findAllByJobIgnoreCase(String manager);


//    commission 을 받는 사원을 모두 출력하는 함수를 작성해 보세요
//    select * from tb_emp where commission is not null;
    List<Emp> findAllByCommissionNotNull();

//     급여는 내림차순, 사원명은 오름차순으로 정렬하는 함수를 작성
//    select * from tb_emp order by salary desc, ename asc;
    List<Emp> findAllByOrderBySalaryDescEnameAsc();

//  salary <1000  또는 1500 초과 조건을 만족하는 사원을 출력하는 함수를 작성하세요
//  and , or , lessthan ( < ) , greaterthan ( > )
//    select * from tb_emp where salary <1000 or salary >1500;
//    select * from tb_emp where salary not between 1000 and 1500;
    List<Emp> findAllBySalaryLessThanOrSalaryGreaterThan(int less, int great);

//   commission 이 300 이거나 500 이거나 1500 인 사원을 출력하는 함수를 작성

    List<Emp> findAllByCommissionIsOrCommissionIsOrCommissionIs(int a,int b ,int c);


}
