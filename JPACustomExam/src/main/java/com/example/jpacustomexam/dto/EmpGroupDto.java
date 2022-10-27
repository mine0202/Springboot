package com.example.jpacustomexam.dto;

/**
 * packageName : com.example.jpacustomexam.dto
 * fileName : DeptGroup
 * author : ds
 * date : 2022-10-25
 * description : Group by 결과 저장할 DTO 인터페이스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-25         ds          최초 생성
 */
public interface EmpGroupDto {

//    저장할 함수 : getter 만들기
//    4)
    Integer getDno();
    String getJob();
    Integer getSumSalary();

//    5)
    Double getAvgSalary();

//    6)
    Integer getMaxSalary();

//    9)
    Integer getCountEno();
    Integer getSalaryEno();

//    10)
    String getMaxHiredate();
    String getMinHiredate();
}








