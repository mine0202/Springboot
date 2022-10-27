package com.example.jpacustomerexam.service.exam04;

import com.example.jpacustomerexam.dto.DeptEmpClassDto;
import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.repository.exam04.Dept04Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dept04Service {

    @Autowired
    Dept04Repository deptRepository;

    public List<DeptEmpDto> selectNativeJoin(){
        List<DeptEmpDto> list = deptRepository.selectNativeJoin();
        return list;
    }
    public List<DeptEmpClassDto> selectJoin(){
        List<DeptEmpClassDto> list = deptRepository.selectJoin();
        return list;
    }

}
