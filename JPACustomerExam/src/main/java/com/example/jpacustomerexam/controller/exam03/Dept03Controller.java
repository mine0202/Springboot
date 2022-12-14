package com.example.jpacustomerexam.controller.exam03;

import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.service.exam03.Dept03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    //   1) getDeptAllPage 1st 작성 방법 : 쿼리스트링 방식
    //    URL 테스트 : http://localhost:8000/exam03/dept/all/paging?page=0&size=2&sort=dno,desc
    @GetMapping("/dept/all/paging")
    public ResponseEntity<Object> getDeptAllPage(Pageable pageable) {

        try {
            Page<Dept> page = deptService.getDeptAllPage(pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  2) 참고)  getDeptAllPage2 2nd 작성 방법 : 파라메터 방식으로 전송
    //    URL 테스트 : http://localhost:8000/exam03/dept/all/paging
    @GetMapping("/dept/all/paging2/page/{page}/size/{size}")
    public ResponseEntity<Object> getDeptAllPage2(@PathVariable int page,
                                                  @PathVariable int size
    ) {

        try {
//            Pageable 객체에 페이지 변수를(page=0, size=2) 저장
            Pageable pageable = PageRequest.of(page, size);

            Page<Dept> deptPage = deptService.getDeptAllPage(pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", deptPage.getContent());
            response.put("currentPage", deptPage.getNumber());
            response.put("totalItems", deptPage.getTotalElements());
            response.put("totalPages", deptPage.getTotalPages());

            if (deptPage.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    URL 테스트 : http://localhost:8000/exam03/dept/all/paging?page=0&size=2&sort=dno,desc
    @GetMapping("/dept/dname/{dname}/paging")
    public ResponseEntity<Object> getDeptDnamePage(@PathVariable String dname,
                                                   Pageable pageable) {

        try {
            Page<Dept> page = deptService.findAllByDnameContaining(dname, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    URL 테스트 : http://localhost:8000/exam03/dept/all/paging?page=0&size=2&sort=dno,desc
    @GetMapping("/dept/dname/{dname}/loc/{loc}/paging")
    public ResponseEntity<Object> getDeptDnameLocPage(@PathVariable String dname,
                                                      @PathVariable String loc,
                                                      Pageable pageable) {

        try {
            Page<Dept> page = deptService.selectByDnameAndLocPage(dname, loc, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    URL 테스트 : http://localhost:8000/exam03/dept/all/paging?page=0&size=2&sort=dno,desc
    @GetMapping("/dept/custom/paging")
    public ResponseEntity<Object> getDeptCustomDeptPage(Pageable pageable) {

        try {
            Page<DeptGroupDto> page = deptService.selectByCustomDeptPage(pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    URL 테스트 : http://localhost:8000/exam03/dept/all/paging?page=0&size=2&sort=dno,desc
    @GetMapping("/dept/case/paging")
    public ResponseEntity<Object> selectByCasePage(Pageable pageable) {

        try {
            Page<DeptGroupDto> page = deptService.selectByCasePage(pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
