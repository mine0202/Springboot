package com.example.controllerexam.controller.exam05;


// @PathVariable  : @RequestParam 과 유사함


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//url 매개변수 전송방식 :
//      1. 쿼리스트링 방식 @RequestParam :
//              http://localhost:8000/exam05?매개변수=값&매개변수2=값2...
//      2. 파라메터 방식 @PathVariable :
//              http://localhost:8000/exam5/값/값2/...

@Controller
@RequestMapping("/exam05")
public class PathVariableController {


    //    @PathVariable ( url 매개변수) 매개변수의 값이 url 파라메터 방식으로 사용되는 어노테이션
//     {매개변수명} 과  함수의 매개변수 을 일치시키면 { } 안의 저장된 값이 함수의 매개변수값으로 전송됨
//    url 테스트 : http://localhost:8000/exam05/path-variable/kim
    @GetMapping("/path-variable/{name}")
    public String getPathVariable(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "exam05/path_variable.html";
    }

//    연습
//    아래와 같이 url 이 정의되어 파라메터방식을 사용하고
//    컨트롤러 함수를 작성하세요
//    rul : http://localhost:8000/exam05/example01/10
//    결과
//    전달받은 값은 10 입니다.
//    전달받은 값은 20 입니다.
//    전달받은 값은 30 입니다.
    @GetMapping("/example01/{num}")
    public String getPathVariable2(@PathVariable Integer num, Model model) {

        model.addAttribute("num", num);
        model.addAttribute("num2", num+10);
        model.addAttribute("num3", num+20);
        return "exam05/example01.html";
    }
}
