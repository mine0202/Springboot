package com.example.controllerexam.controller.exam06;

//@RestController , @PathVariable


import com.example.controllerexam.model.Board;
import com.example.controllerexam.model.Dept;
import com.example.controllerexam.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Vue 와 연동하기 위해 @RestController 사용

@RestController
@RequestMapping("/exam06")
public class MultiPathRestController {

//   @RestController는  Model 을 사용하지 않음.
//  @RestController : 뷰와 서비스를 연결하는 컨트롤러인데 ,
//                  결과를 JSON 객체 형태로 리턴함(데이터만 전송)
//    Rest Application  Vue <-> Springboot 데이터만 통신으로 (axios ) 주고받음 (CSR)

    @GetMapping("/multi-rest/id/{id}/name/{name}")
    public Member getMultiRest(@PathVariable String id,
                               @PathVariable String name) {
        Member member = new Member(id, name);

//        @RestController는 return 객체를 함
        return member;
//        rest 클라이언트를 열기 위해서는 새로운 설치파일을 받아야함.
//        인텔리제이는 rest클라이언트가 포함되어있음
    }

    //    연습
//    부서클래스의 속성에 아래와 같이 데이터를 저장해서 Rest Client 에 출력해보세요
//    결과
//        {
//          "num" : 1,
//          "dno" : 10,
//          "loc" : "Seoul",
//          "dName" : "Sales",
//         }
    @GetMapping("/multi-rest2/dName/{dName}/loc/{loc}/dno/{dno}/num/{num}")
    public Dept getMultiRest2(@PathVariable String dName,
                              @PathVariable String loc,
                              @PathVariable Integer dno,
                              @PathVariable Integer num
                              ) {
        Dept dept = new Dept(dName,loc,dno,num);
        return dept;
    }

//    연습
//    게시판(Board) 모델 클래스를 만들어서 아래와 같이 출력되도록 Rest 클라이언트에 출력하세요
//    단, Rest API Get 방식을 사용하고, url 전달방식은 파라메터 방식을 사용하세요.
//    결과
//       {
//          "no":10,
//          "title":"제목",
//          "content":"내용",
//          "count":1
//        }

    @GetMapping("/multi-rest3/no/{no}/title/{title}/content/{content}/count/{count}")
    public Board getMultiRest3(@PathVariable int no,
                               @PathVariable String title,
                               @PathVariable String content,
                               @PathVariable int count
    ) {
        Board board = new Board(no,title,content,count);
        return board;
    }

}
