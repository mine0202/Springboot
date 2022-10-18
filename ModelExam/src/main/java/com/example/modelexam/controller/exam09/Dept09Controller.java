package com.example.modelexam.controller.exam09;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam09.Dept09Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// RestController 이용, ResponseEntity , GetDeptAll() 사용
//@RestController  Vue + Springboot 연동 프로그램에 사용
// 특징 : return 값이 json 데이터임
// @Slf4j  : Simple Logging facade for java , facade 디자인 패턴이용해서 제작됨
@Slf4j
@RestController
@RequestMapping("/exam09")
public class Dept09Controller {
    @Autowired
    Dept09Service deptService; // 스프링에서 생성된 객체 받아오기

    @GetMapping("/dept")
    public ResponseEntity<Object> getDeptAll(){
        try{
            List<Dept> list = deptService.findAll();
            if( list.isEmpty()== false){
//                성공메세지 + 객체 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else{
//                데이터가 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage()); //에러로그 찍기
//            내부 서버 에러 internal_sever_error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    부서번호로 조회하기
    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> getDeptId(@PathVariable long dno){
        try{
//            Optional 하면 null 체크를 할 수 있음
            Optional<Dept> optionalDept = deptService.findById(dno);
//            .isPresent() 현재 있으면 true
            if(optionalDept.isPresent()){
//                optionalDept.get() 옵셔널 객체안에 들어있는 객체가 나옴.
                return new ResponseEntity<>(optionalDept.get(), HttpStatus.OK);  // 200 번
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//   새로운 부서 정보 생성 insert 함수
//    @RequestBody  : 클라이언트 쪽에서 전송한 객체정보(json데이터)를 함수 매개변수로(부서객체) 전달
    @PostMapping("/dept")
    public ResponseEntity<Object> creatDept(@RequestBody Dept dept){
        try{
//            .save(객체) 객체에 키(부서번호)가 없으면 insert , 있으면 update 로직
            List<Dept> list = deptService.save(dept);
            return new ResponseEntity<>(list,HttpStatus.CREATED);  // 201 번 출력
        }catch (Exception e){
            log.info(e.getMessage()); // 에러 메세지 콘솔 출력
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
