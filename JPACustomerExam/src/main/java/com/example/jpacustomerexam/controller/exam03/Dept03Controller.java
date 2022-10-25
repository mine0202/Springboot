package com.example.jpacustomerexam.controller.exam03;

import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.service.exam02.Dept02Service;
import com.example.jpacustomerexam.service.exam03.Dept03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/exam03")
public class Dept03Controller {

    @Autowired
    Dept03Service deptService;

//    url테스트 : http://localhost:8000/exam03/dept/desc/paging?page=0&size=2
//    url 속성 page 현제페이지번호, size - 한페이지당 데이터 건수

    @GetMapping("/dept/desc/paging")
    public ResponseEntity<Object> findAllByOrderByDnoDesc(Pageable pageable){
        try{
            Page<Dept> page = deptService.findAllByOrderByDnoDesc(pageable);

//            로직을 추가 : data + currentPage(현재페이지수) + totalItems(총데이터건수) + totalPages(총페이지수)
//            Map 자료구조에 담아서 전송(클라이언트)
            Map<String, Object> response = new HashMap<>();

//            맵객체.put(키이름, 값) : 맵객체에 키이름으로 정보를 저장
            response.put("dept",page.getContent());  // 테이블 페이징된 데이터
            response.put("currentPage",page.getNumber());  // 현제페이지 번호 ( 0 번부터 )
            response.put("totalItem",page.getTotalElements());  // 총데이터 건수
            response.put("totalPages",page.getTotalPages());  // 총페이지수
            if( page.isEmpty()== false){
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
