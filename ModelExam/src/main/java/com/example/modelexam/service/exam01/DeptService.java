package com.example.modelexam.service.exam01;



import com.example.modelexam.dao.DeptDao;
import com.example.modelexam.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// service 에 해당하는 파일은 접미어로 Service 를 붙임
// 부서 업무 로직을 작성할 함수가 있는 클래스

//Service : 업무로직( Business logic) 실행할 클래스
//          주로 DAO ( DB 접속 함수들 모임 클래스(CRUD) ) 객체를 이용해서 업무로직을 코딩함
// DAO(Repository) : DB에 직접 접근해서 정보를 추가/검색/수정/삭제(CRUD)하는 함수들이 있는 클래스
// Spring 의 동작 원리
// 객체지향 코딩에서 new 생성자()를 만들었으나 스프링에서는 자동으로 만들어줌
//     -> 스프링부트 컨테이너에 객체들(서버가 가동될때 미리 객체를 생성해서 등록해둠)
//          마크가 붙은 객체만 생성해줌(@Service, @Component, @Repository 등)
//          @Service(업무로직용) , @Repository(데이터베이스 저장용) => 통칭해서 @Component 라고함, 용도에 따른 분류
//     -> 개발자는 객체를 요청만 하면됨( @Autowired 를 이용)

// @Service : 스프링 컨테이너 박스에 대상 객체를 생성함( 스프링 서버가 가동될때 )
//       참고) 만들어진 객체들은 전부 싱글톤( 공유객체 )으로 만듬 -> 메모리절략/성능향상 을 위해서
@Service
public class DeptService {

//    DAO 객체가 필요함 : DB에 접근하기 위해서
    @Autowired
DeptDao deptDao;  // 스프링부트 컨테이너에 생성된 deptDao 이름(객체자료형)의 객체를 하나 받아옴

//    모든 데이터를 조회하는 함수
    public List<Dept> findAll() {
        List<Dept> list = deptDao.selectAll(); // db 접근 모든 데이터 가져오기 함수 실행

//        다른 업무로직 들어갈 수 있으나 지금은 생략
        return list;
    }
}
