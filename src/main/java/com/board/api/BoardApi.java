package com.board.api;

import jakarta.servlet.http.HttpServletRequest;

import com.board.dto.BoardDTO;
import com.board.dto.BoardSearchDTO;
import com.board.service.BoardService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
public class BoardApi {
    private final BoardService service;

    // 게시물 목록 조회
    @GetMapping("")
    public Page<BoardDTO> getList(HttpServletRequest request, BoardSearchDTO search, Pageable pageable) throws Exception {
        return service.getList(search, pageable);
    }

    // 게시물 상세 조회
    @GetMapping("/{id}")
    public BoardDTO get(HttpServletRequest request, @PathVariable int id) throws Exception {
        return service.get(id);
    }

    // 게시물 저장
    @PostMapping("")
    public void add(HttpServletRequest request, @RequestBody BoardDTO dto) throws Exception {
        service.add(dto);
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public void update(HttpServletRequest request, @PathVariable int id, @RequestBody BoardDTO dto) throws Exception {
        service.update(id, dto);
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public void remove(HttpServletRequest request, @PathVariable int id) throws Exception {
        service.remove(id);
    }

}
