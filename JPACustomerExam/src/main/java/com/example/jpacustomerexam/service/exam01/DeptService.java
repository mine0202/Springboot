package com.example.jpacustomerexam.service.exam01;

import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 부서업무 서비스 클래스
@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository;  // JPA CRUD 함수가있음

//    전체 조회 함수
    public List<Dept> findAll(){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Dept> list = deptRepository.findAll();
        return list;
    }

//    id로 조회
    public Optional<Dept> findById(int dno){
        Optional<Dept> optionalDept = deptRepository.findById(dno); // select 문 자동 생성 DB 로 전송
        return optionalDept;
    }

//    insert , update 함수 겸용
    public Dept save(Dept dept){
// save 함수특징: 기본키 값이 dept에 없으면 insert , 기본키값이 있으면 update 로 작동하게 JPA 가 만들어줌
        Dept dept1 = deptRepository.save(dept);
        return dept1;
    }

//    delete 함수 ,  dno( 부서번호) 로 삭제
    public boolean removeById(int dno){

//       dno 에 해당하는 값이 있는지 select 해서 있으면 delete 없으면 skip
        if(deptRepository.existsById(dno)){
            deptRepository.deleteById(dno);
            return true;
        }
        else {
            return false;
        }
    }

//    쿼리 메소드 연습 예제

    public List<Dept> findAllDesc(){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Dept> list = deptRepository.findAllByOrderByDnoDesc(); // 전체조회 dno 내림차순
        return list;
    }

//    sql : select * from tb_dept order by dno asc;
//    sql 문을 오히려 더 잘 알아야함, 콘솔보고 잘 분석해서 잘 날아가는지 알아야함
    public List<Dept> findAllAsc(){
        List<Dept> list = deptRepository.findALlByOrderByDnoAsc();  // 전체조회 dno 오름차순
        return list;
    }


    public List<Dept> findAllDnameAsc(){
        List<Dept> list = deptRepository.findAllByOrderByDnameAsc(); // 전체조회 dname 오름차순
        return list;
    }

    public List<Dept> findAllDnameDesc(String dname){
//         JPA 가 findAll 등을 자동으로 만들어줌
        List<Dept> list = deptRepository.findAllByDnameContainingOrderByDnameDesc(dname); // 전체조회 dname 내림차순
        return list;
    }
}
