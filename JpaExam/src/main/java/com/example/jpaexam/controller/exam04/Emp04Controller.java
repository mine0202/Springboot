package com.example.jpaexam.controller.exam04;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam04.Emp04Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/exam04")
public class Emp04Controller {


    @Autowired
    Emp04Service empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list",list);
        log.debug(list.toString());
        return "exam04/emp/emp_all.html";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
//        모델객체.addAttribute(키,값);  키가 html 의 객체명으로 들어감
        model.addAttribute("emp",optionalEmp.get());
        log.debug(optionalEmp.get().toString());
        return "exam04/emp/emp_id.html";
    }

    @GetMapping("/emp/addition")
    public String addEmp(){
        return "exam04/emp/add_emp.html";
    }


    @PostMapping("/emp/add")
    public RedirectView createEmp(@ModelAttribute Emp emp){
        try{
            empService.save(emp);

        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/exam04/emp");
    }


}