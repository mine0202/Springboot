package com.example.jpacustomerexam.service.exam02;

import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Dept02Service {

    @Autowired
    DeptRepository deptRepository;


    public List<Dept> selectByDname(String dname){
        List<Dept> list = deptRepository.selectByDname(dname);
        return list;
    }
    public List<Dept> selectByDnameAndLoc(String dname,String loc){
        List<Dept> list = deptRepository.selectByDnameAndLoc(dname, loc);
        return list;
    }

    public List<DeptGroupDto> selectByGroupFunc(){
        List<DeptGroupDto> list = deptRepository.selectByGroupFunc();
        return list;
    }

    public List<DeptGroupDto> selectByDnoFunc(String dno){
        List<DeptGroupDto> list = deptRepository.selectByDnoFunc(dno);
        return list;
    }
}
