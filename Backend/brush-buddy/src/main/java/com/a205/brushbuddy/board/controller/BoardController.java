package com.a205.brushbuddy.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.a205.brushbuddy.board.dto.response.BoardListResponseDto;
import com.a205.brushbuddy.board.service.BoardService;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/draftList")
	public ResponseEntity<Page<BoardListResponseDto>> getBoardsByDraftId(
		@RequestParam(required = false) Long draftId,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int page
	) {
		Page<BoardListResponseDto> boardList = boardService.getDraftList(draftId, PageRequest.of(page, size, Sort.by("boardId").descending()));
		return ResponseEntity.ok(boardList);
	}


}
