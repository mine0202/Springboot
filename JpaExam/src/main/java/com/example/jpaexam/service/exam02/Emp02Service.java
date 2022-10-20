package com.example.jpaexam.service.exam02;

import com.example.jpaexam.model.Emp;
import com.example.jpaexam.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Emp02Service {

    @Autowired
    EmpRepository empRepository;

    public List<Emp> findAll(){
        List<Emp> list = empRepository.findAll();
        return list;
    }

    public Optional<Emp> findBYid(int eno){
        Optional<Emp> optionalEmp = empRepository.findById(eno); // JPA 에서 제공해주는 함수
        return optionalEmp;
    }


}