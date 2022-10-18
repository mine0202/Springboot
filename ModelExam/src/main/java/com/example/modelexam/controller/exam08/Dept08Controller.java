package com.example.modelexam.controller.exam08;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam08.Dept08Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// RestController 이용, ResponseEntity , GetDeptAll() 사용
//@RestController  Vue + Springboot 연동 프로그램에 사용
// 특징 : return 값이 json 데이터임
// @Slf4j  : Simple Logging facade for java , facade 디자인 패턴이용해서 제작됨
@Slf4j
@RestController
@RequestMapping("/exam08")
public class Dept08Controller {
    @Autowired
    Dept08Service deptService; // 스프링에서 생성된 객체 받아오기

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
                return new ResponseEntity<>(optionalDept.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
