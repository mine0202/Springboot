package com.example.controllerexam.model;

//회원정보 클래스
// M : Model , Service , DAO , DTO
// Model : 정보 저장/검색을 위한 클래스 , 속성/생성자/Setter/Getter 만 들어있음
// lombok 라이브러리에서 제공하는 어노테이션을 이용하여 Setter/ Getter 만듬
// @Setter : 클래스의 속성을 검토해서 Setter 함수 자동생성
// @Getter : 클래스의 속성을 검토해서 Getter 함수 자동생성
// @ToString : ToString 함수를 자동 재정의 해주는 기능 부여
//           객체를 출력하면 속성의 값이 출력됨. 일반적으로 메모리 주소값출력되었음.
// @AllArgsConstructor : 모든 속성을 매개변수로 하는 생성자 자동생성
// @NoArgsConstructor : 매개변수가 없는 생성자 자동생성

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
//    속성정의
    String id;
    String name;

}
