package com.example.modelexam.controller.exam03;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam03.Dept03Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 부서 컨트롤러 findAll() 함수

@Controller
@RequestMapping("/exam03")
public class Dept03Controller {

//   컨트롤러 역할 :  서비스와 웹브라우저의 중간 역할

    @Autowired
    Dept03Service deptService;  //스프링부트 컨테이너의 deptService 객체 하나 받아오기

    @GetMapping("/dept")
    public String getDeptAll(Model model){
        List<Dept> list = deptService.findAll(); //모든 부서정보를 배열에 저장
        model.addAttribute("list",list);
//        log.debug(list.toString()); // list 에 무슨 값이 있을까?
        return "exam03/dept_all.html";
    }

//    id로 조회하는 함수
    @GetMapping("/dept/{dno}")  // 아래 dno 를 입력하면 찾는 정보가 나옴
    public String getDeptId(@PathVariable long dno , Model model){
        Dept dept = deptService.findById(dno);
        model.addAttribute("dept",dept); // 찾은 정보를 dept에 넣어서 보냄
        return "exam03/dept_id.html";

    }

//    새로운 부서 정보를 요청받아 DB 에 저장하는 save 함수
//    Service 쪽 저장함수 호출
//    참고) 함수는 Rest API 로  출력.
    @PostMapping("/dept/")
//    @ResponseBody : return 값이 json 데이터를 리턴할 수 있게하는 어노테이션
    @ResponseBody
    public List<Dept> saveDept(@RequestBody Dept dept){  // 객체를 body에 입력

        List<Dept> list = deptService.save(dept);
        return list;
    }
}
