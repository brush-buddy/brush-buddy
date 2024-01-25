package com.a205.brushbuddy.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.a205.brushbuddy.board.dto.response.BoardListResponseDto;
import com.a205.brushbuddy.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;


    public Page<BoardListResponseDto> getDraftList(Long draftId, Pageable pageable) {
        try {
            return boardRepository.findByDraft_DraftId(draftId,pageable).map(p->BoardListResponseDto.builder()
                    .boardId(p.getBoardId())
                    .boardTitle(p.getBoardTitle())
                    .boardThumbnail(p.getBoardThumbnail())
                    .boardLikeNumber(p.getBoardLikeNumber())
                    .boardWatch(p.getBoardWatch())
                    .boardTimestamp(p.getBoardTimestamp())
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
