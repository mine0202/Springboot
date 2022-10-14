package com.example.controllerexam.controller.exam08;

// ResponseEntity 클래스 적용 @RequestBody / @PostMapping 사용

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
@RequestMapping("/exam08")
public class ResponseEntityController {


//    ResponseEntity<Object>(배열객체, 상태메세지)
    @PostMapping("/object-body")
    public ResponseEntity<Object> getObject(@RequestBody Member member){
        List<Member> list = new ArrayList<>();
        list.add(member);
        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }

//    멤버(Member) 클래스의 배열(List) 아래 결과를 추가해서 출력해보세요
//    단, 상태 메세지는 FOUND 로 출력하세요
    //    입력:
//      {
//        "id":"judy",
//        "name":"kim"
//      }

    //    결과 :
//    [
//      {
//        "id":"judy",
//        "name":"kim"
//      },
//      {
//        "id":"hong",
//        "name":"홍길동"
//      },
//      {
//        "id":"KUS",
//        "name":"김유신"
//      }
//    ]
    @PostMapping("/object-body2")
    public ResponseEntity<Object> getObject2(@RequestBody Member member){
        List<Member> list = new ArrayList<>();
        list.add(member);

        Member member1 = new Member("hong", "홍길동");
        list.add(member1);
        Member member2 = new Member("KUS", "김유신");
        list.add(member2);
        return new ResponseEntity<Object>(list, HttpStatus.FOUND);
    }
}
