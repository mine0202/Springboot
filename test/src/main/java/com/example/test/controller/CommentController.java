package com.example.test.controller;

import com.example.test.model.Comment;
import com.example.test.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/boards")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<Object> getCommentAll(){
        try{
            List<Comment> list = commentService.findAll();
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


    @GetMapping("/comments/{cid}")
    public ResponseEntity<Object> getCommentId(@PathVariable int cid){
        try{
            Optional<Comment> optionalComment = commentService.findById(cid);
            if( optionalComment.isPresent()){
                return new ResponseEntity<>(optionalComment.get(),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<Object> createComment(@RequestBody Comment comment){
        try{
            Comment comment1 = commentService.save(comment);
            return new ResponseEntity<>(comment1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/comments/{cid}")
    public ResponseEntity<Object> updateComment(@PathVariable int cid, @RequestBody Comment comment){
        try{
            Comment comment1 = commentService.save(comment);
            return new ResponseEntity<>(comment1,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/comments/deletion/{cid}")
    public ResponseEntity<Object> deleteComment(@PathVariable int cid){
        try{
            boolean bSuccess = commentService.removeById(cid);
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


}
