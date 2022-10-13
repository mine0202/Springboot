package com.example.controllerexam.controller.exam01;

// Hello


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 이 클래스가 controller임을 알려주는 어노테이션 :  @Controller
// MVC 디자인 패턴 :
// Model 역할 : 순수 자바 클래스 정보를 저장/ 검색하는 용도 , 속성/생성자/Setter/Getter
// Service 역할 : 업무 로직이 포함된 함수들이 있는 클래스
// Controller 역할 : 뷰(Vue, React, 타임리프,html)와 모델(서비스)를 연결하는 클래스
@Controller
public class HelloController {


//    스프링부트 기본 URL : http://localhost:8080 + /exam01/hello  <- 이렇게 입력하면 get 방식이어서 @GetMapping
//    Hello(매개변수) { }  : @GetMapping(매개변수) 함수위에 붙어있으면
//                      어노테이션의 매개변수와 웹브라우저 URL과 비교해서 일치하면 아래함수가 실행됨
//    @GetMapping : @XXXMapping 어노테이션들은 GET (검색) / POST(추가) / PUT(수정) / DELETE(삭제) 역할을함
//    종합 : url 비교 + GET/POST/PUT/DELET 방식(Method,메소드)비교 를 통해서 아래 함수 실행 @Controller 컨트롤러

//    매개변수 Model 클래스 : @Controller 에서 사용하는 스프링부트와 타임리프간의
//                          데이터 전송을 위한 클래스
//                         HashMap 과 유사한 구조 (키, 값 )
//    함수의 return 값 : 이동할 홈페이지명 (기본경로 : templates/ ) , 문자열
    @GetMapping("/exam01/hello")
    public String Hello(Model model){

        model.addAttribute("greeting","안녕 Springboot");
        model.addAttribute("greeting2","Springboot 처음이지");
        model.addAttribute("greeting3","수고해");
        return "exam01/hello_name_id.html";

    }

//    연습문제
//    웹브라우저 url : http://localhost:8080/exam01/hello2
//    새로만들기
//    출력 : 안녕 hello2 페이지

    @GetMapping("/exam01/hello2")
    public String Hello2( Model model){
        model.addAttribute("greeting4","안녕 hello2 페이지");
        return "exam01/hello2.html";
    }

    //    연습문제
//    웹브라우저 url : http://localhost:8080/exam01/hello4
//    함수 새로만들기
//    화면에 boolean , int, long , double  자료형 변수를 이용해서 html 화면에 출력
//    출력 :
//          true
//          10
//          15
//          20.2
    @GetMapping("/exam01/hello4")
    public String Hello4(Model model){

        boolean boo = true;
        model.addAttribute("greeting4",boo);

        int inT = 10;
        model.addAttribute("greeting5",inT);

        long lonG = 15L;
        model.addAttribute("greeting6",lonG);

        double douB =20.2;
        model.addAttribute ("greeting7",douB);

        return "exam01/hello2.html";

    }


}
