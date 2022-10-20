package com.example.jpaexam.service.exam01;

import com.example.jpaexam.model.Dept;
import com.example.jpaexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 부서업무 서비스 클래스
@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository;

//    전체 조회 함수
    public List<Dept> findAll(){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Dept> list = deptRepository.findAll();
        return list;
    }
}
