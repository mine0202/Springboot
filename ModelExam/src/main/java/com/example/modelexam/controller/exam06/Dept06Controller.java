package com.example.modelexam.controller.exam06;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam06.Dept06Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/exam06")
public class Dept06Controller {

//   컨트롤러 역할 :  서비스와 웹브라우저의 중간 역할

    @Autowired
    Dept06Service deptService;

    @GetMapping("/dept")
    public String getDeptAll(Model model){
        List<Dept> list = deptService.findAll(); //모든 부서정보를 배열에 저장
        model.addAttribute("list",list);
//        log.debug(list.toString()); // list 에 무슨 값이 있을까?
        return "exam06/dept_all.html";
    }

//    id로 조회하는 함수
    @GetMapping("/dept/{dno}")  // 아래 dno 를 입력하면 찾는 정보가 나옴
    public String getDeptId(@PathVariable long dno , Model model){
        Dept dept = deptService.findById(dno);
        model.addAttribute("dept",dept); // 찾은 정보를 dept에 넣어서 보냄
        return "exam06/dept_id.html";
    }

// html 부서정보 추가페이지 링크 생성
    @GetMapping("/dept/addition")
    public String addDept(){
        return "exam06/add_dept.html";  // 부서정보 추가 페이지 경로
    }

//   새로운 객체 정보 DB 저장 함수
//    @ModelAttribute 를 이용해서
    @PostMapping("/dept/add")
    public RedirectView creatdDept(@ModelAttribute Dept dept){
        deptService.save(dept);  // db 저장 서비스 호출
        return new RedirectView("/exam06/dept"); // 전체 부서정보 페이지로 강제이동
    }

//    수정 페이지 열기 함수
    @GetMapping("/dept/edition/{dno}")  // 부서번호를 이용해서 그 부서번호에 해당하는 상세정보 선택
    public String editDept(@PathVariable long dno, Model model){
//        부서번호에 해당하는 정보 요청
        Dept dept = deptService.findById(dno);
        model.addAttribute("dept",dept); // 해당정보를 html 로 넘겨줌
        return "exam06/update_dept.html";
    }

//    수정페이지에서 제출(submit)버튼을 눌렀을 때 실행되는 함수
//    업데이트문이 실행될때 사용하는 어노테이션 : @PutMapping
//    클라이언트 족에서 키값 + 객체를 모두 전송해야함  html 규칙
    @PutMapping("/dept/edit/{dno}")
    public RedirectView updateDept(@PathVariable long dno, @ModelAttribute Dept dept){
        deptService.save(dept); // 부서번호가 있는 객체가 넘어옴
        return new RedirectView("/exam06/dept"); // 부서정보 첫페이지 강제이동
    }

//    삭제  함수 : 삭제후 첫 페이지로 강제이동 RedirectView
    @DeleteMapping("/dept/delete/{dno}")
    public RedirectView deletDept(@PathVariable int dno){
        deptService.removeById(dno); // dno 에 해당하는 값 삭제
        return new RedirectView("/exam06/dept");
    }

}
