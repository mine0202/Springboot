package com.example.modelexam.service.exam10;

import com.example.modelexam.dao.DeptDao;
import com.example.modelexam.model.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class Dept10Service {

    @Autowired
DeptDao deptDao;

    public List<Dept> findAll() {
        List<Dept> list = deptDao.selectAll();
        return list;
    }

    public Optional<Dept> findById(long dno){
        Dept dept = deptDao.selectById(dno);

//  null 체크 객체 사용  .ofNullable : null 이 들어올 수도 있을때 사용
        Optional<Dept> optionalDept = Optional.ofNullable(dept);
        return optionalDept;
    }

    public List<Dept> save(Dept dept){
        List<Dept> list = null;
//      .getDno() 있으면 insert 문 실행
//        없으면 update 문 실행
        if (dept.getDno()== null) {
            int count = deptDao.selectAll().size(); // list 배열의 전체 크기(건수)
            int newDno = ( count+1)*10 ; // 10씩 증가하게 만듬 , 새로운 부서번호
            dept.setDno(newDno); // 부서번호 자동 저장

//           자바 날짜 객체들 : date , Calendar 는 예전, LocalDateTime 최신에 사용
//            날짜포멧정하기 :  yyyy-MM-dd HH:mm:ss
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            현재시간구하기 LocalDateTime.now().toString()
            dept.setInsertTime(LocalDateTime.now().format(dtf)); // 현재시간 포멧 dtf 로 시간 넣기
            list = deptDao.insert(dept); // 새로운 부서정보 저장
        }
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            dept.setUpdateTime(LocalDateTime.now().format(dtf));
            log.debug(dept.toString());
            list = deptDao.update(dept); // 새로운 부서정보 저장
        }

        return list;
    }
//    dno 로 부터 부서정보 삭제 함수
    public boolean removeById(int dno){
        int iCount = deptDao.deleteById(dno); // 삭제함수 호출, 리턴값 삭제건수
        return (iCount>0)? true : false; // 삭제를 한번이상하면 true 아니면 false
    }

}
