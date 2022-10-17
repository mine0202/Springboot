package com.example.controllerexam.controller.exam10;


import com.example.controllerexam.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// ResponseEntity 의 함수호출 예제
@RestController   // 함수의 리턴값이 json 데이터로 나옴( 클라이언트로 전송됨 )
@RequestMapping("/exam10")  // 공통url 설정
public class ReponseEntity3Controller {

//    @PostMapping 클라이언트 쪽에서 객체를 전송 ( http 프로토클 상에- html body쪽에 있음)
//    네트웍 전송 : 헤더(application/json,text 등) + 바디(데이터) + 상태 ( OK, NO_CONTENT, 등)
    @PostMapping("/object-body")
    public ResponseEntity<List<Member>> getObjectBody(@RequestBody Member member){
        List<Member> list = new ArrayList<>();

        try{
            list.add(member);

            if(list != null){
//                데이터가 있으면 성공메세지 전송
                return ResponseEntity.ok().body(list);  // ok 값을 body의 list 에 담아서 리턴
            }
            else {
//                            데이터가 없을때 전송방식  .noContent()
//                빌더 패턴 : 주로 .build() 함수 붙음
//                          생성자를 대신하여 객체를 생성할 때 사용 : 빌더패턴
//                          생성자의 매개변수가 10개라면 . A,B,C,D,E,F,G,.... 중간 값을 빼고 싶을때 번거로움
//                          그래서 bulid 패턴은 자기가 사용하고 싶은 매개변수만 조합해서 사용.
//                           .A().D().J().build()  이렇게 사용함
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
//            에러나면 에러메세지 전송
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
