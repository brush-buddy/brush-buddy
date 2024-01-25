package com.a205.brushbuddy.board.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.a205.brushbuddy.board.dto.response.BoardListResponseDto;

import lombok.RequiredArgsConstructor;


public interface BoardService {


	public Page<BoardListResponseDto> getDraftList(Long draftId, Pageable pageable);
}
