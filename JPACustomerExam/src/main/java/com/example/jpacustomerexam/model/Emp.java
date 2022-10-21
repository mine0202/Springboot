package com.example.jpacustomerexam.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="TB_EMP")
@SequenceGenerator(
        name= "SQ_EMP_GENERATOR"    // 자바에서 사용하는 이름
        , sequenceName = "SQ_EMP"   // 데이터베이스에서 사용하는 이름
        , initialValue = 1           // 초기값
        , allocationSize = 1         // 증가값
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert  // null 무시
@DynamicUpdate  // null 무시
public class Emp extends BaseTimeEntity{
    //          eno ( int ) , 시퀀스 초기값 1 , 1 증가, 기본키 설정
//          ename ( String )
//          job ( String )
//          manager ( int )
//          hiredate ( String )
//          salary ( int )
//          commission ( int )
//          dno ( int )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator ="SQ_EMP_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER")
    private Integer eno;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String ename;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String job;

    @Column(columnDefinition = "NUMBER")
    private Integer manager;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String hiredate;

    @Column(columnDefinition = "NUMBER")
    private Integer salary;

    @Column(columnDefinition = "NUMBER")
    private Integer commission;

    @Column(columnDefinition = "NUMBER")
    private Integer dno;

}
