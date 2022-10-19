package com.example.modelexam.controller.quiz;

import com.example.modelexam.model.Board;
import com.example.modelexam.service.quiz.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/quiz")
public class BoardController {

//    종합 연습
//    BoardService 클래스를 만들고 CRUD  에 해당하는 서비스 함수를 정의
//    BoardController 클래스를 만들고 CRUD  에 해당하는 컨트롤러 함수를 정의
//    단, @RestController 를 사용하고 , 예외처리와 ResponseEntity 를 사용
//    테스트는 Rest Client 를 이용

    @Autowired
    BoardService boardService;

//    조회 함수
    @GetMapping("/board")
    public ResponseEntity<Object> getBoardAll(){
        try{
            List<Board> list = boardService.findAll();
            if(list.isEmpty()== false){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    id로 조회함수
    @GetMapping("/board/{id}")
    public ResponseEntity<Object> getBoardId(@PathVariable int id){
        try{
            Optional<Board> optionalBoard = boardService.findById(id);
            if( optionalBoard.isPresent()){
                return new ResponseEntity<>(optionalBoard.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    insert 함수
    @PostMapping("/board")
    public ResponseEntity<Object> createBoard(@RequestBody Board board){
        try{
            List<Board> list = boardService.save(board);
            return new ResponseEntity<>(list,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    update 함수
    @PutMapping("/board/edit/{id}")
    public ResponseEntity<Object> updateBoard(@PathVariable int id , @RequestBody Board board){
        try{
            List<Board> list = boardService.save(board);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    삭제 함수
    @DeleteMapping("/board/delete/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable int id){
        try{
            boolean bSuccess = boardService.removeById(id);
            if(bSuccess){
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
