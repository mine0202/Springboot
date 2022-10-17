package com.example.modelexam.service.exam01;

import com.example.modelexam.dao.MemberDao;
import com.example.modelexam.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service 서버가동시 스프링서버에서 객체를 만들어줌
// 객체를 만들어주는 어노테이션 종류 : @Service , @Repository , @Component

@Service
public class MemberService {

//  @Autowired :  회원 DAO 객체 가져오기
    @Autowired
    MemberDao memberDao;

    public List<Member> findAll(){
//        DB 접근하여 데이터 가져오기
        List<Member> list2 = memberDao.selectAll();

        return list2;
    }
}
