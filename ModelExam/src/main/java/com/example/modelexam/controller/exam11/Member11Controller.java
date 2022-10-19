package com.example.modelexam.controller.exam11;


import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam11.Member11Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/exam11")
public class Member11Controller {

    @Autowired
    Member11Service memberService;

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

//    eno 로 삭제하는 함수 deleteMember
    @DeleteMapping("/member/delete/{eno}")
    public ResponseEntity<Object> deleteMember(@PathVariable int eno){
        try{
            boolean bSuccess = memberService.removeById(eno);
            if(bSuccess){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
