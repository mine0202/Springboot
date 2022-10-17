package com.example.modelexam.testdata;

import com.example.modelexam.model.Dept;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.example.modelexam.testdata
 * fileName : SampleData
 * author : kangtaegyung
 * date : 2022/10/12
 * description : 샘플 부서 데이터 클래스(DB 테이블 대체)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/12         kangtaegyung          최초 생성
 */
@Component
@Getter
@Setter
public class SampleData {
    List<Dept> list = new ArrayList<>();

    public SampleData() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ldt = LocalDateTime.now().format(dtf);

        Dept dept = Dept.builder()
                .dno(10)
                .dName("Accounting")
                .loc("Pusan")
                .insertTime(ldt)
                .build();

        list.add(dept);

        dept = Dept.builder()
                .dno(20)
                .dName("Marketing")
                .loc("Seoul")
                .insertTime(ldt)
                .build();

        list.add(dept);

        dept = Dept.builder()
                .dno(30)
                .dName("Sales")
                .loc("DaeGu")
                .insertTime(ldt)
                .build();

        list.add(dept);

        dept = Dept.builder()
                .dno(40)
                .dName("Development")
                .loc("Seoul")
                .insertTime(ldt)
                .build();

        list.add(dept);
    }
}
