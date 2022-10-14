package com.example.controllerexam.controller.exam06;


import com.example.controllerexam.model.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam06")
public class MultiPathController {
    @GetMapping("/multi-var/id/{id}/name/{name}")
    public String getMultiPath(@PathVariable String id,
                               @PathVariable String name,
                               Model model) {

        model.addAttribute("id", id);
        model.addAttribute("name", name);

        return "exam06/multi_var.html";
    }

//    연습
//    아래 url 을 보고 함수를 잣겅하세요
//    뷰에 전달할때는 List 담아 전달하고 테이블 형태로 출력하세요(부트스트랩이용)
//    url  :  http://localhost:8000/exam06/example01/dno/10/dName/Sales/loc/Pusan

    @GetMapping("/example01/dno/{dno}/dName/{dName}/loc/{loc}")
    public String getDeptArray(@PathVariable String dno,
                               @PathVariable String dName,
                               @PathVariable String loc,
                               Model model) {
        List<String> list = new ArrayList<>();
        list.add(dno);
        list.add(dName);
        list.add(loc);
        model.addAttribute("list", list);
        return "/exam06/example01.html";
    }

    //    연습
//    위의 예제에서 부서 정보를 Dept 생성자를 이용해서 저장하며
//    각 Dept 객체를 list 담아서(객체 배열) 뷰(html)로 전달하고
//    타임리프에서 반복문으로 출력해 보세요
//    결과
//    No      부서번호          부서명       위치
//    1        10            Sales         Pusan
//    2        20            Accounting    Daegu
//    3        30            Production    Seoul
    @GetMapping("/example02/dno/{dno}/dName/{dName}/loc/{loc}")
    public String getDeptArray1(@PathVariable Integer dno,
                                @PathVariable String dName,
                                @PathVariable String loc,
                                Model model) {
        List<Dept> list = new ArrayList<>();
        int iCount = 1;

        Dept dept = new Dept(dName,loc,dno,iCount);
        list.add(dept);
        Dept dept1 = new Dept("Accounting", "Daegu", 20,++iCount);
        list.add(dept1);
        Dept dept2 = new Dept("Production", "Seoul", 30,++iCount);
        list.add(dept2);


        model.addAttribute("list", list);
        return "/exam06/example02.html";
    }
}
