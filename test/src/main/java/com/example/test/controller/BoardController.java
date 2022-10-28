package com.example.test.controller;

import com.example.test.model.Board;
import com.example.test.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<Object> getBoardAll(){
        try{
            List<Board> list = boardService.findAll();
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/boards/paging")
    public ResponseEntity<Object> getBoardAllPage(Pageable pageable) {

        try {
            Page<Board> page = boardService.getBoardAllPage(pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("dept", page.getContent());
            response.put("currentPage", page.getNumber());
            response.put("totalItems", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());

            if (page.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/boards/{bid}")
    public ResponseEntity<Object> getBoardId(@PathVariable int bid){
        try{
            Optional<Board> optionalBoard = boardService.findById(bid);
            if( optionalBoard.isPresent()){
                return new ResponseEntity<>(optionalBoard.get(),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/boards")
    public ResponseEntity<Object> createBoard(@RequestBody Board board){
        try{
            Board board1 = boardService.save(board);
            return new ResponseEntity<>(board1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/boards/{bid}")
    public ResponseEntity<Object> updateBoard(@PathVariable int bid, @RequestBody Board board){
        try{
            Board board1 = boardService.save(board);
            return new ResponseEntity<>(board1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/boards/deletion/{bid}")
    public ResponseEntity<Object> deleteBoard(@PathVariable int bid){
        try{
            boolean bSuccess = boardService.removeById(bid);
            if(bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/boards/title/{title}")
    public ResponseEntity<Object> searchBoardByTitleContent(@PathVariable String title){
        try{
            List<Board> list = boardService.searchBoardByTitleContent(title);
            if( list.isEmpty()== false){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
