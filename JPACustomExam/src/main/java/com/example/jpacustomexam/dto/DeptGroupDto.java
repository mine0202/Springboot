package com.example.jpacustomexam.dto;

/**
 * packageName : com.example.jpacustomexam.dto
 * fileName : DeptGroupDto
 * author : ds
 * date : 2022-10-24
 * description : 그룹 함수 용도 DTO 인터페이스
 * 함수 : getter 만 필요
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-24         ds          최초 생성
 */
public interface DeptGroupDto {

    //    3번
//    sum
    Integer getSumVar();

    //    avg
    Double getAvgVar();

    //    max
    Integer getMaxVar();

    //    min
    Integer getMinVar();

    //    4번
    String getDnoVar();

    String getDnameVar();

    String getLocVar();

    String getTextVar();

    //    5번
    String getUpperDname();
    String getLowerDname();
    String getLengthDname();
    String getSubstrDname();
//    추가
    String gettruncDname();
    String getTrimDname();
    String getCharDname();

//    6번 문제
    String getIncentive();

}








