package com.example.modelexam.controller.exam03;

import com.example.modelexam.model.Member;
import com.example.modelexam.service.exam03.Member03Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 회원 컨트롤러
@Controller
@RequestMapping("/exam03")
public class Member03Controller {

//    연습 부서클래스를 참고하여
//    MemberService 클래스를 만들고, findByid() 함수를 정의하세요
//    MemberController 클래스를 만들고 getMemberId() 함수를 정의하세요.
//    결과를 뷰로(exam01/member/member_all.html) 로 전송해 보세요( 테이블 형태로 출력 )

    @Autowired
    Member03Service memberService; // memberService 객체 받아오기

//    /member 로 들어오면 해당 서비스를 실행해야함 그래서 memberService 객체를 받아와서 함수를 사용
    @GetMapping("/member")
    public String getMemberAll(Model model){
        List<Member> list2 = memberService.findAll(); // 모든 멤버 정보를 배열에 저장
        model.addAttribute("list2",list2);  // model 은 html 로 정보를 담아서 보내줄 객체
        return "exam03/member/member_all.html";  // 이동할 홈페이지
    }

    @GetMapping("/member/{eno}")
    public String getMemberAll(@PathVariable int eno, Model model){
        Member member1 = memberService.findById(eno); // 해당 멤버 정보를 배열에 저장
        model.addAttribute("member1",member1);  // model 은 html 로 정보를 담아서 보내줄 객체
        return "exam03/member/member_id.html";  // 이동할 홈페이지
    }

    @PostMapping("/member")
    @ResponseBody
    public List<Member> createMember(@RequestBody Member member){
        List<Member> list = memberService.save(member);
        return list;
    }
}
