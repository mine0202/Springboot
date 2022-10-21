package com.example.jpacustomerexam.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table( name ="TB_Faq")
@SequenceGenerator(
        name="SQ_FAQ_GENERATOR"
        , sequenceName = "SQ_FAQ"
        , initialValue = 5
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Faq extends BaseTimeEntity{
//    종합 예제
//    Faq 게시판을 만드려고 합니다.
//     모델을 아래와 같이 정의하고, 서비스 / 컨트롤러 클래스를 정의하세요 ( CRUD 함수 모두 포함)
//    단, no 는 시퀀스로 생성되고 1부터 증가, 초기값 1, 생성일자, 수정일자 자동만영
//    RestController를 이용
//    속성
//          no ( int ) : Faq 번호
//          title ( String ) : 제목
//          content ( String ) : 내용

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    , generator = "SQ_FAQ_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER")
    private Integer no;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String title;

    @Column(columnDefinition = "VARCHAR2(255)")
    private String content;
}
