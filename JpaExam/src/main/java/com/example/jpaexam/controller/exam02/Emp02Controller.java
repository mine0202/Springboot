package com.example.jpaexam.controller.exam02;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam02.Emp02Service;
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
public class Emp02Controller {


    @Autowired
    Emp02Service empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list",list);
        log.debug(list.toString());
        return "exam01/emp/emp_all.html";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
//        모델객체.addAttribute(키,값);  키가 html 의 객체명으로 들어감
        model.addAttribute("emp",optionalEmp.get());
        log.debug(optionalEmp.get().toString());
        return "exam02/emp/emp_id.html";
    }


}