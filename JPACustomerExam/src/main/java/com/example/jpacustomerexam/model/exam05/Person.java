package com.example.jpacustomerexam.model.exam05;

// @OneToOne 관계설정을 위한 클래스
// 한사람당 핸드폰 1개로 가정


import com.example.jpacustomerexam.model.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "TB_PERSON")
@SequenceGenerator(
        name= "SQ_PERSON_GENERATOR"    // 자바에서 사용하는 이름
        , sequenceName = "SQ_PERSON"   // 데이터베이스에서 사용하는 이름
        , initialValue = 1           // 초기값
        , allocationSize = 1         // 증가값
)
@Getter
@Setter
@ToString(exclude = "phone")
@Builder  // 생성자의 역할
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Person extends BaseTimeEntity {

    @Id  // 테이블에 하나이상의 기본키가 필요하므로 변하지 않는 값을 기본키로 만듬
    @GeneratedValue(strategy = GenerationType.SEQUENCE   // 시퀀스사용
            , generator = "SQ_PERSON_GENERATOR"
    )
    private Integer no;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String name;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String job;

//    @OneToOne 양방향으로 설정함
    @OneToOne(mappedBy = "person")
    @JsonManagedReference
    private Phone phone;
}
