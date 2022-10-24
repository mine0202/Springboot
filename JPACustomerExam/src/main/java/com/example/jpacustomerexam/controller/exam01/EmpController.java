package com.example.jpacustomerexam.controller.exam01;

import com.example.jpacustomerexam.model.Emp;
import com.example.jpacustomerexam.service.exam01.EmpService;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController , ResponseEntity 를 사용
//@RestController  return 값이 json 데이터형태로 출력됨
@Slf4j
@RestController
@RequestMapping("/exam01")
public class EmpController {

    // 스프링부트가 가동될때 생성된 객체를 하나 받아오기
//    스프링부트 : DI ( 의존성 주입, @Autowired )
    @Autowired
    EmpService empService;


//    desc 내림차순
    @GetMapping("/emp/eno/desc")
    public ResponseEntity<Object> getEmpAllEnoDesc(){
        try{
            List<Emp> list = empService.findAllDesc(); // 내림차순 정렬 전체조회
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
    @GetMapping("/emp/eno/asc")
    public ResponseEntity<Object> getEmpAllEnoAsc(){
        try{
            List<Emp> list = empService.findAllAsc();
            if (list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//

    @GetMapping("/emp/ename/asc")
    public ResponseEntity<Object> getEmpAllEnameAsc(){
        try{
            List<Emp> list = empService.findAllEnameAsc(); // 내림차순 정렬 전체조회
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/like/ename/{ename}")
    public ResponseEntity<Object> getEmpEnameLike(@PathVariable String ename) {
        try {
            List<Emp> list = empService.findAllByEnameContainingOrderByEnameDesc(ename); // 내림차순 정렬 전체조회
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/like/job/{job}")
    public ResponseEntity<Object> getEmpJobDesc(@PathVariable String job) {
        try {
            List<Emp> list = empService.findAllByJobContainingOrderByJobDesc(job); // 내림차순 정렬 전체조회
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/like/job/dno20/{job}&{dno}")
    public ResponseEntity<Object> getEmpJobDesc(@PathVariable String job, @PathVariable int dno) {
        try {
            List<Emp> list = empService.findAllByJobAndDno(job, dno);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/salary/{start}&{end}")
    public ResponseEntity<Object> getEmpSalary(@PathVariable int start, @PathVariable int end) {
        try {
            List<Emp> list = empService.findAllBySalaryBetween(start, end);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/job/manager/{manager}")
    public ResponseEntity<Object> getEmpJobManager(@PathVariable String manager) {
        try {
            List<Emp> list = empService.findAllByJobIgnoreCase(manager);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/commission")
    public ResponseEntity<Object> getEmpSalaryCommission() {
        try {
            List<Emp> list = empService.findAllByCommissionNotNull();
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/SalaryAndEname")
    public ResponseEntity<Object> getEmpSalaryEname() {
        try {
            List<Emp> list = empService.findAllByOrderBySalaryDescEnameAsc();
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    소문자 , 단어별로 끈어서 사용 : ex) salary than 이것을 salary/than, than/salary 등
//    url 매개변수 {} 앞에 무슨 값이 들어오는지 단어를 적어주면 좋음 Ex)  /dno/10
//    주로 동사보다 명사를 씀  delete 보다
    @GetMapping("/emp/SalaryLessOrGrater/{less}&{great}")
    public ResponseEntity<Object> getEmpSalarLessGrater(@PathVariable int less,@PathVariable int great) {
        try {
            List<Emp> list = empService.findAllBySalaryLessThanOrSalaryGreaterThan(less,great);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/Salary/commission/{a}&{b}&{c}")
    public ResponseEntity<Object> getEmpSalarCommission(@PathVariable int a,@PathVariable int b,@PathVariable int c) {
        try {
            List<Emp> list = empService.findAllByCommissionIsOrCommissionIsOrCommissionIs( a,  b,  c);
            if (list.isEmpty() == false) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
