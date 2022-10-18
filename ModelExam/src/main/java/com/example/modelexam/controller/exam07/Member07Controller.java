package com.example.modelexam.controller.exam07;


import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam07.Member07Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exam07")
public class Member07Controller {

    @Autowired
    Member07Service memberService;

    @GetMapping("/member")
    public ResponseEntity<Object> getMemberAll(){
        try{
            List<Member> list = memberService.findAll();
            if(list.isEmpty()== false){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
