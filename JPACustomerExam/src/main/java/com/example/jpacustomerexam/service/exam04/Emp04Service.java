package com.example.jpacustomerexam.service.exam04;

import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.repository.exam04.Emp04Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Emp04Service {

    @Autowired
    Emp04Repository empRepository;

    public List<DeptEmpDto> selectNativeJoin(String ename){
        List<DeptEmpDto> list = empRepository.selectNativeJoin(ename);
        return list;
    }
}
