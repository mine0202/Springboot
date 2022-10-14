package com.example.controllerexam.model;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    Integer no;
    String title;
    String content;
    Integer count;

}
