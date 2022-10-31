package com.example.jpacustomerexam.service.exam07;

import com.example.jpacustomerexam.dto.DeptEmpClassDto;
import com.example.jpacustomerexam.dto.querydsl.DeptGroupQueryDto;
import com.example.jpacustomerexam.model.exam04.Department;
import com.example.jpacustomerexam.repository.exam07.Dept07Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class Dept07Service {


    @Autowired
    Dept07Repository deptRepository;

    public List<Department> querydslByDname(String dname){
        List<Department> list = deptRepository.querydslByDname(dname);
        return list;
    }

    public List<Department> querydslByDnameAndLoc(String dname, String loc){
        List<Department> list = deptRepository.querydslByDnameAndLoc(dname, loc);
        return list;
    }

    public List<DeptGroupQueryDto> querydslByGroupfunc(){
        List<DeptGroupQueryDto> list = deptRepository.querydslByGroupfunc();
        return list;
    }
    public List<Department> querydslByDeptGt(int dno){
        List<Department> list = deptRepository.querydslByDeptGt(dno);
        return list;
    }
    public List<DeptGroupQueryDto> querydslByBasicFunc(){
        List<DeptGroupQueryDto> list = deptRepository.querydslByBasicFunc();
        return list;
    }

    public List<DeptGroupQueryDto> querydslByCasewhen(){
        List<DeptGroupQueryDto> list = deptRepository.querydslByCasewhen();
        return list;
    }
    public List<DeptGroupQueryDto> querydslByDnoGroup(long countDno){
        List<DeptGroupQueryDto> list = deptRepository.querydslByDnoGroup(countDno);
        return list;
    }

    @Transactional
    public List<DeptEmpClassDto> querydslByDnameJoin(String dname){
        List<DeptEmpClassDto> list = deptRepository.querydslByDnameJoin(dname);
        return list;
    }


//    @Transactional : 함수 안에  insert/update/delete  여러개 섞여 있는 경우 모든 sql 문이 작성되고 나서
//                  마지막에 commit을 실행해서 작동하는 방식
    @Transactional
    public List<DeptEmpClassDto> querydslByDnameJoin2(String dname){
        List<DeptEmpClassDto> list = deptRepository.querydslByDnameJoin2(dname);
        return list;
    }


    public List<Department> querydslByDnoSub(){
        List<Department> list = deptRepository.querydslByDnoSub();
        return list;
    }


}
