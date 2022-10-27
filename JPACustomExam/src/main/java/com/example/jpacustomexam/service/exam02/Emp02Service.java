package com.example.jpacustomexam.service.exam02;

import com.example.jpacustomexam.dto.EmpGroupDto;
import com.example.jpacustomexam.model.Emp;
import com.example.jpacustomexam.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.jpaexam.service.exam01
 * fileName : EmpService
 * author : ds
 * date : 2022-10-20
 * description : Emp 서비스 클래스 ( 업무 로직 짜는 클래스 )
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
@Service
public class Emp02Service {

    @Autowired
    EmpRepository empRepository;

//    1)
    public List<Emp> selectByEname(String ename) {
        List<Emp> list = empRepository.selectByEname(ename);

        return list;
    }

//    2)
    public List<Emp> selectDescAsc() {
        List<Emp> list = empRepository.selectDescAsc();

        return list;
    }

//    3)
    public List<Emp> selectSalary(int salary) {
        List<Emp> list = empRepository.selectSalary(salary);

        return list;
    }

//    4)
    public List<Emp> selectHiredate(String first, String last ) {
        List<Emp> list = empRepository.selectHiredate(first, last);

        return list;
    }

//    5)
    public List<EmpGroupDto> selectGroupDnoJob() {
        List<EmpGroupDto> list = empRepository.selectGroupDnoJob();

        return list;
    }

//    6)
    public List<EmpGroupDto> selectGroupDnoTrunc() {
        List<EmpGroupDto> list = empRepository.selectGroupDnoTrunc();

        return list;
    }

//    7)
    public List<EmpGroupDto> selectGroupDnoMax() {
        List<EmpGroupDto> list = empRepository.selectGroupDnoMax();

        return list;
    }

//    8)
    public List<EmpGroupDto> selectGroupJobHaving() {
        List<EmpGroupDto> list = empRepository.selectGroupJobHaving();

        return list;
    }

//    8)
    public List<EmpGroupDto> selectGroupSumMax() {
        List<EmpGroupDto> list = empRepository.selectGroupSumMax();

        return list;
    }

//    9)
    public List<EmpGroupDto> selectGroupHiredate() {
        List<EmpGroupDto> list = empRepository.selectGroupHiredate();

        return list;
    }
}











