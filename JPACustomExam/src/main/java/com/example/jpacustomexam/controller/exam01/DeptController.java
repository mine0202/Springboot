package com.example.jpacustomexam.controller.exam01;

import com.example.jpacustomexam.model.Dept;
import com.example.jpacustomexam.service.exam01.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.jpaexam.controller.exam07
 * fileName : Dept07Controller
 * author : ds
 * date : 2022-10-21
 * description : 부서 컨트롤러 쿼리 메소드
 * 요약 :
 *     쿼리 메소드 : 자동으로 사용자 정의 sql문을 생성해주는 함수
 *          목적 : 기본 함수보다 다양한 sql문을 작성하기 위해 사용
 *         사용법 : 함수이름으로 sql 문장을 작성함 ( Repository 안에 함수명만 작성 )
 *         ex) JPA 클래스 == 대상 테이블
 *             find == select
 *             all  == *
 *             by   == from
 *             속성  == where 컬럼
 *          orderBy == order by
 *          속성 desc == 컬럼 desc
 *
 *          1) 조인 ( @ManyToOne, @OneToMany, @OneToOne -> JPA PK / FK
 *          2) 동적쿼리 : Dynamic 쿼리 -> Querydsl 라이브러리 설치 (오픈소스)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
// @RestController : return 값이 json 데이터 형태로 출력됨
@Slf4j
@RestController
@RequestMapping("/exam01")
public class DeptController {

    //    스프링부트 : DI(의존성 주입) ( @Autowired )
    @Autowired
    DeptService deptService; // @Autowired : 스프링부트가 가동될때 생성된 객체를 하나 받아오기

    //  *1) 클라이언트 : (form태그) Get 방식(url) -> 2) 서버 : @GetMapping("url") -> 3) DB: select 요청,
    @GetMapping("/dept")
    public ResponseEntity<Object> getDeptAll() {

        try {
            List<Dept> list = deptService.findAll();

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  *1) 클라이언트 : (form태그) Get 방식(url) -> 2) 서버 : @GetMapping("url") -> 3) DB: select 요청,
    //    ID 로 조회(select)하는 함수
    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> getDeptId(@PathVariable int dno) {

        try {
            Optional<Dept> optionalDept = deptService.findById(dno); // select 문

            if (optionalDept.isPresent() == true) {
//                데이터 + 성공 메세지 전송
//                옵셔널객체.get() : 안에 있는 객체 꺼내기 함수
                return new ResponseEntity<>(optionalDept.get(), HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    부서 정보 추가하는 함수(insert)
//    @RequestBody : 클라이언트에서 json 객체를 전송하면 스프링부트 함수에서 매개변수로 전달받음
    @PostMapping("/dept")
    public ResponseEntity<Object> createDept(@RequestBody Dept dept) {

        try {
            Dept dept2 = deptService.save(dept); // insert 문 호출

            return new ResponseEntity<>(dept2, HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    수정 실행 함수
//  *1) 클라이언트 : (form태그) Put 방식(url) -> 2) 서버 : @PutMapping("url") -> 3) DB: update 요청,
    @PutMapping("/dept/edit/{dno}")
    public ResponseEntity<Object> updateDept(@PathVariable int dno, @RequestBody Dept dept) {

        try {
//            매개변수 dept 안에 부서번호(기본키)값이 있음 -> update 문 실행
            Dept dept2 = deptService.save(dept); // update 문 호출

            return new ResponseEntity<>(dept2, HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    컨트롤러 삭제 함수
//  *1) 클라이언트 : (form태그) Delete 방식(url) -> 2) 서버 : @DeleteMapping("url") -> 3) DB: delete 요청,
    @DeleteMapping("/dept/delete/{dno}")
    public ResponseEntity<Object> deleteDept(@PathVariable int dno) {
        try {
//            id에 해당하는 부서정보를 삭제하는 함수 호출
            boolean bSuccess = deptService.removeById(dno); // delete 문 호출

            if (bSuccess == true) {
//                성공 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
//                실패했을 경우 : id값이 존재하지 않을때
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    1) 쿼리 메소드 연습 예제 ( 컨트롤러 )
    @GetMapping("/dept/desc")
    public ResponseEntity<Object> getDeptAllDesc() {

        try {
            List<Dept> list = deptService.findAllDesc(); // 전체 조회(내림차순 정렬)

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    2) 쿼리 메소드 연습 예제 ( 컨트롤러 ) : 오름차순
    @GetMapping("/dept/asc")
    public ResponseEntity<Object> getDeptAllAsc() {

        try {
            List<Dept> list = deptService.findAllAsc(); // 전체 조회(내림차순 정렬)

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dept/desc/{dname}")
    public ResponseEntity<Object> getDeptAllDnameDesc(@PathVariable String dname) {

        try {
            List<Dept> list = deptService.findAllLikeDname(dname); // 전체 조회(내림차순 정렬)

            if (list.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}










