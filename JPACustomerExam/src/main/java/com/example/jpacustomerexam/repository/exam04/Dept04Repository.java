package com.example.jpacustomerexam.repository.exam04;


import com.example.jpacustomerexam.dto.DeptEmpClassDto;
import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.model.exam04.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// import 에서 spring  붙은것을 선택, 또는  java.util.* 선택


// join 실습 레파지토리
//  JpaRepository< 모델 , 기본키 속성 타입 ( 자료형 ) >

@Repository
public interface Dept04Repository extends JpaRepository<Department, Integer> {

//     예제 1, 부서와 사원테이블 조인해서 보여주기
//////    테이블이 더 많아지므로 DTO 인터페이스 를 만들어야함
//    native query
    @Query(value = "select d.* , e.eno, e.ename, e.salary from tb_department d, tb_employee e " +
            "where d.dno = e.dno" , nativeQuery = true)
    List<DeptEmpDto> selectNativeJoin();

//    객체쿼리 , JPQL 쿼리
//    Employee 클래스에 @manyToOne 을 만들어서 employee e inner join e.department
//    DTO 사용 : 클래스를 사용해서 생성자안에 보고싶은 값을 넣어줌
//    @ManyToOne 이 있어야 아래 쿼리가능

    @Query(value = "select new com.example.jpacustomerexam.dto.DeptEmpClassDto(d.dno, d.dname,d.loc, e.eno, e.ename, e.salary) " +
            "  from  Employee e inner join e.department d ")
    List<DeptEmpClassDto> selectJoin();


//  참고, 관계 설정없이 객체 쿼리로 조인사용
//     on 을 사용하여 연결 Employee 클래스에 관계 설정이 되어있어서 Emp 클래스를 사용함
//
//    @Query(value = "select " +
//            "new com.example.jpacustomerexam.dto.DeptEmpClassDto(d.dno, d.dname,d.loc, e.eno, e.ename, e.salary) " +
//            "  from  Emp e inner join Department d on e.dno = d.dno")
//    List<DeptEmpClassDto> selectJoin();


}
