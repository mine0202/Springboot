package com.example.jpacustomerexam.repository;

import com.example.jpacustomerexam.dto.EmpGroupDto;
import com.example.jpacustomerexam.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//##################################################################
//    exam @Query
//##################################################################

//    ename 으로 like 검색하는 함수를 정의
//     @Query 사용, native query 로 작성
@Query(value = "select e.* from tb_emp e where e.ename like %:ename%",nativeQuery = true)
    List<Emp> selectEname(@Param("ename") String ename);

//    salary 내림차순, ename 오름차순으로 정렬하는 함수를 작성하세요
    //     @Query 사용, native query 로 작성
//    쿼리 변수는 매개변수

    @Query(value = "select e.* from tb_emp e order by e.salary desc , e.ename asc",nativeQuery = true)
    List<Emp> selectDescAsc();


//    commission 이 null 이고 , salary가 매개변수값보다 같거나 큰 사원 정보를 출력
    //     @Query 사용, native query 로 작성
//    sql 문에 매개변수 사용을 위해서  :변수명 으로 입력
    @Query(value = "select e.* from tb_emp e where e.commission is null and e.salary>= :salary ",nativeQuery = true)
    List<Emp> selectCommissionSalary(@Param("salary") Integer salary);


//    1982년도에 입사한 사원 출력하는 함수를 정의
//    입사일은 내림차순 정렬
@Query(value = "select e.* from tb_emp e where e.hiredate like %:hiredate% ",nativeQuery = true)
    List<Emp> selectHiredate(@Param("hiredate") String hiredate);



//    부서별, 직위별 월 급여 합계를 출력하는 함수를 정의
//    단 dto만들어서 사용
@Query(value = "select e.dno, e.job, sum(e.salary) as sumSalary from tb_emp e group by e.dno, e.job order by e.dno",nativeQuery = true)
    List<EmpGroupDto> sumSalary();

// 부서별 평균 월급여를 출력하는 함수를 작성
//    단, 소수점은 절삭
@Query(value = "select e.dno," +
        " trunc(avg(e.salary)) as truncSal " +
        "from tb_emp e group by e.dno " +
        "order by e.dno",nativeQuery = true)
List<EmpGroupDto> truncSalary();

//    부서별 최고급여 중에서 3000이상인 사원만 조회하는 함수
@Query(value = "select e.dno, " +
        "max(e.salary) as maxSal " +
        "from tb_emp e " +
        "group by e.dno having max(e.salary) >= 3000",nativeQuery = true)
List<EmpGroupDto> maxSalary();


//    job 이 'MANAGER' 가 아니고 job 별 월급 합계가 5000 이상되는 사원의 정보를 출력하는 함수
//     단 월급여 합계로 오름차순 정렬
    @Query(value = "select  job, " +
        "sum(salary) as sumSal from tb_emp " +
        "group by job " +
        "having sum(salary) >=5000 " +
        "and job != 'MANAGER' " +
        "order by sum(salary) asc ",nativeQuery = true)
    List<EmpGroupDto> sumSalaryJob();

//    사원의 총수와 최고급여를 출력
    @Query(value = "select count(ename) as countVar, max(salary) as maxSal from tb_emp", nativeQuery = true)
    List<EmpGroupDto> selectCountMax();


//    사원테이블에서 가장 오래된 사원의 입사일과 가장 빠른 사원의 입사일을 출력
//    필요하면 DTO 를 이용
    @Query(value = "select min(hiredate) as Old , max(hiredate) as Young from tb_emp",nativeQuery = true)
    List<EmpGroupDto> selectOldYoung();

}
