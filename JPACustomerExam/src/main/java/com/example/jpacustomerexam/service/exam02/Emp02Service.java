package com.example.jpacustomerexam.service.exam02;

import com.example.jpacustomerexam.dto.EmpGroupDto;
import com.example.jpacustomerexam.model.Emp;
import com.example.jpacustomerexam.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Emp02Service {
    @Autowired
    EmpRepository empRepository;

    public List<Emp> selectEname(String ename){
        List<Emp> list = empRepository.selectEname(ename);
        return list;
    }

    public List<Emp> selectDescAsc(){
        List<Emp> list = empRepository.selectDescAsc();
        return list;
    }
    public List<Emp> selectCommissionSalary(Integer salary){
        List<Emp> list = empRepository.selectCommissionSalary(salary);
        return list;
    }
    public List<Emp> selectHiredate(String hiredate){
        List<Emp> list = empRepository.selectHiredate(hiredate);
        return list;
    }
    public List<EmpGroupDto> sumSalary(){
        List<EmpGroupDto> list = empRepository.sumSalary();
        return list;
    }
    public List<EmpGroupDto> truncSalary(){
        List<EmpGroupDto> list = empRepository.truncSalary();
        return list;
    }
    public List<EmpGroupDto> maxSal(){
        List<EmpGroupDto> list = empRepository.maxSalary();
        return list;
    }
    public List<EmpGroupDto> sumSalaryJob(){
        List<EmpGroupDto> list = empRepository.sumSalaryJob();
        return list;
    }
    public List<EmpGroupDto> selectCountMax(){
        List<EmpGroupDto> list = empRepository.selectCountMax();
        return list;
    }
    public List<EmpGroupDto> selectOldYoung(){
        List<EmpGroupDto> list = empRepository.selectOldYoung();
        return list;
    }

}
