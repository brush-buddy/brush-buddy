package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Reply;
import com.a205.brushbuddy.board.dto.BoardDetailResponseDto;
import com.a205.brushbuddy.board.dto.BoardModifyRequestDto;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.dto.ReplyWriteRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<Board> getBoardList(String search, Pageable pageable) ; // 게시판 리스트 조회 및 검색 조회
    boolean writeBoard(Integer userId, BoardWriteRequestDto requestDto) ; // 게시판 작성 메소드

    BoardDetailResponseDto getDetail(Integer userId, Long boardId) ; // 게시판 상세 조회 메소드

    boolean modifyBoard(Long boardId, Integer userId, BoardModifyRequestDto requestDto) ; // 게시판 수정

    boolean deleteBoard(Integer userId, Long boardId); // 게시판 삭제

    Page<Reply> getReplies(Long BoardId, Pageable pageable) ; // 댓글 목록 조회

    boolean writeReply(Integer userId, Long boardId, ReplyWriteRequestDto requestDto); // 댓글 작성

    boolean deleteReply(Integer userId, Long replyId) ; // 댓글 삭제

    boolean addHeart(Integer userId, Long boardId);

    boolean deleteHeart(Integer userId, Long boardId);
}
