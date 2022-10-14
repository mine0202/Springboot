package com.example.controllerexam.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    String dName;
    String loc;
    Integer dno;
    Integer num;

    public Dept(String dName, String loc, Integer dno) {
        this.dName = dName;
        this.loc = loc;
        this.dno = dno;
    }
}
