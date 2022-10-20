package com.example.jpaexam.controller.exam03;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.service.exam03.Dept03Service;
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
public class Dept03Controller {

    @Autowired
    Dept03Service deptService;

    @GetMapping("/dept")
    public String getDeptAll(Model model){
        List<Dept> list = deptService.findAll();

        model.addAttribute("list", list);
        return "exam03/dept_all.html";
    }

//    id 로 조회하는 컨트롤러 함수
    @GetMapping("/dept/{dno}")
    public String getDeptId(@PathVariable int dno, Model model){
//      Optional : null 체크하는 객체
        Optional<Dept> optionalDept = deptService.findById(dno);
//        옵셔널객체.get() 옵셔널객체안에있는 부서객체가 나옴
        model.addAttribute("dept", optionalDept.get());
        log.debug(optionalDept.get().toString());
        return "exam03/dept_id.html";
    }

//    insert 함수
//   @ResponseBody : return 값이 json 값이나오게하는 어노테이션 , 주로 함수위에 정의
    @PostMapping("/dept")
    @ResponseBody
    public Dept createDept(@RequestBody Dept dept){  // 객체가 통으로 넘어.. @RequestBody
        Dept dept1=null;
        try{
            dept1 = deptService.save(dept); // insert 문 실행

        }catch (Exception e){
            log.debug(e.getMessage()); // 에러 콘솔 출력
        }
        return dept1;
    }
}
