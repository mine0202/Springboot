package com.example.modelexam.controller.exam09;


import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam09.Member09Service;
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
@RequestMapping("/exam09")
public class Member09Controller {

    @Autowired
    Member09Service memberService;

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
//    1) 연습문제 : 부서클래스를 참고하여
//         Member08Service 클래스에 findById() 함수를 수정하세요( Optional 이용 )
//         Member08Controller 클래스에 getMemberId() 함수를 추가하세요 ( Optional 이용 )
//    단, Rest Client 이용한 출력을 하세요 ( Rest API 프로그램. @RestController 를 이용 )

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
}
