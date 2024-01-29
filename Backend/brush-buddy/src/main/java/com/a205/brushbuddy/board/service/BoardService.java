package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Reply;
import com.a205.brushbuddy.board.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList(String search, Pageable pageable) throws Exception; // 게시판 리스트 조회 및 검색 조회
    boolean writeBoard(Integer userId, BoardWriteRequestDto requestDto) throws Exception; // 게시판 작성 메소드

    BoardDetailResponseDto getDetail(Long boardId) throws Exception; // 게시판 상세 조회 메소드

    boolean modifyBoard(Long boardId, Integer userId, BoardModifyRequestDto requestDto) throws Exception; // 게시판 수정

    boolean deleteBoard(Integer userId, Long boardId) throws Exception; // 게시판 삭제

    List<Reply> getReplies(Long BoardId, Pageable pageable) throws Exception; // 댓글 목록 조회

    boolean writeReply(Integer userId, Long boardId, ReplyWriteRequestDto requestDto) throws Exception; // 댓글 작성

    boolean deleteReply(Integer userId, Long replyId) throws Exception; // 댓글 삭제

    boolean addHeart(Integer userId, Long boardId) throws Exception;

    boolean deleteHeart(Integer userId, Long boardId) throws Exception;
}
