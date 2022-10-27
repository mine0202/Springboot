package com.example.jpacustomexam.repository;

import com.example.jpacustomexam.dto.DeptGroupDto;
import com.example.jpacustomexam.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.example.jpaexam.repository
 * fileName : DeptRepository
 * author : ds
 * date : 2022-10-20
 * description : JPA CRUD를 위한 인터페이스(==DAO)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
// @Service, @Repository, @Component : 서버가 기동될때 객체를 자동 생성해주는 어노테이션
// JpaRepository<모델(엔티티)명, @ID의_속성자료형> : JPA 인터페이스를 상속받아야 CRUD 함수를 사용가능
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
//    #######################################################################
//     쿼리 메소드
//    #######################################################################

    //    1) 쿼리 메소드 방식 : JPA 제공하는 사용자 정의 함수
//         쿼리문을 자동으로 만들어줌 : 함수의 이름을 보고 만듬
//    그룹함수는 지원안함 : GroupBy 없음
//    전체 조회 내림차순 정렬 : find + All + By + OrderBy + Dno + Desc 이름 분석
//    find : 데이터 찾기 -> select
//    All : 여러 건(배열) -> *
//    by : 고정(항상 나옴) -> from
//    속성명 : 생략 ( 있으면 매개변수로 들어옴 ) where 절의 컬럼명 (조건절)
//    OrderBy : 정렬 (고정) -> order by
//    속성명Desc : 속성명으로 내림차순 정렬 -> 컬럼명 desc
//    속성명Asc : 속성명으로 오름차순 정렬 -> 컬럼명 asc
    List<Dept> findAllByOrderByDnoDesc(); // 전체 조회 내림차순(부서번호) -> 이것을 사용하는 규칙

    //    전체 부서 검색을 부서번로를 오름차순으로 정렬하는 함수 만들기
//    sql : select * from tb_dept order by dno asc;
//    sql 문을 오히려 더 잘아셔야 함
    List<Dept> findAllByOrderByDnoAsc();

    //    select * from tb_dept where dname like '%S%' -> like 검색
//   2) dname like 검색으로 dname 내림차순 조회 : Containing
    List<Dept> findAllByDnameContainingOrderByDnameDesc(String dname);

//    1) 연습문제 : 전체 조회 dname 으로 내림차순 함수를 정의하고 콘솔에 출력해 보세요

//    2) @Query 어노테이션을 이용한 방식

//    #######################################################################
//     @Query(쿼리문) : 2가지 ( 1) 객체 쿼리, 2) native 쿼리(sql문 작성) )
//    #######################################################################
//    1) 부서명( dname ) like 검색 하고 부서명으로 내림차순 조회
//  1-1) Native 쿼리 : 오라클 쿼리 sql문
    @Query(value="select d.* from tb_dept d where d.dname like %:dname%"
            ,nativeQuery = true)
    List<Dept> selectByDname(@Param("dname") String dname);

//  1-2) 객체 쿼리
//       특징 : 1) from 대상 : 객체명(클래스명) 별명
//             2) * 못씀(all 컬럼) : 별명 ( 모든 속성 )
//             3) 컬럼명 -> 속성명(필드명)
//             4) 대소문자 구분
//       장점 : DB 에 종속적이지 않음 ( Oracle, MY-SQL , MS-SQL 교체해도 쉽게 변경 가능 )
//    참고) 복잡 업무 시스템 : 분석함수 sql 문 ( Connect by , RANK 등 )
//    @Query(value="select d " +
//            "from Dept d " +
//            "where d.dname like %:dname%")
//    List<Dept> selectByDname(@Param("dname") String dname);

//    2) 부서 테이블에서 부서명( dname )과 위치(loc)를 매개변수로 받아서
//       부서 정보를 조회하는 함수를 작성하려고 한다.
//    @Query 써서 작성해 보세요 : selectByDnameAndLoc()
//    1-1) native query : sql문
    @Query(value = "select d.* " +
            "from tb_dept d " +
            "where d.dname = :dname " +
            "and d.loc = :loc "
            ,nativeQuery = true)
    List<Dept> selectByDnameAndLoc(@Param("dname") String dname,
                                   @Param("loc") String loc);

//    1-2) 객체 쿼리 : 테이블명 -> 클래스명, 컬럼명 -> 속성(필드)명, * 없어짐, 대소문자 구분
//    @Query(value = "select d " +
//            "from Dept d " +
//            "where d.dname = :dname " +
//            "and d.loc = :loc ")
//    List<Dept> selectByDnameAndLoc(@Param("dname") String dname,
//                                   @Param("loc") String loc);

//    3) 부서테이블의 부서번호를 sum, avg, max, min 값을 출력하는 함수를 작성하세요.
//    그룹함수 : 쿼리메소드 ( 지원하지 않음 )
//    모델 Dept 적당한 속성이 없음 : dno, dname, loc
//    담을 그릇(클래스) : DTO( Data Transfer Object ) 클래스 ( 가공된 추가 데이터를 담을 클래스 )
//    1) nativeQuery 를 사용할 경우 : DTO 인터페이스 ( 안에는 getter 만 정의 ) ( 프로젝션 )
//    2) 객체 쿼리를 사용할 경우 : DTO 클래스
//    1-1) native 쿼리 : 일반 sql문
    @Query(value = "select sum(d.dno) as sumVar, " +
            "avg(d.dno) as avgVar, " +
            "max(d.dno) as maxVar, " +
            "min(d.dno) as minVar " +
            "from tb_dept d "
            ,nativeQuery = true)
    List<DeptGroupDto> selectByGroupFunc();

//    1-2) 객체 쿼리
//    @Query(value = "select sum(d.dno) as sumVar, " +
//            "avg(d.dno) as avgVar, " +
//            "max(d.dno) as maxVar, " +
//            "min(d.dno) as minVar " +
//            "from Dept d ")
//    List<DeptGroupDto> selectByGroupFunc();

//    4) 부서 정보를 아래와 같이 조작해서 출력하는 함수를 작성하세요.
// 결과 : 4건
//    힌트 : sql 내장 함수 , 문자열 붙이기 함수 concat('문자열', 컬럼) 또는 'Oracle ' || 'Mania'
//    {
//        "sumVar": null,
//    ...
//        "dnoVar": "부서번호는 40",
//        "dnameVar": "부서명은 OPERATIONS",
//        "locVar": " 부서위치는BOSTON",
//        "textVar": "모든 부서정보를 출력했습니다."
//   }
    @Query(value = "select concat('부서번호는 ', d.dno) as dnoVar, " +
            "concat('부서명은 ', d.dname) as dnameVar, " +
            "concat('부서위치는 ',d.loc) as locVar, " +
            "'모든 부서정보를 출력햇습니다.'  as textVar " +
            "from tb_dept d "
            ,nativeQuery = true
    )
    List<DeptGroupDto> selectByCustomDept();

//    5) 연습문제 : DeptGroupDto 이용
//    SQL 기본 내장 함수를 사용하는 쿼리를 작성하고 함수로 정의하세요.
//    sql 함수 : upper(dname), lower(dname), length(dname), substr(dname, 1, 2)
//    부서명(dname)
//    함수명 : selectByBasicFunc()
    @Query(value = "select upper(d.dname) as upperDname " +
            ",lower(d.dname) as lowerDname " +
            ",length(d.dname) as lengthDname " +
            ",substr(d.dname,1,2 ) as substrDname " +
            ",trim('  Oracle Mania   ') as trimDname " +
            ",trunc(98.123) as truncDname " +
            ",to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') as charDname " +
            "from tb_dept d"
          ,nativeQuery = true)
    List<DeptGroupDto> selectByBasicFunc();

//    6번 : case when ( sql 조건문 사용 )
//    부서번호가 20보다 작으면 '연말 보너스 : 100%' 출력
//    부서번호가 20보다 크면 '연말 보너스 : 200%' 출력
//    둘다 아니면 '연말 보너스 : 없음' 출력하는 함수를 작성
    @Query(value = "select d.dno" +
            "     ,case when d.dno < 20 then '연말 보너스 : 100%'" +
            "     when d.dno > 20 then '연말 보너스 : 200%'" +
            "     else '연말 보너스 : 없음'" +
            "     end as incentive " +
            "from tb_dept d "
                    ,nativeQuery = true)
    List<DeptGroupDto> selectByCase();
}









