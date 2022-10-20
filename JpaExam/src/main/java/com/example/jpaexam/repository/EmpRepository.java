package com.example.jpaexam.repository;

import com.example.jpaexam.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends JpaRepository<Emp,Integer> {
//    JPQL 함수를 사용할 수 있음 : 응용
//    @Query 쿼리메소드
}
