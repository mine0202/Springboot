package com.example.modelexam.service.exam04;

import com.example.modelexam.dao.MemberDao;
import com.example.modelexam.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// @Service 서버가동시 스프링서버에서 객체를 만들어줌
// 객체를 만들어주는 어노테이션 종류 : @Service , @Repository , @Component

@Service
@Slf4j
public class Member04Service {

//  @Autowired :  회원 DAO 객체 가져오기
    @Autowired
    MemberDao memberDao;

    public List<Member> findAll(){
//        DB 접근하여 데이터 가져오기
        List<Member> list2 = memberDao.selectAll();

        return list2;
    }

    public  Member findById(int eno){
        Member member = memberDao.selectById(eno);

        return member;
    }

    public  List<Member> save(Member member){
        List<Member> list = null;
        if(member.getEno()==null){
            int count = memberDao.selectAll().size();
//                사원번호 8000 부터 증가
            int newEno = (count+8000);
            member.setEno(newEno);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            member.setInsertTime(LocalDateTime.now().format(dtf));
            list = memberDao.insert(member);
        }

        return list;
    }
}
