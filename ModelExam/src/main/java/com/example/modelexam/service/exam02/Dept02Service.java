package com.example.modelexam.service.exam02;



import com.example.modelexam.dao.DeptDao;
import com.example.modelexam.model.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// save()함수 추가
@Slf4j
@Service
public class Dept02Service {

//    DAO 객체가 필요함 : DB에 접근하기 위해서
    @Autowired
DeptDao deptDao;  // 스프링부트 컨테이너에 생성된 deptDao 이름(객체자료형)의 객체를 하나 받아옴

//    모든 데이터를 조회하는 함수
    public List<Dept> findAll() {
        List<Dept> list = deptDao.selectAll(); // db 접근 모든 데이터 가져오기 함수 실행

//        다른 업무로직 들어갈 수 있으나 지금은 생략
        return list;
    }

//   부서 id 로 조회하는 함수
    public Dept findById(long dno){  //한건만 나오므로 List 를 사용하지않고 Dept 를 사용
        Dept dept = deptDao.selectById(dno);
        log.debug(dept.toString());
        return dept;
    }

}
