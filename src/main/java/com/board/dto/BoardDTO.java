package com.board.dto;

import java.util.Date;

import com.board.entity.Board;

import lombok.Data;

@Data
public class BoardDTO {

    private int id;     // 게시판 id

    private String title;   // 게시판 제목

    private String content;     // 게시판 내용

    private String username;    // 게시판 작성자명

    private Date regdate;   // 게시판 등록일

    private Date moddate;   // 게시판 수정일

    public Board toEntity() {

        Board entity = new Board();
        entity.setId(this.getId());
        entity.setTitle(this.getTitle());
        entity.setContent(this.getContent());
        entity.setUsername(this.getUsername());
        entity.setRegdate(this.getRegdate());
        entity.setModdate(this.getModdate());

        return entity;
    }

    public static BoardDTO toDto(Board entity) {

        BoardDTO dto = new BoardDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setUsername(entity.getUsername());
        dto.setRegdate(entity.getRegdate());
        dto.setModdate(entity.getModdate());

        return dto;

    }


}
