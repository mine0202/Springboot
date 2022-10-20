package com.example.jpaexam.service.exam04;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 부서업무 서비스 클래스
@Service
public class Dept04Service {

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

//    insert
    public Dept save(Dept dept){
// save 함수특징: 기본키 값이 dept에 없으면 insert , 기본키값이 있으면 update 로 작동하게 JPA 가 만들어줌
        Dept dept1 = deptRepository.save(dept);
        return dept1;
    }
}
