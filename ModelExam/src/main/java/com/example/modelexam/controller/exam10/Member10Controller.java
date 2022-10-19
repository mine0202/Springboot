package com.example.modelexam.controller.exam10;


import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam10.Member10Service;
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
public class Member10Controller {

    @Autowired
    Member10Service memberService;

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

    @GetMapping("/member/{eno}")
    public ResponseEntity<Object> getMemberId(@PathVariable int eno){
        try{
            Optional<Member> optionalMember = memberService.findById(eno);
            if(optionalMember.isPresent()){
                return new ResponseEntity<>(optionalMember.get(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/member")
    public ResponseEntity<Object> creatMember(@RequestBody Member member){
        try{
            List<Member> list = memberService.save(member);
            return new ResponseEntity<>(list,HttpStatus.CREATED);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    연습
//    Rest Client 이용한 출력을 하세요( Rest Api 프로그램
//    테스트 : Rest Client 테스트
//    Content-Type: application/json
//
//{
//  "eno": 7369,
//  "ename": "TaeGyungfdsafds",
//  "job": "SALESfdsafd",
//  "manager": 7902
//}

    @PutMapping("/member/edit/{eno}")
    public ResponseEntity<Object> updateMember(@PathVariable int eno, @RequestBody Member member){
        try {
            List<Member> list = memberService.save(member);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
