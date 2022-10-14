package com.example.controllerexam.controller.exam07;

import com.example.controllerexam.model.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// @RequestBody Post 방식 JSON 데이터를 전달 받음

// @RequestBody : Json 데이터를 springboot 클래스로 변환해서 저장해줌
// 클라이언트(json 속성명) 과 서버쪽 클래스 속성명이 일치해야함
//@RestController Json 데이터를 주고받는 컨트롤러, 뷰와 서비스 연결 담당
@RestController
@RequestMapping("/exam07")
public class ObjectController {

    @PostMapping("/object-variable")
    public List<Member> getObject(@RequestBody Member member) {

        List<Member> list = new ArrayList<>();
        list.add(member);
        return list;  // 객체가 리턴됨

    }

//    위의 예제에서 아래 데이터를 추가해서 Rest 클라이언트 출력해보세요
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
    @PostMapping("/object-variables")
    public List<Member> getObject2(@RequestBody Member member) {

        List<Member> list = new ArrayList<>();
        list.add(member);

        Member member1 = new Member("hong","홍길동");
        list.add(member1);
        Member member2 = new Member("KSU","김유신");
        list.add(member2);
        return list;  // 객체가 리턴됨

    }
}
