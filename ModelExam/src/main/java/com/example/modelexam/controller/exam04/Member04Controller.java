package com.example.modelexam.controller.exam04;

import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam04.Member04Service;
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
@RequestMapping("/exam04")
public class Member04Controller {

    @Autowired
    Member04Service memberService; // memberService 객체 받아오기

//    /member 로 들어오면 해당 서비스를 실행해야함 그래서 memberService 객체를 받아와서 함수를 사용
    @GetMapping("/member")
    public String getMemberAll(Model model){
        List<Member> list2 = memberService.findAll(); // 모든 멤버 정보를 배열에 저장
        model.addAttribute("list2",list2);  // model 은 html 로 정보를 담아서 보내줄 객체
        return "exam04/member/member_all.html";  // 이동할 홈페이지
    }

    @GetMapping("/member/addition")
    public String addMember(){
        return "exam04/member/add_member.html";
    }

    @PostMapping("/member/add")
    public RedirectView createMember(@ModelAttribute Member member){
        log.debug("createMember 시작");
        memberService.save(member);
        log.debug("createMember 끝");

        return new RedirectView("/exam04/member");
    }
}
