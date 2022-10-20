package com.example.jpaexam.controller.exam03;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam03.Emp03Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/exam03")
public class Emp03Controller {


    @Autowired
    Emp03Service empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list",list);
        log.debug(list.toString());
        return "exam03/emp/emp_all.html";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
//        모델객체.addAttribute(키,값);  키가 html 의 객체명으로 들어감
        model.addAttribute("emp",optionalEmp.get());
        log.debug(optionalEmp.get().toString());
        return "exam03/emp/emp_id.html";
    }

    @PostMapping("/emp")
    @ResponseBody
    public Emp createEmp(@RequestBody Emp emp){
        Emp emp1 = null;
        try{
            emp1 = empService.save(emp);

        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return emp1;
    }


}