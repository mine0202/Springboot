package com.example.jpacustomerexam.controller.exam02;

import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.service.exam02.Dept02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exam02")
public class Dept02Controller {

    @Autowired
    Dept02Service deptService;

    @GetMapping("/dept/{dname}")
    public ResponseEntity<Object> selectByDname(@PathVariable String dname){
        try{
            List<Dept> list = deptService.selectByDname(dname);
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
    @GetMapping("/dept/dnameAndLoc/{dname}&{loc}")
    public ResponseEntity<Object> selectByDnameAndLoc(@PathVariable String dname, @PathVariable String loc){
        try{
            List<Dept> list = deptService.selectByDnameAndLoc(dname,loc);
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


    @GetMapping("/dept/deotgroupdto")
    public ResponseEntity<Object> selectByGroupFunc(){
        try{
            List<DeptGroupDto> list = deptService.selectByGroupFunc();
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

    @GetMapping("/dept/deotgroupdto/Var/{dno}")
    public ResponseEntity<Object> selectByDnoFunc(@PathVariable String dno){
        try{
            List<DeptGroupDto> list = deptService.selectByDnoFunc(dno);
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
    @GetMapping("/dept/basicfunc/dname")
    public ResponseEntity<Object> selectByBasicFunc(){
        try{
            List<DeptGroupDto> list = deptService.selectByBasicFunc();
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
    @GetMapping("/dept/basicfunc/case")
    public ResponseEntity<Object> selectByCase(){
        try{
            List<DeptGroupDto> list = deptService.selectByCase();
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
