package com.example.controllerexam.controller.exam04;

import com.example.controllerexam.model.Dept;
import com.example.controllerexam.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

// @Controller : 뷰와 서비스를 연결하는 클래스 , 뷰와 연결을 위한 기능을 제공받을 수 있음
@Controller
@RequestMapping("/exam04")
public class ObjectParamController {

    //    url  : http://localhost:8000/exam04/object-param
    @GetMapping("/object-param")
//    @ModelAttribute : url 매개변수의 값을 이름이 일치하는 클래스의 속성에 저장하고
//                      자동으로 Model 객체에 담아서 뷰(타임리프)로 정보를 전송함 (키명 : 객체명)
//    ex) url 테스트 : http://localhost:8000/exam04/object-param?name=kim&id=judy
//          Member 클래스 속성 : id , name  속성
//         url 매개변수명 == 클래스 속성명 (일치 )
    public String getObjectParam(@ModelAttribute Member member) {

        return "exam04/object_param.html";
    }


//    연습문제
//    모델에 부서클래스 Dept 를 만들어서 @ModelAttribute 로 전달받고
//    전달받은 값을 화면에 출력해보세요
//    테스트 url : http://localhost:8000/exam04/example01?dno=10&dName=Accounting&loc=Daegu

    @GetMapping("/example01")
    public String deptParam(@ModelAttribute Dept dept) {
        return "exam04/example01.html";
    }

    //    연습문제
//    모델에 부서클래스 Dept 를 만들어서 @ModelAttribute 로 전달받고 아래의 결과가 출력되게 해보세요
//    결과
//      부서번호       부서명      위치
//        10      Accounting   Daegu
//        20      Sales        Seoul
//        30      Production   Pusan
    @GetMapping("/example02")
    public String deptParam2(@ModelAttribute Dept dept, Model model) {
        List<Dept> list = new ArrayList<>();

        list.add(dept);
        Dept dept2 = new Dept("Sales", "Seoul", 20);
        list.add(dept2);
        Dept dept3 = new Dept("Production", "Pusan", 30);
        list.add(dept3);

        model.addAttribute("list", list);
        return "exam04/example02.html";
    }




}

