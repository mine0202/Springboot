package com.example.jpaexam.controller.exam02;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.service.exam02.Dept02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/exam02")
public class Dept02Controller {

    @Autowired
    Dept02Service deptService;

    @GetMapping("/dept")
    public String getDeptAll(Model model){
        List<Dept> list = deptService.findAll();

        model.addAttribute("list", list);
        return "exam01/dept_all.html";
    }

//    id 로 조회하는 컨트롤러 함수
    @GetMapping("/dept/{dno}")
    public String getDeptId(@PathVariable int dno, Model model){
//      Optional : null 체크하는 객체
        Optional<Dept> optionalDept = deptService.findById(dno);
//        옵셔널객체.get() 옵셔널객체안에있는 부서객체가 나옴
        model.addAttribute("dept", optionalDept.get());
        log.debug(optionalDept.get().toString());
        return "exam02/dept_id.html";
    }
}
