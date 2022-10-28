package com.example.test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="TB_BOARD")
@SequenceGenerator(
        name= "SQ_BOARD_GENERATOR"
        , sequenceName = "SQ_BOARD"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_BOARD_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER", name="BID")
    private Integer bid;

    @Column(columnDefinition = "VARCHAR2(255)", name="BOARD_TITLE")
    private String boardTitle;

    @Column(columnDefinition = "VARCHAR2(255)", name="BOARD_CONTENT")
    private String boardContent;

    @Column(columnDefinition = "VARCHAR2(255)", name="BOARD_WRITER")
    private String boardWriter;

    @Column(columnDefinition = "NUMBER", name="VIEW_CNT")
    private Integer viewCnt;
}
