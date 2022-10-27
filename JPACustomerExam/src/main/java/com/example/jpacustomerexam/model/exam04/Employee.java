package com.example.jpacustomerexam.model.exam04;

import com.example.jpacustomerexam.model.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


//  조인을 위한 사원테이블 @ManyTOOne 을 사용
@Entity
@Table(name="TB_EMPLOYEE")
@SequenceGenerator(
        name= "SQ_EMPLOYEE_GENERATOR"    // 자바에서 사용하는 이름
        , sequenceName = "SQ_EMPLOYEE"   // 데이터베이스에서 사용하는 이름
        , initialValue = 1           // 초기값
        , allocationSize = 1         // 증가값
)
@Getter
@Setter
@ToString(exclude = "department")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert  // null 무시
@DynamicUpdate  // null 무시
public class Employee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator ="SQ_EMPLOYEE_GENERATOR"
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


//     사원 / 부서 조인
//     오라클로  select d.* , e.eno , e.ename form tb_dept a , tb_emp e where d.dno = e.dno;
//    이퀄조인 equal join 이라고함

//    @Column(columnDefinition = "NUMBER")
//    private Integer dno;
//     위처럼 사용하지 않음

//    @ManyToOne 관계설정
//    객체를 만듬
//    JPA  자동 sql 문 생성  FK (외래키, 포린키 ) 만들어줌
//    성능저하때문에 fetch = FetchType.LAZY 를 사용함
//    사용하는 이유는
//     @ManyToOne 으로 달면 성능저하가 발생 : N+1 쿼리 문제 발생
//    일반적으로 쿼리가 1개가 실행되야하는데 @ManyToOne를 했더니 1개 실행되고 N개의 sql문이 추가로 실행이됨
//    N+1 문제 : 1번째 쿼리 하나당 2번째 쿼리가 또 실행되어 성능이 대폭 저하되는 문제
//    첫번째 + 두번째 : 조인해서 쿼리해야 성능이 개선되지만 , JPA  엔진이 해석을 못하는 문제

//    스프링에서 ( 클래스 ) 객체를 json 데이터로 변한해서 byte로 클라이언트에 전송 ( REST API, @RestController )
//     json 으로 변환해주는 것은 잭슨(Jackson)이 자동변환시켜줌
//    @ManyToOne관계설정시 잭슨이 자동변환할때 에러가 발생함
//    해결책 : @JsonIgnore  ( json으로 변환금지 ), @JsonBackReference 를 사용
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dno")
    @JsonBackReference
    private Department department;


}
