package com.example.modelexam.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName : com.example.modelexam.model
 * fileName : Common
 * author : kangtaegyung
 * date : 2022/10/16
 * description : 공통 속성 클래스
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
@AllArgsConstructor
@NoArgsConstructor
public class Common {
    protected String insertTime;
    protected String updateTime;
}
