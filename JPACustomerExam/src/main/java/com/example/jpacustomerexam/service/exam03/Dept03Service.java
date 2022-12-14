package com.example.jpacustomerexam.service.exam03;

import com.example.jpacustomerexam.dto.DeptGroupDto;
import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.repository.exam03.Dept03Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Dept03Service {

    @Autowired
    Dept03Repository deptRepository;

    public Page<Dept> findAllByOrderByDnoDesc(Pageable pageable){
        Page<Dept> page = deptRepository.findAllByOrderByDnoDesc(pageable);
        return page;
    }


    //    2)
    //    전체 부서 정보 조회시 페이징 처리
    public Page<Dept> getDeptAllPage(Pageable pageable) {
//        쿼리메소드 안만들어도 됨
        Page<Dept> page = deptRepository.findAll(pageable); // findAll(페이징객체) 있음

        return page;
    }

    //    3)
    public Page<Dept> findAllByDnameContaining(String dname, Pageable pageable) {

        Page<Dept> page = deptRepository.findAllByDnameContaining(dname, pageable);

        return page;
    }

    //    4) @Query 이용 페이징 처리 함수
    public Page<Dept> selectByDnameAndLocPage(String dname,
                                              String loc,
                                              Pageable pageable) {

        Page<Dept> page = deptRepository.selectByDnameAndLocPage(dname, loc, pageable);

        return page;
    }

    //    4) @Query 이용 페이징 처리 함수
    public Page<DeptGroupDto> selectByCustomDeptPage(Pageable pageable) {

        Page<DeptGroupDto> page = deptRepository.selectByCustomDeptPage(pageable);

        return page;
    }

    //    5) @Query 이용 페이징 처리 함수
    public Page<DeptGroupDto> selectByCasePage(Pageable pageable) {

        Page<DeptGroupDto> page = deptRepository.selectByCasePage(pageable);

        return page;
    }
}
