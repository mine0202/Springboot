package com.example.jpaexam.controller.exam01;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam01.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/exam01")
public class EmpController {

//    연습
//      Emp 클래스를 만들고, repository, service, controller 클래스를 정의
//    함수는 findAll() 정의
//    데이터를 확인할 수 있는 emp/emp_all.html 만들기
//    단, 부서 클래스를 참고하고, Emp 속성은 아래를 참고
//    Emp :
//          eno ( int ) , 시퀀스 초기값 1 , 1 증가, 기본키 설정
//          ename ( String )
//          jop ( String )
//          manager ( int )
//          hiredate ( String )
//          salary ( int )
//          commission ( int )
//          dno ( int )

    @Autowired
    EmpService empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list",list);
        log.debug(list.toString());
        return "exam01/emp/emp_all.html";
    }
}
