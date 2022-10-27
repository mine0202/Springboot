
package com.example.jpacustomerexam.controller.exam03;

import com.example.jpacustomerexam.model.Emp;
import com.example.jpacustomerexam.service.exam03.Emp03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/exam03")
public class Emp03Controller {

    //    스프링부트 : DI(의존성 주입) ( @Autowired )
    @Autowired
    Emp03Service empService; // @Autowired : 스프링부트가 가동될때 생성된 객체를 하나 받아오기

    //  1)
    @GetMapping("/emp/all/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/all/paging?page=0&size=2
    public ResponseEntity<Object> getEmpAllPage(Pageable pageable) {

        try {
            Page<Emp> page = empService.findAll(pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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
    @GetMapping("/emp/ename/desc/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/all/paging?page=0&size=2
    public ResponseEntity<Object> getEmpByEnameDescPage(Pageable pageable) {

        try {
            Page<Emp> page = empService.findAllByOrderByEnameDesc(pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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

    //  3)
    @GetMapping("/emp/salary/{first}/{last}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/all/paging?page=0&size=2
    public ResponseEntity<Object> getEmpBySalaryBetweenPage(@PathVariable int first,
                                                            @PathVariable int last,
                                                            Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .findAllBySalaryBetweenAndCommissionIsNotNull(first, last, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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

    //  4)
    @GetMapping("/emp/ename/{ename}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getEmpByEnameContainingPage(@PathVariable String ename,
                                                            Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .findAllByEnameContaining(ename, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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

    //  5)
    @GetMapping("/emp/dno/{dno}/salary/{salary}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getEmpByDnoSalaryPage(@PathVariable int dno,
                                                              @PathVariable int salary,
                                                            Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .findAllByDnoAndSalaryGreaterThanEqual(dno, salary, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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

    //  6)
    @GetMapping("/emp/salary2/{first}/{last}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getBySalaryBetweenPage(@PathVariable int first,
                                                     @PathVariable int last,
                                                     Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .selectBySalaryPage(first, last, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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

    //  7)
    @GetMapping("/emp/hiredate/{first}/{last}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getByHireDatePage(@PathVariable String first,
                                                     @PathVariable String last,
                                                     Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .selectByHireDatePage(first, last, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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
    @GetMapping("/emp/salary/{first}/{last}/dno/{firstDno}/{lastDno}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getBySalaryDnoPage(@PathVariable int first,
                                                     @PathVariable int last,
                                                     @PathVariable int firstDno,
                                                     @PathVariable int lastDno,
                                                     Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .selectBySalaryDnoPage(first, last, firstDno, lastDno, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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
    @GetMapping("/emp/hiredate/{hiredate}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getByHiredateContainingPage(@PathVariable String hiredate,
                                                     Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .selectByHiredateContainingPage(hiredate, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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
    @GetMapping("/emp/commission/{commission}/paging")
//    URL 테스트 : http://localhost:8000/exam03/emp/ename/S/paging?page=0&size=4
    public ResponseEntity<Object> getByCommissionPage(@PathVariable int commission,
                                                     Pageable pageable)
    {

        try {
            Page<Emp> page = empService
                    .selectByCommissionPage(commission, pageable);

//            페이지 정보를 맵 자료구조에 저장해서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
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











