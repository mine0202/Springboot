package com.example.jpacustomerexam.repository.exam07;


import com.example.jpacustomerexam.model.exam04.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Querydsl 실습 레파지 토리
@Repository
public interface Dept07Repository extends JpaRepository<Department, Integer>, Dept07RepositoryCustom {


}
