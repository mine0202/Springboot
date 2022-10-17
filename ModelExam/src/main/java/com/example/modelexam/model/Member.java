package com.example.modelexam.model;

import lombok.*;

/**
 * packageName : com.example.modelexam.model
 * fileName : Member
 * author : kangtaegyung
 * date : 2022/10/14
 * description : 회원 정보 테이블
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/14         kangtaegyung          최초 생성
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Member extends Common {
    private Integer eno;
    private String eName;
    private String job;
    private Integer manager;

    @Builder
    public Member(String insertTime, String updateTime, Integer eno, String eName, String job, Integer manager) {
        super(insertTime, updateTime);
        this.eno = eno;
        this.eName = eName;
        this.job = job;
        this.manager = manager;
    }
}
