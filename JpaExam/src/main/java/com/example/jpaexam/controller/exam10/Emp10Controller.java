package com.example.jpaexam.controller.exam10;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam10.Emp10Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/exam10")
public class Emp10Controller {

    @Autowired
    Emp10Service empService;

    @GetMapping("/emp")
    public ResponseEntity<Object> getEmpAll(){
        try{
            List<Emp> list = empService.findAll();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/{eno}")
    public ResponseEntity<Object> getEmpId(@PathVariable int eno){
        try{
            Optional<Emp> optionalEmp = empService.findBYid(eno);
            if(optionalEmp.isPresent()){
                return new ResponseEntity<>(optionalEmp.get(),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    클라이언트 from 태그 Post url 방식으로 보내고 서버에서 @PostMapping(url) 으로 받아서 DB에 insert 요청
    @PostMapping("/emp")
    public ResponseEntity<Object> createEmp(@RequestBody Emp emp){
        try{
            Emp emp1 = empService.save(emp); // insert 문 호출
            return new ResponseEntity<>(emp1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/emp/edit/{eno}")
    public ResponseEntity<Object> updateEmp(@RequestBody Emp emp, @PathVariable int eno){
        try{
            Emp emp1 = empService.save(emp); // update 문 호출
            return new ResponseEntity<>(emp1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
//            서버 접속 에러 메세지를 클라이언트에 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
