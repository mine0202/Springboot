package com.example.jpacustomerexam.repository;


import com.example.jpacustomerexam.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JPA CRUD 를 위한 인터페이스 (Dao 와 같음)
// JPA 라이브러리를 상속받아야함 , 상속만 받으면 부모의 모든것을 사용할수 있음
// extends JpaRepository<모델명(엔터티), @Id의 속성자료형> : JPA 인터페이스를 상속받아야 CRUD를 사용가능
// @Service,@Repository, @Component 서버가 기동될때 객체를 자동 생성해주는 어노테이션
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
//    1. 쿼리 메소드
//    전체조회 , 내림차순으로 정렬   find  All  By  OrderBy  Dno  Desc  이렇게 합쳐짐
//    find 데이터 찾기
//    All 있으면 여러건(배열) , All 없으면 한건
//    By 고정(항상 나옴)
//    속성명 - 전체조회라서 생략, 있으면 매개변수로 들어옴
//    OrderBy 정렬 예약어(고정)
//    속성명Desc 속성명으로 내림차순 정렬
//    속성명Asc 속성명으로 오름차순 정렬
    List<Dept> findAllByOrderByDnoDesc();  // 전체 조회 내림차순() 이것을 사용하는 규칙이있음

//    연습 : 전체조회 dname 내림차순 함수를 정의하고 콘솔에 출력
    List<Dept> findAllByOrderByDnameAsc();

//    연습 : dname 으로 like 검색으로 내림차순조회 : Containing
    List<Dept> findAllByDnameContainingOrderByDnameDesc(String dname);

//    2. @Query 어노테이션을 이용한 방식

//    3. QueryDsl 언어를 이용하는 방식

}
