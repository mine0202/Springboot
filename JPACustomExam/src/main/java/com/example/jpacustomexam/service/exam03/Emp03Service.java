package com.example.jpacustomexam.service.exam03;

import com.example.jpacustomexam.dto.EmpGroupDto;
import com.example.jpacustomexam.model.Emp;
import com.example.jpacustomexam.repository.EmpRepository;
import com.example.jpacustomexam.repository.exam03.Emp03Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class Emp03Service {

    @Autowired
    Emp03Repository empRepository;

//    1)
    public Page<Emp> findAll(Pageable pageable) {
        Page<Emp> list = empRepository.findAll(pageable);

        return list;
    }

//    2)
    public Page<Emp> findAllByOrderByEnameDesc(Pageable pageable) {
        Page<Emp> list = empRepository.findAllByOrderByEnameDesc(pageable);

        return list;
    }

//    3)
    public Page<Emp> findAllBySalaryBetweenAndCommissionIsNotNull(int first,
                                                                  int last,
                                                                  Pageable pageable)
    {
        Page<Emp> list = empRepository
                .findAllBySalaryBetweenAndCommissionIsNotNull(first, last, pageable);

        return list;
    }

//    4)
    public Page<Emp> findAllByEnameContaining(String ename, Pageable pageable)
    {
        Page<Emp> list = empRepository
                .findAllByEnameContaining(ename, pageable);

        return list;
    }

//    5)
    public Page<Emp> findAllByDnoAndSalaryGreaterThanEqual(int dno, int salary, Pageable pageable)
    {
        Page<Emp> list = empRepository
                .findAllByDnoAndSalaryGreaterThanEqual(dno, salary, pageable);

        return list;
    }

//    5)
    public Page<Emp> selectBySalaryPage(int first, int last, Pageable pageable)
    {
        Page<Emp> list = empRepository
                .selectBySalaryPage(first, last, pageable);

        return list;
    }

//    5)
    public Page<Emp> selectByHireDatePage(String first, String last, Pageable pageable)
    {
        Page<Emp> list = empRepository
                .selectByHireDatePage(first, last, pageable);

        return list;
    }

//    5)
    public Page<Emp> selectBySalaryDnoPage(int first,
                                           int last,
                                           int firstDno,
                                           int lastDno,
                                           Pageable pageable)
    {
        Page<Emp> list = empRepository
                .selectBySalaryDnoPage(first, last, firstDno, lastDno,  pageable);

        return list;
    }

//    6)
    public Page<Emp> selectByHiredateContainingPage(String hiredate,
                                           Pageable pageable)
    {
        Page<Emp> list = empRepository
                .selectByHiredateContainingPage(hiredate,  pageable);

        return list;
    }

//    7)
    public Page<Emp> selectByCommissionPage(int commission,
                                           Pageable pageable)
    {
        Page<Emp> list = empRepository
                .selectByCommissionPage(commission,  pageable);

        return list;
    }

}











