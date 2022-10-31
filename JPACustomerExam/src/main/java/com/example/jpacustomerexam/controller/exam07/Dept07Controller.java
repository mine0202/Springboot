package com.example.jpacustomerexam.controller.exam07;

import com.example.jpacustomerexam.dto.DeptEmpClassDto;
import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.dto.querydsl.DeptGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.service.exam04.Dept04Service;
import com.example.jpacustomerexam.service.exam07.Dept07Service;
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
public class Dept07Controller {

    @Autowired
    Dept07Service deptService;


    @GetMapping("/dept/querydsl/dname/{dname}")
    public ResponseEntity<Object> querydslByDname(@PathVariable String dname){
        try{
            List<Department> list = deptService.querydslByDname(dname);

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

    @GetMapping("/dept/querydsl/dname/loc/{dname}&{loc}")
    public ResponseEntity<Object> querydslByDnameAndLoc(@PathVariable String dname, @PathVariable String loc){
        try{
            List<Department> list = deptService.querydslByDnameAndLoc(dname, loc);

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

    @GetMapping("/dept/querydsl/groupfunc")
    public ResponseEntity<Object> querydslByGroupfunc(){
        try{
            List<DeptGroupQueryDto> list = deptService.querydslByGroupfunc();

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

    @GetMapping("/dept/querydsl/querydslByDeptGt/{dno}")
    public ResponseEntity<Object> querydslByDeptGt(@PathVariable int dno){
        try{
            List<Department> list = deptService.querydslByDeptGt(dno);

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
    @GetMapping("/dept/querydsl/querydslByBasicFunc")
    public ResponseEntity<Object> querydslByBasicFunc(){
        try{
            List<DeptGroupQueryDto> list = deptService.querydslByBasicFunc();

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
    @GetMapping("/dept/querydsl/querydslByCasewhen")
    public ResponseEntity<Object> querydslByCasewhen(){
        try{
            List<DeptGroupQueryDto> list = deptService.querydslByCasewhen();

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

    @GetMapping("/dept/querydsl/querydslByDnoGroup/{countDno}")
    public ResponseEntity<Object> querydslByDnoGroup(@PathVariable long countDno){
        try{
            List<DeptGroupQueryDto> list = deptService.querydslByDnoGroup(countDno);

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

    @GetMapping("/dept/querydsl/querydslByDnameJoin/{dname}")
    public ResponseEntity<Object> querydslByDnameJoin(@PathVariable String dname){
        try{
            List<DeptEmpClassDto> list = deptService.querydslByDnameJoin(dname);

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

    @GetMapping("/dept/querydsl/querydslByDnameJoin2/{dname}")
    public ResponseEntity<Object> querydslByDnameJoin2(@PathVariable String dname){
        try{
            List<DeptEmpClassDto> list = deptService.querydslByDnameJoin2(dname);

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

    @GetMapping("/dept/querydsl/querydslByDnoSub")
    public ResponseEntity<Object> querydslByDnoSub(){
        try{
            List<Department> list = deptService.querydslByDnoSub();

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
