package com.example.jpaexam.controller.exam08;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam08.Emp08Service;
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


@Slf4j
@RestController
@RequestMapping("/exam08")
public class Emp08Controller {

    @Autowired
    Emp08Service empService;

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

}
