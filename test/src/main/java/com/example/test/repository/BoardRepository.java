package com.example.test.repository;


import com.example.test.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query(value = "select b.* from tb_board b where b.BOARD_TITLE like %:title% ",nativeQuery = true)
    List<Board> searchBoardByTitleContent(@Param("title")String title);

    @Query(value = "select b.* from tb_board b order by insert_time desc ",nativeQuery = true)
    List<Board> searchAllOrderByInsertTime();

}
