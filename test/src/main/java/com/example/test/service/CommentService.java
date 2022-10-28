package com.example.test.service;

import com.example.test.model.Comment;
import com.example.test.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findAll(){
        List<Comment> list = commentRepository.findAll();
        return list;
    }

    public Optional<Comment> findById(int cid){
        Optional<Comment> optionalComment = commentRepository.findById(cid);
        return optionalComment;
    }

    public Comment save(Comment comment){
        Comment comment1 = commentRepository.save(comment);
        return comment1;
    }

    public boolean removeById(int cid){

        if(commentRepository.existsById(cid)){
            commentRepository.deleteById(cid);
            return true;
        }
        else {
            return false;
        }
    }

}
