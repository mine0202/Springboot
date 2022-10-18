package com.example.modelexam.controller.exam05;

import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam05.Member05Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

// 회원 컨트롤러
@Slf4j
@Controller
@RequestMapping("/exam05")
public class Member05Controller {

    @Autowired
    Member05Service memberService; // memberService 객체 받아오기

//    /member 로 들어오면 해당 서비스를 실행해야함 그래서 memberService 객체를 받아와서 함수를 사용
    @GetMapping("/member")
    public String getMemberAll(Model model){
        List<Member> list = memberService.findAll(); // 모든 멤버 정보를 배열에 저장
        model.addAttribute("list",list);  // model 은 html 로 정보를 담아서 보내줄 객체
        return "exam05/member/member_all.html";  // 이동할 홈페이지
    }

    @GetMapping("/member/addition")
    public String addMember(){
        return "exam05/member/add_member.html";
    }

    @PostMapping("/member/add")
    public RedirectView createMember(@ModelAttribute Member member){
        log.debug("createMember 시작");
        memberService.save(member);
        log.debug("createMember 끝");

        return new RedirectView("/exam05/member");
    }

//      수정페이지 열기 함수
    @GetMapping("/member/edition/{eno}")
    public String editMember(@PathVariable int eno, Model model){
//        사원번호에 해당하는 정보 요청
        Member member = memberService.findById(eno);
        model.addAttribute("member",member);
        return "exam05/member/update_member.html";
    }

//    수정페이제 제출버튼 눌렀을때 실행되는 함수
    @PutMapping("/member/edit/{eno}")
    public RedirectView updateMember(@PathVariable int eno, @ModelAttribute Member member){
        memberService.save(member); // 사원번호가 있는 객체가 넘어옴
        return new RedirectView("/exam05/member"); // 첫페이지 이동
    }
}
