package com.board.dto;

import lombok.Data;

@Data
public class BoardSearchDTO {

    private String title;   // 게시판 제목 검색 조건

    private String username;    // 게시판 작성자 검색 조건
}
