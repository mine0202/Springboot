package com.example.jpaexam.controller.exam08;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.service.exam08.Dept08Service;
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

// @RestController , ResponseEntity 를 사용
//@RestController  return 값이 json 데이터형태로 출력됨
@Slf4j
@RestController
@RequestMapping("/exam08")
public class Dept08Controller {

    // 스프링부트가 가동될때 생성된 객체를 하나 받아오기
//    스프링부트 : DI ( 의존성 주입, @Autowired )
    @Autowired
    Dept08Service deptService;

//    크라이언트가 form 태그에서 method Get(url) 방식 으로 보내면 서버에서 @GetMapping("url")으로 받고  데이터베이스에서는  select 요청
    @GetMapping("/dept")
    public ResponseEntity<Object> getDeptAll(){
        try{
            List<Dept> list = deptService.findAll();
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

    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> getDeptId(@PathVariable int dno){
        try{
            Optional<Dept> optionalDept = deptService.findById(dno);  // select 문
            if( optionalDept.isPresent()){
                return new ResponseEntity<>(optionalDept.get(),HttpStatus.OK);
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
}
