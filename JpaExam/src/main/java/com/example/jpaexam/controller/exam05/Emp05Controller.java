package com.example.jpaexam.controller.exam05;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam05.Emp05Service;
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
@RequestMapping("/exam05")
public class Emp05Controller {


    @Autowired
    Emp05Service empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list",list);
        log.debug(list.toString());
        return "exam05/emp/emp_all.html";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
//        모델객체.addAttribute(키,값);  키가 html 의 객체명으로 들어감
        model.addAttribute("emp",optionalEmp.get());
        log.debug(optionalEmp.get().toString());
        return "exam05/emp/emp_id.html";
    }

    @GetMapping("/emp/addition")
    public String addEmp(){
        return "exam05/emp/add_emp.html";
    }

    @PostMapping("/emp/add")
    public RedirectView createEmp(@ModelAttribute Emp emp){
        try{
            empService.save(emp);

        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/exam05/emp");
    }

    @GetMapping("/emp/edition/{eno}")
    public String editEmp(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
        model.addAttribute("emp",optionalEmp.get());
        return "exam05/emp/update_emp.html";
    }

    @PutMapping("/emp/edit/{eno}")
    public RedirectView updateEmp(@PathVariable int eno, @ModelAttribute Emp emp){
        try{
            empService.save(emp);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/exam05/emp");
    }
}