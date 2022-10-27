package com.example.jpacustomexam.controller.exam03;

import com.example.jpacustomexam.dto.DeptGroupDto;
import com.example.jpacustomexam.model.Dept;
import com.example.jpacustomexam.service.exam02.Dept02Service;
import com.example.jpacustomexam.service.exam03.Dept03Service;
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
import java.util.List;
import java.util.Map;

/**
 * packageName : com.example.jpaexam.controller.exam07
 * fileName : Dept07Controller
 * author : ds
 * date : 2022-10-21
 * description : 부서 컨트롤러 쿼리 메소드
 * 요약 :
 *     사용자 정의(Custom) 함수 : sql문 직접 개발자 작성하는 것 핵심
 *     1) 쿼리 메소드 : 자동으로 사용자 정의 sql문을 생성해주는 함수
 *          목적 : 기본 함수보다 다양한 sql문을 작성하기 위해 사용
 *         사용법 : 함수이름으로 sql 문장을 작성함 ( Repository 안에 함수명만 작성 )
 *         ex) JPA 클래스 == 대상 테이블
 *             find == select
 *             all  == *
 *             by   == from
 *             속성  == where 컬럼
 *          orderBy == order by
 *          속성 desc == 컬럼 desc
 *    2) @Query(value=쿼리문)함수명() : 쿼리문에 해당되는 부분을 직접 개발자가 작성 , 함수명() 중요하지 않음
 *       쿼리문의 매개변수 전달 -> select * from tb_dept where dno = :변수명
 *                             List<Dept> selectAll(@Param(변수명) 타입 변수명)
 *          2-1) nativeQuery = true : 일반 sql문으로 작성
 *          2-2) nativeQuery = false, 생략 :  객체 쿼리
 *                     ( 테이블명 , 컬럼명 대신 클래스명, 속성(필드)명 사용 )
 *    3) 페이징 처리 함수
 *       목적 : 전체 데이터를 화면에 출력하면 성능과 가독성이 떨어지므로 , 몇건씩 끊어서 보여주는것이
 *          목적
 *       속성 : page = 현재페이지, size = 1 페이지 당 보여줄 데이터 수 ( url 매개변수 전달 )
 *       페이징 객체 : 1) 매개변수 페이징 객체 : Pageable
 *                   2) 리턴될 페이징 객체 : Page<객체자료형>
 *       클라이언트로 전송할 데이터 : Map 자료구조 이용
 *          1) 데이터 : 부서, 사원 등
 *          2) currentPage : 현재 페이지 수
 *          3) totalItems : 전체 데이터 수
 *          4) totalPages : 전체 페이지 수
 *
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
// @RestController : return 값이 json 데이터 형태로 출력됨
@Slf4j
@RestController
@RequestMapping("/exam03")
public class Dept03Controller {

    @Autowired
    Dept03Service deptService;

    //    URL 테스트 : http://localhost:8000/exam03/dept/desc/paging?page=0&size=2
    @GetMapping("/dept/desc/paging")
    public ResponseEntity<Object> getDeptDescPage(Pageable pageable) {

        try {
            Page<Dept> page = deptService.getDeptDescPage(pageable);

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










