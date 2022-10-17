package com.example.modelexam.service.exam04;



import com.example.modelexam.dao.DeptDao;
import com.example.modelexam.model.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

// findById() 함수 추가
@Slf4j
@Service
public class Dept04Service {

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

    //   부서 정보를 저장하는 save 함수
//    생성날짜 추가
//    부서번호 자동생성
    public List<Dept> save(Dept dept){
        List<Dept> list = null;

//        추가일경우 : insert 호출 ( 부서번호는 자동 생성할 예정 )
        if (dept.getDno()== null) {
//            부서번호 자동 생성
            int count = deptDao.selectAll().size(); // list 배열의 전체 크기(건수)
            int newDno = ( count+1)*10 ; // 10씩 증가하게 만듬 , 새로운 부서번호
            dept.setDno(newDno); // 부서번호 자동 저장

//            생성 잘짜 추가
//           자바 날짜 객체들 : date , Calendar 는 예전, LocalDateTime 최신에 사용
//            날짜포멧정하기 :  yyyy-MM-dd HH:mm:ss
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            현재시간구하기 LocalDateTime.now().toString()
            dept.setInsertTime(LocalDateTime.now().format(dtf)); // 현재시간 포멧 dtf 으로 시간 넣기

            list = deptDao.insert(dept); // 새로운 부서정보 저장
        }

        return list;
    }

}
