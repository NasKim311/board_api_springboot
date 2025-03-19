package com.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;     // 게시판 id

    private String title;   // 게시판 제목

    private String content;     // 게시판 내용

    private String username;    // 게시판 작성자명

    private Date regdate;   // 게시판 등록일

    private Date moddate;   // 게시판 수정일
}
