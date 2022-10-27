package com.example.jpacustomerexam.controller.exam07;

import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.model.exam04.Employee;
import com.example.jpacustomerexam.service.exam07.Dept07Service;
import com.example.jpacustomerexam.service.exam07.Emp07Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exam07")
public class Emp07Controller {

    @Autowired
    Emp07Service empService;


    @GetMapping("/emp/querydsl/ename/{ename}")
    public ResponseEntity<Object> querydslByEname(@PathVariable String ename){
        try{
            List<Employee> list = empService.querydslByEname(ename);

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

    @GetMapping("/emp/querydsl/ename/job/{ename}&{job}")
    public ResponseEntity<Object> querydslByEnameAndJob(@PathVariable String ename, @PathVariable String job){
        try{
            List<Employee> list = empService.querydslByEnameAndJob(ename, job);

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


}
