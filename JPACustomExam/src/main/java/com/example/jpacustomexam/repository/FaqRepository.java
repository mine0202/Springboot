package com.example.jpacustomexam.repository;

import com.example.jpacustomexam.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface FaqRepository extends JpaRepository<Faq, Integer> {
}









