package com.example.jpacustomerexam.dto;

import lombok.*;

// 객체쿼리 JPQL 의 결과를 저장할 DTO 클래스
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptEmpClassDto {

//   부서
    private Integer dno;


    private String dname;

    private String loc;

//    사원
    private Integer eno;
    private String ename;
    private String job;

    private Integer manager;
    private String hiredate;
    private Integer salary;
    private Integer commission;

//    마지막 dno 는 없어짐 부서클래스 위에 있음

//    생성자를 이용해서 필요한 속성만 출력하기

    public DeptEmpClassDto(Integer dno, String dname, String loc, Integer eno, String ename, Integer salary) {
        this.dno = dno;
        this.dname = dname;
        this.loc = loc;
        this.eno = eno;
        this.ename = ename;
        this.salary = salary;
    }
}
