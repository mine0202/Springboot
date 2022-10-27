package com.example.jpacustomexam.service.exam03;

import com.example.jpacustomexam.dto.DeptGroupDto;
import com.example.jpacustomexam.model.Dept;
import com.example.jpacustomexam.repository.DeptRepository;
import com.example.jpacustomexam.repository.exam03.Dept03Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : com.example.jpaexam.service.exam01
 * fileName : DeptService
 * author : ds
 * date : 2022-10-20
 * description : 부서 업무 서비스 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
@Service
public class Dept03Service {

    @Autowired
    Dept03Repository deptRepository; // JPA CRUD 함수가 있는 인터페이스

//    1)
    //    전체 부서 정보 조회시 페이징 처리
    public Page<Dept> getDeptDescPage(Pageable pageable) {
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









