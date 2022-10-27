package com.example.jpacustomerexam.controller.exam04;

import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.service.exam04.Emp04Service;
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
@RequestMapping("/exam04")
public class Emp04Controller {

    @Autowired
    Emp04Service empService;


    @GetMapping("/dept/native/join/{ename}")
    public ResponseEntity<Object> selectNativeJoin(@PathVariable String ename){
        try{
            List<DeptEmpDto> list = empService.selectNativeJoin(ename);

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
