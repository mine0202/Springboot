package com.example.jpaexam.service.exam02;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 부서업무 서비스 클래스
@Service
public class Dept02Service {

    @Autowired
    DeptRepository deptRepository;  // JPA CRUD 함수가있음

//    전체 조회 함수
    public List<Dept> findAll(){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Dept> list = deptRepository.findAll();
        return list;
    }

//    id로 조회
    public Optional<Dept> findById(int dno){
        Optional<Dept> optionalDept = deptRepository.findById(dno); // select 문 자동 생성 DB 로 전송
        return optionalDept;
    }
}
