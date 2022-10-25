package com.example.jpacustomerexam.controller.exam02;

import antlr.debug.SemanticPredicateAdapter;
import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.dto.EmpGroupDto;
import com.example.jpacustomerexam.model.Emp;
import com.example.jpacustomerexam.service.exam02.Emp02Service;
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
@RequestMapping("/exam02")
public class Emp02Controller {

    @Autowired
    Emp02Service empService;

    @GetMapping("/emp/{ename}")
    public ResponseEntity<Object> selectEname(@PathVariable String ename){
        try{
            List<Emp> list = empService.selectEname(ename);
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/selectDescAsc")
    public ResponseEntity<Object> selectDescAsc(){
        try{
            List<Emp> list = empService.selectDescAsc();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/selectCommissionSalary/{salary}")
    public ResponseEntity<Object> selectCommissionSalary(@PathVariable int salary){
        try{
            List<Emp> list = empService.selectCommissionSalary(salary);
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/hiredate/{hiredate}")
    public ResponseEntity<Object> selectHiredate(@PathVariable String hiredate){
        try{
            List<Emp> list = empService.selectHiredate(hiredate);
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/sumSalary")
    public ResponseEntity<Object> sumSalary(){
        try{
            List<EmpGroupDto> list = empService.sumSalary();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/truncSalary")
    public ResponseEntity<Object> truncSalary(){
        try{
            List<EmpGroupDto> list = empService.truncSalary();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/maxSal")
    public ResponseEntity<Object> maxSal(){
        try{
            List<EmpGroupDto> list = empService.maxSal();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/sumSalaryJob")
    public ResponseEntity<Object> sumSalaryJob(){
        try{
            List<EmpGroupDto> list = empService.sumSalaryJob();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/selectCountMax")
    public ResponseEntity<Object> selectCountMax(){
        try{
            List<EmpGroupDto> list = empService.selectCountMax();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/emp/selectOldYoung")
    public ResponseEntity<Object> selectOldYoung(){
        try{
            List<EmpGroupDto> list = empService.selectOldYoung();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
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
