package com.example.jpaexam.controller.exam06;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.service.exam06.Emp06Service;
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
@RequestMapping("/exam06")
public class Emp06Controller {


    @Autowired
    Emp06Service empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list",list);
        log.debug(list.toString());
        return "exam06/emp/emp_all.html";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
//        모델객체.addAttribute(키,값);  키가 html 의 객체명으로 들어감
        model.addAttribute("emp",optionalEmp.get());
        log.debug(optionalEmp.get().toString());
        return "exam06/emp/emp_id.html";
    }

    @GetMapping("/emp/addition")
    public String addEmp(){
        return "exam06/emp/add_emp.html";
    }

    @PostMapping("/emp/add")
    public RedirectView createEmp(@ModelAttribute Emp emp){
        try{
            empService.save(emp);

        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/exam06/emp");
    }

    @GetMapping("/emp/edition/{eno}")
    public String editEmp(@PathVariable int eno, Model model){
        Optional<Emp> optionalEmp = empService.findBYid(eno);
        model.addAttribute("emp",optionalEmp.get());
        return "exam06/emp/update_emp.html";
    }

    @PutMapping("/emp/edit/{eno}")
    public RedirectView updateEmp(@PathVariable int eno, @ModelAttribute Emp emp){
        try{
            empService.save(emp);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/exam06/emp");
    }

//    Emp 컨트롤러/서비스 클래스의  .deleteEmp() 함수를 추가
//     exam06/emp/update_emp.html 페이지를 삭제 버튼 추가 , 제출버튼을 클릭하면 회원이 삭제되도록
//    컨트롤러/서비스 함수를 정의하세요.
//    단, 회원이 삭제되면 첫페이지로 Redirect 될 수 있도록 하세요.

    @DeleteMapping("/emp/delete/{eno}")
    public RedirectView deleteEmp(@PathVariable int eno){
        try{
            empService.removeById(eno);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/exam06/emp");
    }
}