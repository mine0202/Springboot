package com.example.modelexam.controller.exam01;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam01.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 부서 컨트롤러 findAll() 함수
// 로깅 라이브러리 : log4j ( 결함발생) 하여 log4j2 로 바꿈. 최근에는 logback 를 사용함
@Slf4j
@Controller
@RequestMapping("/exam01")
public class DeptController {

//   컨트롤러 역할 :  서비스와 웹브라우저의 중간 역할

    @Autowired
    DeptService deptService;  //스프링부트 컨테이너의 deptService 객체 하나 받아오기

    @GetMapping("/dept")
    public String getDeptAll(Model model){
        List<Dept> list = deptService.findAll(); //모든 부서정보를 배열에 저장
        model.addAttribute("list",list);

//        (정보가 제일 많음 ) trace > warn > info > debug > error (정보가 제일 작음 ) : 로깅레벨
        log.debug(list.toString()); // list에 어떤 값이 있을까
        return "exam01/dept_all.html";
    }
}
