package com.example.controllerexam.controller.exam02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//  @RequestMapping(공통url)  클래스 위에 정의해서 공통 url 을 적용할 수 있음
@Controller
@RequestMapping("/exam02")
public class ParamController {

// url : http://localhost:8000/exam02/hello-name?name=haha
//  1) 쿼리스트링( Query String) :   ?name=haha  ?매개변수=값
//    @RequestParam  : 커리스트링의 매개변수 == 함수의 매개변수 일치시켜줌
//                  (url 매배변수 값 함수의 매개변수 값으로 전달됨. )
    @GetMapping("/hello-name")
    public String Helloname(Model model,
                            @RequestParam String name
    ){
        model.addAttribute("greeting","안녕하세요"+name);
        return "exam02/hello_name_id.html";
    }


//    연습문제
//    숫자로 url 매개변수를 전달해서 화면에 출력해보세요.
//    url : http://localhost:8000/exam02/hello-name2
//    결과: 10
    @GetMapping("/hello-name2")
    public String Helloname2(Model model,
                            @RequestParam int num
    ){
        model.addAttribute("greeting",num);
        return "exam02/hello2.html";
    }
}
