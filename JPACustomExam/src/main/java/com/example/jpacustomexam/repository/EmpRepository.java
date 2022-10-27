package com.example.jpacustomexam.repository;

import com.example.jpacustomexam.dto.EmpGroupDto;
import com.example.jpacustomexam.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.example.jpaexam.repository
 * fileName : EmpRepository
 * author : ds
 * date : 2022-10-20
 * description : Emp 리파지토리(== DAO ) CRUD 용 함수가 있는 인터페이스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
    //    JPQl 함수를 사용할 수 있음 : 응용
//    @Query, 쿼리메소드
//    쿼리 메소드 연습문제 :
//    1) Eno 속성(컬럼)에 대해서 내림차순으로 전체 조회
    List<Emp> findAllByOrderByEnoDesc();

    //    2) ename like 검색하여 ename으로 내림차순 조회
//    select * from tb_emp where ename like '%매개변수%' order by ename desc;
    List<Emp> findAllByEnameContainingOrderByEnameDesc(String ename);

    //    3) emp 테이블에서 job 에 like 검색을 추가하고 job 내림차순으로 정렬하는 함수를 작성하세요.
//    select * from tb_emp where job lik '%매개변수%' order by job desc;
    List<Emp> findAllByJobContainingOrderByJobDesc(String job);

    //    4) emp 테이블에서 job 이 manager 이고 매개변수로 부서번호(dno) 를 전달받는 함수를 작성하세요.
//    select * from tb_emp where job = 'MANAGER' and dno = 20;
    List<Emp> findAllByJobAndDno(String job, int dno);

    //    5) Emp 테이블에서 salary 가 1000 ~ 1500 사이의 값을 같는 사원을 조회하려고 합니다.
//    함수를 작성해 주세요. ( Between 사용 )
//    select * from tb_emp where salary between 1000 and 1500;
    List<Emp> findAllBySalaryBetweenOrderByEname(int first, int last);

    //    6) Emp 테이블에서 Job 을 매개변수로 받는 함수를 작성해 주세요 .
//    단, Job 의 매개변수값은 대문자 또는 소문자가 들어올 수 있음
//    select * from tb_emp where job = upper('manager');
    List<Emp> findAllByJobIgnoreCase(String job);

    //    7) COMMISSION 을 받을 수 있는 자격이 되는 사원을 모두 출력하는 함수를 작성해 보세요.
//    SELECT * FROM TB_EMP WHERE COMMISSION IS NOT NULL;
    List<Emp> findAllByCommissionIsNotNull();

    //    8) 급여(SALARY) 는 내림차순, 사원명(ENAME) 은 오름차순으로 정렬하는 함수를 작성해 보세요
//    SELECT * FROM TB_EMP ORDER BY SALARY DESC, ENAME ASC;
    List<Emp> findAllByOrderBySalaryDescEnameAsc();

    //    9) salary < 1000 또는 salary > 1500 이 조건을 만족하는 사원을 출력하는 함수를 작성하세요.
//    힌트) Or , LessThan ( < ), GreaterThan ( > )
//    SELECT * FROM TB_EMP WHERE SALARY < 1000 OR SALARY > 1500;
//    SELECT * FROM TB_EMP WHERE SALARY NOT BETWEEN 1000 AND 1500;
    List<Emp> findAllBySalaryLessThanOrSalaryGreaterThan(int first, int last);

    //    10) commissition 이 300 이거나 500 이거나 1500 인 사원을 출력하는 함수를 작성하세요.
    List<Emp> findAllByCommissionOrCommissionOrCommission(int first, int second, int third);

    //    ######################################################################################
//    exam02 : @Query
//    ######################################################################################
//    1) ename like 검색하는 함수를 정의하세요.
//    단, @Query 사용하세요, native 쿼리로 작성하세요.
//    쿼리문 :변수 -> 매개변수
    @Query(value = "select e.*\n" +
            "from tb_emp e\n" +
            "where e.ename like %:ename%"
            , nativeQuery = true)
    List<Emp> selectByEname(@Param("ename") String ename);

    //    2) salary 내림차순, ename 오름차순으로 정렬하는 함수를 작성하세요.
    //    단, @Query 사용하세요, native 쿼리로 작성하세요.
    @Query(value = "select e.* " +
            "from tb_emp e " +
            "order by e.salary desc " +
            "       ,e.ename asc"
            , nativeQuery = true)
    List<Emp> selectDescAsc();

    //    3) commission 이 null 이고 , salary 가  매개변수값보다 같거나 큰 사원 정보를 출력하세요.
    //    단, @Query 사용하세요, native 쿼리로 작성하세요.
//    sql 문에 매개변수 사용 -> :변수명
    @Query(value = "select e.* " +
            "from tb_emp e " +
            "where commission is null " +
            "and salary >= :salary "
            , nativeQuery = true)
    List<Emp> selectSalary(@Param("salary") int salary);

    //    4) 1982년도에 입사한 사원 출력하는 함수를 정의하세요.
//    단, 입사일은(hiredate) 내림차순 하세요.
    @Query(value = "select e.* " +
            "from tb_emp e " +
            "where hiredate between :first and :last "
            , nativeQuery = true)
    List<Emp> selectHiredate(@Param("first") String first,
                             @Param("last") String last);

    //    5) 부서별(dno), 직위별(job) 월급여 합계를 출력하는 함수를 정의하세요.
//    단, dto 만들어서 사용하세요
    @Query(value = "select e.dno, e.job, sum(e.salary) as sumSalary  " +
            "from tb_emp e  " +
            "group by e.dno  " +
            "       ,e.job"
            , nativeQuery = true)
    List<EmpGroupDto> selectGroupDnoJob();

    //    6) 부서별 평균 월급여를 출력하는 함수를 작성하세요.
//    단, 소수점은 절삭하세요.
    @Query(value = "select e.dno, trunc(avg(e.salary)) as avgSalary  " +
            "from tb_emp e  " +
            "group by e.dno  "
            , nativeQuery = true)
    List<EmpGroupDto> selectGroupDnoTrunc();

//    7) 부서별 최고급여 중에서 3000 이상인 사원만 조회하는 함수를 정의하세요.
    @Query(value = "select dno, max(salary) as maxSalary   " +
            "from tb_emp   " +
            "group by dno   " +
            "having max(salary) >= 3000"
            ,nativeQuery = true)
    List<EmpGroupDto> selectGroupDnoMax();

//    8) job 이 'MANAGER' 가 아니고  job 별 월급여 합계가 5000 이상 되는
//    사원의 정보를 출력하는 함수를 정의하세요.
//    단, 월급여 합계로 오름차순 정렬하세요.
// 필요하면 DTO 를 이용하세요.
    @Query(value = "select job, sum(salary) as sumSalary " +
            "from tb_emp " +
            "where job not like '%MANAGER%'  " +
            "group by job " +
            "having sum(salary) >= 5000 " +
            "order by sum(salary) "
    ,nativeQuery = true)
    List<EmpGroupDto> selectGroupJobHaving();

//    9) 사원의 총수와(count) 최고급여를(max0 출력하세요.
    // 필요하면 DTO 를 이용하세요.
    @Query(value = "select count(e.eno) as countEno  " +
            "      ,max(e.salary) as salaryEno  " +
            "from tb_emp e"
            ,nativeQuery = true)
    List<EmpGroupDto> selectGroupSumMax();


//    10) 사원 테이블에서 가장 오래된 사원의 입사일과 가장 빠른 사원의 입사일을 출력하세요.
// 필요하면 DTO 를 이용하세요.
    @Query(value = "select max(hiredate) as maxHiredate " +
            "      ,min(hiredate) as minHiredate " +
            "from tb_emp"
    ,nativeQuery = true)
    List<EmpGroupDto> selectGroupHiredate();
}









