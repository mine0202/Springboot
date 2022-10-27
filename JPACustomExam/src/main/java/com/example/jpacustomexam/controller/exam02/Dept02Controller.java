package com.example.jpacustomexam.controller.exam02;

import com.example.jpacustomexam.dto.DeptGroupDto;
import com.example.jpacustomexam.model.Dept;
import com.example.jpacustomexam.service.exam01.DeptService;
import com.example.jpacustomexam.service.exam02.Dept02Service;
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
 *     사용자 정의(Custom) 함수 : sql문 직접 개발자 작성하는 것 핵심
 *     1) 쿼리 메소드 : 자동으로 사용자 정의 sql문을 생성해주는 함수
 *          목적 : 기본 함수보다 다양한 sql문을 작성하기 위해 사용
 *         사용법 : 함수이름으로 sql 문장을 작성함 ( Repository 안에 함수명만 작성 )
 *         ex) JPA 클래스 == 대상 테이블
 *             find == select
 *             all  == *
 *             by   == from
 *             속성  == where 컬럼
 *          orderBy == order by
 *          속성 desc == 컬럼 desc
 *    2) @Query(value=쿼리문)함수명() : 쿼리문에 해당되는 부분을 직접 개발자가 작성 , 함수명() 중요하지 않음
 *       쿼리문의 매개변수 전달 -> select * from tb_dept where dno = :변수명
 *                             List<Dept> selectAll(@Param(변수명) 타입 변수명)
 *          2-1) nativeQuery = true : 일반 sql문으로 작성
 *          2-2) nativeQuery = false, 생략 :  객체 쿼리
 *                     ( 테이블명 , 컬럼명 대신 클래스명, 속성(필드)명 사용 )
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
// @RestController : return 값이 json 데이터 형태로 출력됨
@Slf4j
@RestController
@RequestMapping("/exam02")
public class Dept02Controller {

    @Autowired
    Dept02Service deptService;

    //  1)
    @GetMapping("/dept/dname/{dname}")
    public ResponseEntity<Object> selectByDname(@PathVariable String dname) {

        try {
            List<Dept> list = deptService.selectByDname(dname);

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

    //  2)
    @GetMapping("/dept/dname/{dname}/loc/{loc}")
    public ResponseEntity<Object> selectByDnameAndLoc(@PathVariable String dname,
                                                    @PathVariable String loc
    ) {

        try {
            List<Dept> list = deptService.selectByDnameAndLoc(dname, loc);

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

    //  3)
    @GetMapping("/dept/groupfunc")
    public ResponseEntity<Object> selectByGroupFunc() {

        try {
            List<DeptGroupDto> list = deptService.selectByGroupFunc();

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

    //  4)
    @GetMapping("/dept/custom")
    public ResponseEntity<Object> selectByCustomDept() {

        try {
            List<DeptGroupDto> list = deptService.selectByCustomDept();

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

    //  5)
    @GetMapping("/dept/basicfunc")
    public ResponseEntity<Object> selectByBasicFunc() {

        try {
            List<DeptGroupDto> list = deptService.selectByBasicFunc();

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

    //  6)
    @GetMapping("/dept/case")
    public ResponseEntity<Object> selectByCase() {

        try {
            List<DeptGroupDto> list = deptService.selectByCase();

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










