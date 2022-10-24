package com.example.jpacustomerexam.service.exam01;

import com.example.jpacustomerexam.model.Emp;
import com.example.jpacustomerexam.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 부서업무 서비스 클래스
@Service
public class EmpService {

    @Autowired
    EmpRepository empRepository;  // JPA CRUD 함수가있음


//    쿼리 메소드 연습 예제

    public List<Emp> findAllDesc(){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Emp> list = empRepository.findAllByOrderByEnoDesc(); // 전체조회 dno 내림차순
        return list;
    }
//
//    sql : select * from tb_dept order by dno asc;
//    sql 문을 오히려 더 잘 알아야함, 콘솔보고 잘 분석해서 잘 날아가는지 알아야함
    public List<Emp> findAllAsc(){
        List<Emp> list = empRepository.findAllByOrderByEnoAsc();  // 전체조회 dno 오름차순
        return list;
    }


    public List<Emp> findAllEnameAsc(){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Emp> list = empRepository.findAllByOrderByEnameAsc(); // 전체조회 ename 오름차순
        return list;
    }

    public  List<Emp> findAllByEnameContainingOrderByEnameDesc(String ename) {
        List<Emp> list = empRepository.findAllByEnameContainingOrderByEnameDesc(ename);
        return list;
    }
//
    public  List<Emp> findAllByJobContainingOrderByJobDesc(String job){
        List<Emp> list = empRepository.findAllByJobContainingOrderByJobDesc(job);
        return list;
    }

    public List<Emp> findAllByJobAndDno(String job, Integer dno){
        List<Emp> list = empRepository.findAllByJobAndDno(job, dno);
        return list;
    }


    public  List<Emp> findAllBySalaryBetween(int start, int end){
        List<Emp> list = empRepository.findAllBySalaryBetweenOrderByEname(start, end);
        return list;
    }
    public  List<Emp> findAllByJobIgnoreCase(String manager){
        List<Emp> list = empRepository.findAllByJobIgnoreCase(manager);
        return list;
    }
    public  List<Emp> findAllByCommissionNotNull(){
        List<Emp> list = empRepository.findAllByCommissionNotNull();
        return list;
    }
    public  List<Emp> findAllByOrderBySalaryDescEnameAsc(){
        List<Emp> list = empRepository.findAllByOrderBySalaryDescEnameAsc();
        return list;
    }

    public  List<Emp> findAllBySalaryLessThanOrSalaryGreaterThan(int less, int great){
        List<Emp> list = empRepository.findAllBySalaryLessThanOrSalaryGreaterThan(less, great);
        return list;
    }

    public  List<Emp> findAllByCommissionIsOrCommissionIsOrCommissionIs(int a, int b, int c){
        List<Emp> list = empRepository.findAllByCommissionIsOrCommissionIsOrCommissionIs(a, b, c);
        return list;
    }


}
