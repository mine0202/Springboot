package com.example.jpacustomexam.repository.exam03;

import com.example.jpacustomexam.dto.EmpGroupDto;
import com.example.jpacustomexam.model.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface Emp03Repository extends JpaRepository<Emp, Integer> {

//    쿼리메소드를 이용하세요.
//    1) Emp 테이블을 전체 조회하는 findAll() 함수에 페이징 처리 로직을 추가하세요.( 기본 함수 제공 )

    //    2) ename 으로 내림차순 정렬해서 전체 사원 정보를 출력하는 함수를 정의하세요.
//    단, 페이징 처리 로직을 추가하세요.
    Page<Emp> findAllByOrderByEnameDesc(Pageable pageable);

    //    3) salary 가 1000 ~ 1500 사이이고 Commission 이 not null 일 때 함수를 정의하세요.
//    단, 페이징 처리 로직을 추가하세요.
    Page<Emp> findAllBySalaryBetweenAndCommissionIsNotNull(int first,
                                                           int last,
                                                           Pageable pageable
    );

    //    4) ename like 검색하고 결과를 페이징하는 함수를 작성하세요.
    //    단, 페이징 처리 로직을 추가하세요.
    Page<Emp> findAllByEnameContaining(String ename, Pageable pageable);

    //    5) 10번 부서에 해당하고 salary 가 1000 이상인 결과를 조회하는 함수를 작성하세요.
    //    단, 페이징 처리 로직을 추가하세요.
    Page<Emp> findAllByDnoAndSalaryGreaterThanEqual(int dno, int salary, Pageable pageable);

    //   @Query 작성하세요.
//    문제 6) 급여가 2000 ~ 3000 사이에 포함되지 않는 사원을 출력하는 함수를 작성하세요.
//        //    단, 페이징 처리 로직을 추가하세요.
//    nativeQuery = true 일 경우 (일반 sql문 작성) countQuery 필요(페이징 추가하려면)
    @Query(value = "select e.*  " +
            "from tb_emp e  " +
            "where e.salary not between :first and :last"
            , countQuery = "select count(*)  " +
            "from tb_emp e  " +
            "where e.salary not between :first and :last"
            , nativeQuery = true
    )
    Page<Emp> selectBySalaryPage(@Param("first") int first,
                                 @Param("last") int last,
                                 Pageable pageable);

    //    문제 7) 1981년 2월 20일 부터 1981년 5월 1일 사이에 입사한 사원을 출력하는 함수를 작성하세요.
    //        //    단, 페이징 처리 로직을 추가하세요.
    @Query(value = "select e.*  " +
            "from tb_emp e  " +
            "where e.hiredate between :first and :last"
            , countQuery = "select count(*)  " +
            "from tb_emp e  " +
            "where e.hiredate between :first and :last"
            , nativeQuery = true
    )
    Page<Emp> selectByHireDatePage(@Param("first") String first,
                                   @Param("last") String last,
                                   Pageable pageable);

    //    문제 8) 월급여가 1000 ~ 3000 사이에 사원중 부서번호 10, 20번인 사원을 출력하는 함수를 작성하세요.
//    매개변수를 이용하세요.
//    힌트 : where 절 between ~ and , in (값1, 값2...)
//    단, 페이징 처리 하세요.
    @Query(value = "select e.*   " +
            "from tb_emp e   " +
            "where e.salary between :first and :last   " +
            "and e.dno in (:firstDno, :lastDno)"
            , countQuery = "select count(*)   " +
            "from tb_emp e   " +
            "where e.salary between :first and :last   " +
            "and e.dno in (:firstDno, :lastDno)"
            , nativeQuery = true
    )
    Page<Emp> selectBySalaryDnoPage(@Param("first") int first,
                                    @Param("last") int last,
                                    @Param("firstDno") int firstDno,
                                    @Param("lastDno") int lastDno,
                                    Pageable pageable
    );


    //    문제 9) 1981년에 입사한 사원을 출력하세요.
//    단 페이징 처리 하세요.
    @Query(value = "select e.*    " +
            "from tb_emp e    " +
            "where e.hiredate like %:hiredate%"
            ,countQuery = "select count(*)    " +
            "from tb_emp e    " +
            "where e.hiredate like %:hiredate%"
            , nativeQuery = true
    )
    Page<Emp> selectByHiredateContainingPage(@Param("hiredate") String hiredate,
                                             Pageable pageable
    );


//    문제 10) 커미션(Commission) 이 500 이상인 사원의 정보를 출력하는 함수를 작성하세요.
//    단, 페이징 처리 하세요.
@Query(value = "select e.*     " +
        "from tb_emp e     " +
        "where e.commission >= :commission"
        ,countQuery = "select count(*)    " +
        "from tb_emp e     " +
        "where e.commission >= :commission"
,nativeQuery = true)
    Page<Emp> selectByCommissionPage(@Param("commission") int commission,
                                     Pageable pageable);
}









