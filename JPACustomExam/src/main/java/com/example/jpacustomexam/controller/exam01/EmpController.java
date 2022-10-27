package com.example.jpacustomexam.controller.exam01;

import com.example.jpacustomexam.model.Emp;
import com.example.jpacustomexam.service.exam01.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.jpaexam.controller.exam07
 * fileName : Emp07Controller
 * author : ds
 * date : 2022-10-21
 * description : Emp Rest 컨트롤러
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/exam01")
public class EmpController {

    //    스프링부트 : DI(의존성 주입) ( @Autowired )
    @Autowired
    EmpService empService; // @Autowired : 스프링부트가 가동될때 생성된 객체를 하나 받아오기

    //  *1) 클라이언트 : (form태그) Get 방식(url) -> 2) 서버 : @GetMapping("url") -> 3) DB: select 요청,
    @GetMapping("/emp")
    public ResponseEntity<Object> getEmpAll() {

        try {
            List<Emp> list = empService.findAll();

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  2)
    @GetMapping("/emp/desc")
    public ResponseEntity<Object> getEmpAllDesc() {

        try {
            List<Emp> list = empService.findAllByOrderByEnoDesc();

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  3) like 검색 : ename 함수
    @GetMapping("/emp/like/ename/{ename}")
    public ResponseEntity<Object> getEmpEnameLike(@PathVariable String ename) {

        try {
            List<Emp> list = empService.findAllByEnameContainingOrderByEnameDesc(ename);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //  4) like 검색 : job 함수
    @GetMapping("/emp/like/job/{job}")
    public ResponseEntity<Object> getEmpJobLike(@PathVariable String job) {

        try {
            List<Emp> list = empService.findAllByJobContainingOrderByJobDesc(job);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  5) like 검색 : job 함수
    @GetMapping("/emp/dno/{dno}")
    public ResponseEntity<Object> getEmpJobLike(@PathVariable int dno) {

        try {
//            job = "MANAGER" 고정
            List<Emp> list = empService.findAllByJobAndDno("MANAGER", dno);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  6) like 검색 : job 함수
    @GetMapping("/emp/between/salary/{first}/{last}")
    public ResponseEntity<Object> getEmpSalaryBetween(@PathVariable int first,
                                                      @PathVariable int last
    ) {

        try {
//            job = "MANAGER" 고정
            List<Emp> list = empService.findAllBySalaryBetweenOrderByEname(first, last);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  7) job 대소문자 무시 함수
    @GetMapping("/emp/job/{job}")
    public ResponseEntity<Object> getEmpSalaryBetween(@PathVariable String job
    ) {

        try {
            List<Emp> list = empService.findAllByJobIgnoreCase(job);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  8)
    @GetMapping("/emp/commission")
    public ResponseEntity<Object> getEmpCommissionIsNotNull() {

        try {
            List<Emp> list = empService.findAllByCommissionIsNotNull();

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  9)
    @GetMapping("/emp/salary/desc/ename/asc")
    public ResponseEntity<Object> getEmpSalaryDescEnameAsc() {

        try {
            List<Emp> list = empService.findAllByOrderBySalaryDescEnameAsc();

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  9)
//    URL 설계 표준( REST API 프로그램 개발 ) :
//    1) 소문자 , 단어별로 끈어서 사용 : ex) salary than -> salary/than, than/salary 등
//    2) url 매개변수 {}  : {}앞에 무슨 값이 들어오는지 설명 단어를 적어주면 좋음 ex) /dno/10
//    3) 주로 동사보다 명사를 추천 : ex) delete -> deletion, add -> addition 등
    @GetMapping("/emp/than/salary/{first}/{last}")
    public ResponseEntity<Object> getEmpSalaryThan(@PathVariable int first,
                                                   @PathVariable int last
                                                   ) {

        try {
            List<Emp> list = empService.findAllBySalaryLessThanOrSalaryGreaterThan(first, last);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  10)
//    URL 설계 표준( REST API 프로그램 개발 ) :
//    1) 소문자 , 단어별로 끈어서 사용 : ex) salary than -> salary/than, than/salary 등
//    2) url 매개변수 {}  : {}앞에 무슨 값이 들어오는지 설명 단어를 적어주면 좋음 ex) /dno/10
//    3) 주로 동사보다 명사를 추천 : ex) delete -> deletion, add -> addition 등
    @GetMapping("/emp/or/commission/{first}/{second}/{third}")
    public ResponseEntity<Object> getEmpSalaryThan(@PathVariable int first,
                                                   @PathVariable int second,
                                                   @PathVariable int third
                                                   ) {

        try {
            List<Emp> list = empService.findAllByCommissionOrCommissionOrCommission(first, second, third);

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}











