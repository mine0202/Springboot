package com.example.jpacustomerexam.model.exam05;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "TB_PHONE")
@SequenceGenerator(
        name= "SQ_PHONE_GENERATOR"    // 자바에서 사용하는 이름
        , sequenceName = "SQ_PHONE"   // 데이터베이스에서 사용하는 이름
        , initialValue = 1           // 초기값
        , allocationSize = 1         // 증가값
)
@Getter
@Setter
@ToString (exclude = "person")
@Builder  // 생성자의 역할
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Phone {

    @Id  // 테이블에 하나이상의 기본키가 필요하므로 변하지 않는 값을 기본키로 만듬
    @GeneratedValue(strategy = GenerationType.SEQUENCE   // 시퀀스사용
            , generator = "SQ_PHONE_GENERATOR"
    )
    private Integer pno;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String pname;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String vendor;

//    phone 클래스 현재는 1개지만 향후 여러개가 될 수 있음
//    여기에 FK (참조키 / 외래키 ) 를 생성하는 것이 좋음

    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn 상태클래스 기본키속성으로 많이 설정함
    @JoinColumn(name = "no")
    @JsonBackReference
    private Person person;


}
