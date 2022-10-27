package com.example.jpacustomerexam.repository.exam04;

import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.model.exam04.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Emp04Repository extends JpaRepository<Employee, Integer> {

// 연습
//    employee & department 조인후 ename like 검색하는 함수를 작성해 주세요
//    nativeQuery , 객체 쿼리 양자 선택해서 코딩

    @Query(value = "select d.* , e.eno, e.ename, e.salary from tb_department d, tb_employee e " +
            "where d.dno = e.dno and  e.ename like %:ename%",nativeQuery = true)
    List<DeptEmpDto> selectNativeJoin(@Param("ename") String ename);


}









