package com.example.jpaexam.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

// JPA 매커니즘 : 클래스를 대상으로 테이블 자동 생성,
//              기본 제공하는 함수들은 자동으로 CRUD sql 문을 만들어줌
//              sql 문 작성에 필요한 노력을 절약할 수 있음
//              개발자는 제공하는 함수만 호출하고 sql 문은 JPA 라이브러리가 알아서 생성해줌
// JPA 에서는 model 을 엔티티( Entity ) 라고도 함
// JPA 에서는 클래스 모양대로 테이블을 만들어줌
// @Entity  : 대상 클래스를 참고하여 DB에 물리 테이블을 생성함
// @Table(name="테이블명") 자동생성시 테이블명으로 생성됨
// @SequenceGenerator(각종속성) : oracle DB 시퀀스 생성시 사용할 속성들
// @DynamicInsert : insert 시 null 인 컬럼 제외해서 sql 문 자동생성
// @DynamicUpdate : update 시 null 인 컬럼 제외해서 sql 문 자동생성
// @Id 기본키가 지정될 속성 -> DB에 기본키를 자동으로 만들어줌
// @Column(columnDefinition = "컬럼타입(개수)") : DB에 자동생성될 테이블의 컬럼정보 직접 지정
@Entity
@Table(name="TB_DEPT")
@SequenceGenerator(
        name= "SQ_DEPT_GENERATOR"    // 자바에서 사용하는 이름
        , sequenceName = "SQ_DEPT"   // 데이터베이스에서 사용하는 이름
        , initialValue = 1           // 초기값
        , allocationSize = 1         // 증가값
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Dept extends BaseTimeEntity{
//    부서번호 dno
//    @Id 기본키가 됨
//    시퀀스 사용 : ORACLE / POSTGRE 등
//    MYSQL / MARIA DB 는 increment 이용
//    제너레이터의 이름을 SQ_DEPT_GENERATOR 으로 정하여 입력
    @Id  // 테이블에 하나이상의 기본키가 필요하므로 변하지 않는 값을 기본키로 만듬
    @GeneratedValue(strategy = GenerationType.SEQUENCE   // 시퀀스사용
                    , generator = "SQ_DEPT_GENERATOR"
    )
    private Integer dno;  // 10씩 증가하게 하려함 , 오라클에서는 시퀀스를 사용
//    부서명 dname
//    컬럼 상태를 지정해줘야함
    @Column(columnDefinition = "VARCHAR2(255)")   // 해당컬럼을 이렇게 만들도록 지시
    private String dname;
//    지역 loc
    @Column(columnDefinition = "VARCHAR2(255)")
    private String loc;

}
