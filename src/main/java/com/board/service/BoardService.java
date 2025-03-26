package com.board.service;

import com.board.dto.BoardDTO;
import com.board.dto.BoardSearchDTO;
import com.board.entity.Board;
import com.board.repository.BoardRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Service
@Slf4j
@AllArgsConstructor
public class BoardService {
    private final BoardRepository repo;

    public Page<BoardDTO> getList(BoardSearchDTO dto, Pageable pageable) throws Exception {

//        Page<Board> list = repo.findAllWithSearch(dto, pageable);
        Page<Board> list = repo.findAllWithSearch(dto.getTitle(), dto.getUsername(), pageable);
        ArrayList<BoardDTO> newList = new ArrayList<>();

        for (Board entity : list.getContent()) {

            BoardDTO exEntity = BoardDTO.toDto(entity);
            newList.add(exEntity);

        }

        return new PageImpl<>(newList, list.getPageable(), list.getTotalElements());

    }

    public BoardDTO get(int id) throws Exception {

        // id로 상세 데이터 조회
        Board entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("데이터를 찾을 수 없습니다."));

        // 이전글과 다음글 ID 조회
        Integer previousId = repo.findPreviousId(id);
        Integer nextId = repo.findNextId(id);

        BoardDTO boardDTO = BoardDTO.toDto(entity);
        boardDTO.setPreviousId(previousId);
        boardDTO.setNextId(nextId);

        return boardDTO;

    }

    @Transactional
    public void add(BoardDTO dto) throws Exception {

        Board entity = dto.toEntity();

        // 로그인 기능 구현 전 임의의 username 할당
        entity.setUsername("nas");

        repo.saveCustom(entity.getTitle(), entity.getContent(), entity.getUsername());

    }

    @Transactional
    public void update(int id, BoardDTO dto) throws Exception {

        Board existingBoard = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("데이터를 찾을 수 없습니다."));

        Board entity = dto.toEntity();

        repo.updateCustom(id, entity.getTitle(), entity.getContent());

    }

    @Transactional
    public void remove(int id) throws Exception {

        Board existingBoard = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("데이터를 찾을 수 없습니다."));

        repo.deleteById(id);

    }

}
