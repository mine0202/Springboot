package com.example.jpacustomerexam.repository;


import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// JPA CRUD 를 위한 인터페이스 (Dao 와 같음)
// JPA 라이브러리를 상속받아야함 , 상속만 받으면 부모의 모든것을 사용할수 있음
// extends JpaRepository<모델명(엔터티), @Id의 속성자료형> : JPA 인터페이스를 상속받아야 CRUD를 사용가능
// @Service,@Repository, @Component 서버가 기동될때 객체를 자동 생성해주는 어노테이션
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
//    1. 쿼리 메소드 jpa에서 제공하는 사용자 정의 함수
//    쿼리문을 자동으로 만들어줌 : 함수의 이름을 보고 만듬
//    전체조회 , 내림차순으로 정렬   find  All  By  OrderBy  Dno  Desc  이렇게 합쳐짐
//    find 데이터 찾기  : select
//    All 있으면 여러건(배열) , All 없으면 한건  : *
//    By 고정(항상 나옴)  : from
//    속성명 - 전체조회라서 생략, 있으면 매개변수로 들어옴  : where 절의 컬럼명 (조건절)
//    OrderBy 정렬 예약어(고정)  :  order by
//    속성명Desc 속성명으로 내림차순 정렬  :컬럼명 desc
//    속성명Asc 속성명으로 오름차순 정렬  : 컬럼명 asc
    List<Dept> findAllByOrderByDnoDesc();  // 전체 조회 내림차순() 이것을 사용하는 규칙이있음

//    전체 부서 검색을 오름차순으로 정렬하는 함수 만들기
    List<Dept> findALlByOrderByDnoAsc();


//    연습 : 전체조회 dname 내림차순 함수를 정의하고 콘솔에 출력
    List<Dept> findAllByOrderByDnameAsc();

//    연습 : dname 으로 like 검색으로 내림차순조회 : Containing
    List<Dept> findAllByDnameContainingOrderByDnameDesc(String dname);


//   #########################################################
//    2. @Query 어노테이션을 이용한 방식

//     2가지가 있으며 객체쿼기 와 native 쿼리 (sql 묹 작성 )
//   ##########################################################

////    dname 으로 like  검색하고 부서명올 내림차순 조회
    @Query(value = "select d.* from tb_dept d where d.dname like %:dname% order by d.dname desc "
            , nativeQuery = true )
    List<Dept> selectByDname(@Param("dname") String dname);


//    객체 쿼리
//     특징 : 1. from 대상 : 객체명( 클래스명)
//           2. *(all 컬럼) 못씀  : 별명 ( 모든 속성 )
//           3. 컬럼명 대신 속성명(필드명)
//           4. 대소문자 구분
//       장점 : DB 종속적이지 않음 ( Oracle, My-sql , Ms-sql 교체해도  쉽게 변경가능 )
//      복작 업무 시스템은 분석함수 sql  문 ( connect by , RANK 등)
//    @Query(value = "select d" +
//            " from Dept d "+
//            "where d.dname like %:dname% order by d.dname desc")
//    List<Dept> selectByDname(@Param("dname") String dname);

// 부서테이블에서 부서명 (dname) 과 위치 (loc) 를 매개변수로 받아서 부서정보조회하는 함수
    @Query(value = "select d.* " +
            "from tb_dept d " +
            "where d.dname = :dname and d.loc = :loc ",
            nativeQuery = true)
    List<Dept> selectByDnameAndLoc(@Param("dname") String dname, @Param("loc") String loc);

// 객체 쿼리  :  테이블멸이 클래스명으로  컬럼명이 속성(필드)명으로 바뀌고 * 없어짐 , 대소문자 구분
//    @Query(value = "select d " +
//        "from Dept d " +
//        "where d.dname = :dname and d.loc = :loc " )
//List<Dept> selectByDnameAndLoc(@Param("dname") String dname, @Param("loc") String loc);

//  부서테이블의 부서번호를 sum ,avg , max, min 값을 출력하는 함수를 작성
//     그룹함수 : 쿼리 메소드를 지원하지 않음
//     Dept : dno , dname , loc 3개뿐이라서
//     DTO ( Data Transfer Object ) 클래스 : 가공된 추가 데이터를 담을 그릇을 만든 추가한 속성클래스
//    native query 를 사용할 경우 , DTO  인터페이스 (안에는 getter 만 정의 ) 프로젝션 인터페이스라고함
//    객체쿼리를 사용할 경우 DTO 클래스

//    dto/DeptGroupDto 인터페이스에 getter 함수( getSumVar , getAvgVar , getMaxVar , getMinVar )를 만들고
//    get을 제외한 함수명으로 적어줌
    @Query(value = "select sum(d.dno) as sumVar, " +
            "avg(d.dno) as avgVar, " +
            "max(d.dno) as maxVar, " +
            "min(d.dno) as minVar " +
            "from tb_dept d", nativeQuery = true)
    List<DeptGroupDto> selectByGroupFunc();

//  객체 쿼리
//
//    @Query(value = "select sum(d.dno) as sumVar, " +
//            "avg(d.dno) as avgVar, " +
//            "max(d.dno) as maxVar, " +
//            "min(d.dno) as minVar " +
//            "from Dept d")
//    List<DeptGroupDto> selectByGroupFunc();

//    부서정보를 아래와 같이 조작해서 출력하는 함수를 작성
//    힌트 sql 내장 함수 문자열 붙이기 함수 concat ( '문자열", 컬럼 )
//     "dnoVar": "부서번호는 40",
//    "dnameVar": "부서명은 OPERATIONS",
//    "locVar": "부서위치는 BOSTON",
//    "textVar": "모든 부서정보를 출력했습니다.",

    @Query(value = " select concat('부서번호는 ',d.dno) as dnoVar, " +
            "concat('부서명은 ',d.dname) as dnameVar, " +
            "concat('부서위치는 ' , d.loc) as dloVar, " +
            "'모든 부서정보를 출력했습니다.' as textVar " +
            "from tb_dept d where d.dno = :dno", nativeQuery = true)
    List<DeptGroupDto> selectByDnoFunc(@Param("dno") String dno);




//    3. QueryDsl 언어를 이용하는 방식

}
