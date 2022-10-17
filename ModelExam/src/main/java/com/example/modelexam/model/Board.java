package com.example.modelexam.model;

import lombok.*;

/**
 * packageName : com.example.modelexam.model
 * fileName : Board
 * author : kangtaegyung
 * date : 2022/10/16
 * description : 게시판 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/16         kangtaegyung          최초 생성
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board extends Common {
    private Integer id;
    private String title;
    private String content;

    @Builder
    public Board(String insertTime, String updateTime, Integer id, String title, String content) {
        super(insertTime, updateTime);
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
