package com.example.jpacustomerexam.model.exam04;

import com.example.jpacustomerexam.model.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="TB_DEPARTMENT")
@SequenceGenerator(
        name= "SQ_DEPARTMENT_GENERATOR"    // 자바에서 사용하는 이름
        , sequenceName = "SQ_DEPARTMENT"   // 데이터베이스에서 사용하는 이름
        , initialValue = 1           // 초기값
        , allocationSize = 1         // 증가값
)
@Getter
@Setter
@ToString(exclude = "employee")
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate

// 관계 조인을 위한 클래스, 관계맺기
//          종류 : @ManyToOne, @OneToMany, @OneToOne, ( 추천안함 @ManyToMany )
//  기본키 , 참조키
// 부서는 하나에 사원은 많이 있으므로 @OneToMany 를 사용함
// @ManyToOne  : 단방향 조인 ( 추천 )  , 부서클래스에 @OneToMany 를 달지 않음, 사원클래스 @ManyToOne 달기
//               양방향 조인 ( 꼭 필요하다면 사용 ) , 부서클래스에 @OneToMany 를 달기, 사원클래스 @ManyToOne 달기
// JPA 클래스에서만 단향향, 양방향이 있어서 1:1 , 1:다, 다:다 에 적용되어 6가지가 있음

public class Department extends BaseTimeEntity {

    @Id  // 테이블에 하나이상의 기본키가 필요하므로 변하지 않는 값을 기본키로 만듬
    @GeneratedValue(strategy = GenerationType.SEQUENCE   // 시퀀스사용
            , generator = "SQ_DEPARTMENT_GENERATOR"
    )

    private Integer dno;


    @Column(columnDefinition = "VARCHAR2(255)")
    private String dname;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String loc;

//    양방향 관계를  위한  설정 추가
//    양방향 관계 특징
//    1. Many 쪽 클래스 @ManyToOne 추가, 실제 테이블의 dno 컬럼이 생성되고, FK 생성됨
//          FK 를 관리하는 클래스(주인클래스)
//    2. one 쪽 클래스 @OneToMany 추가 ,컬럼이 생성안됨 , FK없음, 클래스에만 속성이 있음
//    사원이 많으니까 배열로 만들어야함
//     @OneToMany ( mappedBy =  "사원클래스의 @ManyToOne 달린 속성" )
//    @OneToMany( mappedBy = "department")
//    @JsonManagedReference
//    private List<Employee> employees = new ArrayList<>(); // 사원이 Many 이기때문에 배열로 만듬

    @OneToMany( mappedBy = "department")
//    잭슨( 객체를 json 으로 자동변환시켜주는 기능) 에러를 발생시킴
    @JsonManagedReference
    private Set<Employee> employee = new HashSet<>(); // set 을 사용하면 중복제거
}