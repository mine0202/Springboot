package com.example.modelexam.service.quiz;

import com.example.modelexam.dao.BoardDao;
import com.example.modelexam.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardService {

//    객체 생성된것 받아오기
    @Autowired
    BoardDao boardDao;

//    모든 게시판 검색 함수
    public List<Board> findAll(){
        List<Board> list = boardDao.selectAll();
        return list;
    }

//    id 로 검색하는 함수 , null 확인을 위해 Optional 을 사용
    public Optional<Board> findById(int id){
        Board board = boardDao.selectById(id);  // 1건이므로 list에 넣지 않아도 됨
//        Optional 안에 board 가 들어가 있음, null 에러 방지하기 우해 이렇게 사용함
        Optional<Board> optionalBoard = Optional.ofNullable(board);
        return optionalBoard;
    }

//    insert , update 함수
    public List<Board> save(Board board){
        List<Board> list = null;
        if( board.getId()== null){
//            새 id 부여
            int count = boardDao.selectAll().size();
            int newId = count +1;
            board.setId(newId);

//            입력한 날짜 저장
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            board.setInsertTime(LocalDateTime.now().format(dtf));
            list=boardDao.insert(board);
        }
        else {
//            id 가 있으면 업데이트
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            board.setUpdateTime(LocalDateTime.now().format(dtf));
            list=boardDao.update(board);
        }
        return list;
    }

//    삭제 함수
    public boolean removeById(int id){
        int aCount = boardDao.deleteById(id);
        return ( aCount>0)? true:false;
    }

}
