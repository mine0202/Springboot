package com.example.jpacustomexam.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * packageName : com.example.jpaexam.model
 * fileName : Faq
 * author : ds
 * date : 2022-10-21
 * description : Faq 엔티티(==모델) 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
@Entity
@Table(name = "TB_FAQ")
@SequenceGenerator(name = "SQ_FAQ_GENERATOR"
                    ,sequenceName = "SQ_FAQ" // DB 생성되는 시퀀스 이름
                    ,initialValue = 1       // 초기값
                    ,allocationSize = 1     // 1씩 증가
)
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Faq extends BaseTimeEntity {
//    1) 종합 예제 :
//    Faq 게시판을 만들려고 합니다.
//    모델을 아래와 같이 정의하고 , 서비스/ 컨트롤러 클래스를 정의하세요 (CRUD 함수 모두 포함)
//    단 no 는 시퀀스로 생성되고 초기값 1, 1부터 증가, 생성일자/수정일자 자동 반영
//      RestControlller 를 이용하세요
//    속성
//        no ( int )  : faq 번호
//        title ( String ) : faq 제목
//        content ( String ) : faq 내용
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE // 시퀀스 사용(오라클)
                ,generator = "SQ_FAQ_GENERATOR" // 제너레이터 이름
    )
    @Column(columnDefinition = "NUMBER")
    private Integer no;
    @Column(columnDefinition = "VARCHAR2(255)")
    private String title;
    @Column(columnDefinition = "VARCHAR2(255)")
    private String content;
}










