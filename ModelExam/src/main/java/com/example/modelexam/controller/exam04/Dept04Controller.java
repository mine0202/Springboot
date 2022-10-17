package com.example.modelexam.controller.exam04;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam03.Dept03Service;
import com.example.modelexam.service.exam04.Dept04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

// 부서 컨트롤러 findAll() 함수

@Controller
@RequestMapping("/exam04")
public class Dept04Controller {

//   컨트롤러 역할 :  서비스와 웹브라우저의 중간 역할

    @Autowired
    Dept04Service deptService;  //스프링부트 컨테이너의 deptService 객체 하나 받아오기

    @GetMapping("/dept")
    public String getDeptAll(Model model){
        List<Dept> list = deptService.findAll(); //모든 부서정보를 배열에 저장
        model.addAttribute("list",list);
//        log.debug(list.toString()); // list 에 무슨 값이 있을까?
        return "exam04/dept_all.html";
    }

//    id로 조회하는 함수
    @GetMapping("/dept/{dno}")  // 아래 dno 를 입력하면 찾는 정보가 나옴
    public String getDeptId(@PathVariable long dno , Model model){
        Dept dept = deptService.findById(dno);
        model.addAttribute("dept",dept); // 찾은 정보를 dept에 넣어서 보냄
        return "exam04/dept_id.html";

    }

// html 부서정보 추가페이지 링크 생성
    @GetMapping("/dept/addition")
    public String addDept(){
        return "exam04/add_dept.html";  // 부서정보 추가 페이지 경로
    }

//   새로운 객체 정보 DB 저장 함수
//    @ModelAttribute 를 이용해서
    @PostMapping("/dept/add")
    public RedirectView creatdDept(@ModelAttribute Dept dept){
        deptService.save(dept);  // db 저장 서비스 호출

        return new RedirectView("/exam04/dept"); // 전체 부서정보 페이지로 강제이동
    }
}
