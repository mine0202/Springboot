package com.example.jpacustomerexam.controller.exam01;

import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.service.exam01.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController , ResponseEntity 를 사용
//@RestController  return 값이 json 데이터형태로 출력됨
@Slf4j
@RestController
@RequestMapping("/exam01")
public class DeptController {

    // 스프링부트가 가동될때 생성된 객체를 하나 받아오기
//    스프링부트 : DI ( 의존성 주입, @Autowired )
    @Autowired
    DeptService deptService;

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

//    클라이언트 from 태그 Post url 방식으로 보내고 서버에서 @PostMapping(url) 으로 받아서 DB에 insert 요청
    @PostMapping("/dept")
    public ResponseEntity<Object> createDept(@RequestBody Dept dept){
        try{
            Dept dept1 = deptService.save(dept); // 키 값이 없으므로 insert 요청
            return new ResponseEntity<>(dept1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    json 객체와 일반 변수를 받아서 @RequestBody + @PathVariable 을 사용
//    클라이언트 form 태그 put 방식 url 보내고 서버에서 @PostMapping(url) 방아서 DB에 update 요청
    @PutMapping("/dept/edit/{dno}")
    public ResponseEntity<Object> updateDept(@PathVariable int dno, @RequestBody Dept dept){
        try{
            Dept dept1 = deptService.save(dept); // 키 값이 있으면 자동으로 update 실행됨
            return new ResponseEntity<>(dept1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    delete 함수
//    클라이언트 form 태그 delete 방식 url 보내고 서버에서 @DeleteMapping(url) 방아서 DB에 delete 요청
    @DeleteMapping("/dept/delete/{dno}")
    public ResponseEntity<Object> deleteDept(@PathVariable int dno){
        try{
            boolean bSuccess = deptService.removeById(dno); // delete 실행됨
            if(bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
//            실패했을경우 dno 가 존재하지 않을때
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    desc 내림차순
    @GetMapping("/dept/dno/desc")
    public ResponseEntity<Object> getDeptAllDnoDesc(){
        try{
            List<Dept> list = deptService.findAllDesc(); // 내림차순 정렬 전체조회
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

    @GetMapping("/dept/dno/asc")
    public ResponseEntity<Object> getDeptAllDnoAsc(){
        try{
            List<Dept> list = deptService.findAllAsc();
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


    @GetMapping("/dept/dname/asc")
    public ResponseEntity<Object> getDeptAllDnameAsc(){
        try{
            List<Dept> list = deptService.findAllDnameAsc(); // 내림차순 정렬 전체조회
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

    @GetMapping("/dept/desc/{dname}")
    public ResponseEntity<Object> getDeptAllDnameDesc(@PathVariable String dname){
        try{
            List<Dept> list = deptService.findAllDnameDesc(dname); // 내림차순 정렬 전체조회
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


}
