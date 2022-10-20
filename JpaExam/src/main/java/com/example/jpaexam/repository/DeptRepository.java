package com.example.jpaexam.repository;


import com.example.jpaexam.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA CRUD 를 위한 인터페이스 (Dao 와 같음)
// JPA 라이브러리를 상속받아야함 , 상속만 받으면 부모의 모든것을 사용할수 있음
// extends JpaRepository<모델명(엔터티), @Id의 속성자료형> : JPA 인터페이스를 상속받아야 CRUD를 사용가능
// @Service,@Repository, @Component 서버가 기동될때 객체를 자동 생성해주는 어노테이션
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {

}
