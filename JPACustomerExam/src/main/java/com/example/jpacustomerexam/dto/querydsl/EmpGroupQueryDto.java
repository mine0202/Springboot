package com.example.jpacustomerexam.dto.querydsl;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// DTO (data transfer object)
public class EmpGroupQueryDto {

//    querydsl 에서 count 는 long 자료형을 사용함
    Long countVar;
    Integer sumVar;
    Double avgVar;
    Integer maxVar;
    Integer minVar;

}
