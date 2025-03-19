package com.board.repository;

import com.board.dto.BoardSearchDTO;
import com.board.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 게시글 목록 조회
    @Query(nativeQuery = true, value =
            "select * from board as b"
                    + " where (:#{#dto.title} = '' or b.title like %:#{#dto.title}%)"
                    + " and (:#{#dto.username} = '' or b.username like %:#{#dto.username}%)"
    )
    Page<Board> findAllWithSearch(@Param("dto") BoardSearchDTO dto, Pageable pageable);

    // 게시글 상세 조회
//    @Query("select b from Board as b where b.id = :id")
//    Board findById(@Param("id") int id);

    // 게시글 저장
    @Modifying
    @Transactional
    @Query(value = "insert into board (title, content, username, regdate) values (:#{#title}, :#{#content}, :#{#username}, CURRENT_TIMESTAMP)", nativeQuery = true)
    void saveCustom(@Param("title") String title, @Param("content") String content, @Param("username") String username);

    // 게시글 수정
    @Modifying
    @Transactional
    @Query(value = "update board set title = :title, content = :content, moddate = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
    void updateCustom(@Param("id") int id, @Param("title") String title, @Param("content") String content);


    // 게시글 삭제
//    @Modifying
//    @Transactional
//    @Query("delete from Board as b where b.id = :id")
//    void deleteById(@Param("id") String id);

}
