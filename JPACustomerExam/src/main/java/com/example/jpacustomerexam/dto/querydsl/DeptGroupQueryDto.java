package com.example.jpacustomerexam.dto.querydsl;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// DTO (data transfer object)
public class DeptGroupQueryDto {

//    querydsl 에서 conut 는 long 자료형을 사용함
    Long countVar;
    Integer sumVar;
    Double avgVar;
    Integer maxVar;
    Integer minVar;

//     upper, lower, substr  을 사용하기위해 속성만듬
    String upperVar;
    String lowerVar;
    String substrVar;

    String caseString;

    Long dno;
    Long dnoCount;
}
