package com.example.modelexam.controller.exam11;

import com.example.modelexam.model.Dept;
import com.example.modelexam.service.exam11.Dept11Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/exam11")
public class Dept11Controller {
    @Autowired
    Dept11Service deptService;

    @GetMapping("/dept")
    public ResponseEntity<Object> getDeptAll(){
        try{
            List<Dept> list = deptService.findAll();
            if( list.isEmpty()== false){
//                성공메세지 + 객체 전송
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else{
//                데이터가 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage()); //에러로그 찍기
//            내부 서버 에러 internal_sever_error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    번호로 조회하기
    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> getDeptId(@PathVariable long dno){
        try{
//            Optional 하면 null 체크를 할 수 있음
            Optional<Dept> optionalDept = deptService.findById(dno);
//            .isPresent() 현재 있으면 true
            if(optionalDept.isPresent()){
//                optionalDept.get() 옵셔널 객체안에 들어있는 객체가 나옴.
                return new ResponseEntity<>(optionalDept.get(), HttpStatus.OK);  // 200 번
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//   새로운 부서 정보 생성 insert 함수
//    @RequestBody  : 클라이언트 쪽에서 전송한 객체정보(json데이터)를 함수 매개변수로(부서객체) 전달
    @PostMapping("/dept")
    public ResponseEntity<Object> creatDept(@RequestBody Dept dept){
        try{
//            .save(객체) 객체에 키(부서번호)가 없으면 insert , 있으면 update 로직
            List<Dept> list = deptService.save(dept);
            return new ResponseEntity<>(list,HttpStatus.CREATED);  // 201 번 출력
        }catch (Exception e){
            log.info(e.getMessage()); // 에러 메세지 콘솔 출력
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    부서수정 함수
//    클라이언트 전송할 데이터 : 부서번호 ( @PathVariable ) + 수정된 객체 ( @RequestBody )
//     @PathVariable 1개의 변수 값을 받는 어노테이션
//    서비스 클래스의 .save(객체) : 매개변수의 객체안에 키값(부서번호)이 없으면 insert 호출
//                              있으면 update 호출되는 로직으로 코딩됨
    @PutMapping("/dept/edit/{dno}")
    public ResponseEntity<Object> updateDept(@PathVariable long dno, @RequestBody Dept dept){
        try{
//            키값(dno 부서번호)이 있음 업데이트 진행
            List<Dept> list = deptService.save(dept);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    부서번호에 해당하는 부서정보 삭제함수
//     @DeleteMapping == delete 문 호출 ( sql문) == Delete 문 방식 ( url method)
//    부서번호 하나만 받아서 삭제하면됨
    @DeleteMapping("/dept/delete/{dno}")
    public ResponseEntity<Object> deleteDept(@PathVariable int dno){
        try{
//            removeById 리턴 값이 정상적으로 삭제되었으면 true , 아니면 false 이므로 boolean
            boolean bSuccess = deptService.removeById(dno);
            if ( bSuccess) {
//                delete 문이 성공했을경우
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
