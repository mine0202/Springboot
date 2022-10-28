package com.example.test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="TB_COMMENT")
@SequenceGenerator(
        name= "SQ_COMMENT_GENERATOR"
        , sequenceName = "SQ_COMMENT"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_COMMENT_GENERATOR"
    )
    @Column(columnDefinition = "NUMBER",name="CID")
    private Integer cid;

    @Column(columnDefinition = "VARCHAR2(255)",name="COMMENT_CONTENT")
    private String commentContent;

    @Column(columnDefinition = "VARCHAR2(255)",name="COMMENT_WRITER")
    private String commentWriter;

    @Column(columnDefinition = "NUMBER",name="BID")
    private Integer bid;
}
