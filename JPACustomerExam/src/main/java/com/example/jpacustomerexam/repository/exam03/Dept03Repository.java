package com.example.jpacustomerexam.repository.exam03;


import com.example.jpacustomerexam.model.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 페이징 처리 : 한번에 화면에 보여줄 개수 를 1페이지로 정해서 프로그램에서 나타내는것 ( 한페이지에 10개씩, 20개씩 .. )
// page : 한 페이지 번호
// size : 전체 페이지 수
// currentpage :  현재 페이지 번호
// totalItems : 전체 데이터 총건수
// totalpages : 전체 페이지 수

@Repository
public interface Dept03Repository extends JpaRepository<Dept, Integer> {
//  exam 03
//    쿼리메소드 방식 사용
//     전체 부서 정보 조회시 페이징 처리함수
    Page<Dept> findAllByOrderByDnoDesc(Pageable pageable);


}
