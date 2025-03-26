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
//    @Query(nativeQuery = true, value =
//            "select * from board as b"
//                    + " where (:#{#dto.title} = '' or b.title like concat('%', :#{#dto.title}, '%'))"
//                    + " and (:#{#dto.username} = '' or b.username like concat('%', :#{#dto.username}, '%'))"
//    )
//    Page<Board> findAllWithSearch(@Param("dto") BoardSearchDTO dto, Pageable pageable);
    @Query(nativeQuery = true, value =
            "select * from board as b"
                    + " where (:title is null or :title = '' or b.title like concat('%', :title, '%'))"
                    + " and (:username is null or :username = '' or b.username like concat('%', :username, '%'))"
                    + " order by b.regdate desc"
    )
    Page<Board> findAllWithSearch(@Param("title") String title, @Param("username") String username, Pageable pageable);

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

    // 이전 게시글 id 조회
    @Query(nativeQuery = true, value ="select id from board where id < :id order by id desc limit 1")
    Integer findPreviousId(@Param("id") int id);

    // 다음 게시글 id 조회
    @Query(nativeQuery = true, value ="select id from board where id > :id order by id asc limit 1")
    Integer findNextId(@Param("id") int id);

}
