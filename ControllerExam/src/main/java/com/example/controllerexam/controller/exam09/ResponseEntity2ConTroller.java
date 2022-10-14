package com.example.controllerexam.controller.exam09;
//try ~ catch 함께 ResponseEntity 사용

import com.example.controllerexam.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

    @RestController
    @RequestMapping("/exam09")
    public class ResponseEntity2ConTroller {

        @PostMapping("/object-body")
    //    Object 는 최고 조상
        public ResponseEntity<Object> getObjectBody(@RequestBody Member member){
            try{
                List<Member> list = new ArrayList<>();
                list.add(member);
                if (member != null){
    //              성공하면   클라이언트에 객체 + 성공 메세지 전송
    //                OK  200
                    return new ResponseEntity<Object>(list,HttpStatus.OK);
                }
                else {
    //                NO_CONTENT 204
                    return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                }
            }catch (Exception e){
    //            BAD_REQUEST 400
    //            웹 프로그래밍에서 400번대 메세지는 주로 서버에러.
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }
        }
    }
