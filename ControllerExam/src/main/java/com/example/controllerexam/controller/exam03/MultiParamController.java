package com.example.controllerexam.controller.exam03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//@RequestParam 2개이상 사용하기
// 어노테이션 : 주로 클래스나 함수위에 정의해서 새로운 기능을 추가하는 것
//  ex)  @Controller, @GetMapping, @Setter , @RequestParam , @RequestMapping 등

@Controller
@RequestMapping("/exam03")
public class MultiParamController {

    // url : http://localhost:8000/exam03/hello-name-id?name=Kim&id=judy
    @GetMapping("/hello-name-id")
    public String HelloNameId(@RequestParam String name,
                              @RequestParam String id,
                              Model model) {
//        출력
//              이름은 Kim
//              id 는 judy

        model.addAttribute("greeting", "이름은 " + name);
        model.addAttribute("greeting2", "id는 " + id);
        return "exam03/hello_name_id.html";
    }

    // url : http://localhost:8000/exam03/hello-array?name=Kim&id=judy
    @GetMapping("/hello-array")
    public String HelloArray(@RequestParam String name,
                             @RequestParam String id,
                             Model model) {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(id);

        model.addAttribute("list", list);
        return "exam03/hello_array.html";
    }

    //    연습문제
//    URL 매개변수로 dno=10 , dName=Accounting, loc=Seoul
//    http://localhost:8000/exam03/depart-array?dno=10&dName=Accounting&loc=Seoul
//    결과
//        부서번호 : 10
//        부서명 : Accounting
//        위치 : Seoul
    @GetMapping("/depart-array")
    public String departArray(@RequestParam String dno,
                              @RequestParam String dName,
                              @RequestParam String loc,
                              Model model) {
//    List<String> list = new ArrayList<>();
//    list.add(dno);
//    list.add(dName);
//    list.add(loc);

        model.addAttribute("list", "부서번호 : " + dno);
        model.addAttribute("list1", "부서명 : " + dName);
        model.addAttribute("list2", "위치 : " + loc);
        return "exam03/depart_array.html";
    }

    // 연습문제
//    위에서 작성항 함수를 리스트에 담아서 html 로 전달하세요
//    html 반복문 사용
    @GetMapping("/depart-array2")
    public String departArray2(@RequestParam String dno,
                               @RequestParam String dName,
                               @RequestParam String loc,
                               Model model) {
        List<String> list = new ArrayList<>();
        list.add(dno);
        list.add(dName);
        list.add(loc);

//        model.addAttribute("list", "부서번호 : " + list.get(0));
//        model.addAttribute("list1", "부서명 : " + list.get(1));
//        model.addAttribute("list2", "위치 : " + list.get(2));
        model.addAttribute("list",list);

        return "exam03/depart_array.html";
    }
}
