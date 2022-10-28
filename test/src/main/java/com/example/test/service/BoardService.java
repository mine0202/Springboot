package com.example.test.service;

import com.example.test.model.Board;
import com.example.test.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public List<Board> findAll(){
        List<Board> list = boardRepository.searchAllOrderByInsertTime();
        return list;
    }

    public Page<Board> getBoardAllPage(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);

        return page;
    }

    public Optional<Board> findById(int bid){
        Optional<Board> optionalBoard = boardRepository.findById(bid);
        return optionalBoard;
    }

    public Board save(Board board){
        Board board1 = boardRepository.save(board);
        return board1;
    }

    public boolean removeById(int bid){

        if(boardRepository.existsById(bid)){
            boardRepository.deleteById(bid);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Board> searchBoardByTitleContent(String title){
        List<Board> list = boardRepository.searchBoardByTitleContent(title);
        return list;
    }


}
