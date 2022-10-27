package com.example.jpacustomerexam.dto;


// Dept& Emp 조인한 결과를 저장할 DTO 인터페이스
public interface DeptEmpDto {

//    부서
     Integer getDno();

     String getDname();

     String getLoc();


//     사원
     Integer getEno();

     String getEname();

     String getSalary();
}
