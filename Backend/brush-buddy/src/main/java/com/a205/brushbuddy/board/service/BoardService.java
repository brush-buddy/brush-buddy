package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.dto.*;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<Board> getBoardList(Map<String, String> param) throws Exception; // 게시판 리스트 조회 및 검색 조회
    boolean writeBoard(BoardWriteRequestDto requestDto) throws Exception; // 게시판 작성 메소드

    BoardDetailResponseDto getDetail(Long boardId) throws Exception; // 게시판 상세 조회 메소드

    boolean modifyBoard(Long boardId, Integer userId, BoardModifyRequestDto requestDto) throws Exception; // 게시판 수정

    boolean deleteBoard(Integer userId, Long boardId) throws Exception; // 게시판 삭제

    List<CommentListResponseDto> getComments(Long BoardId, CommentWriteRequestDto requestDto) throws Exception; // 댓글 목록 조회

    boolean writeComment(CommentWriteRequestDto requestDto) throws Exception; // 댓글 작성

    boolean deleteComment(Long boardId, Long commentId) throws Exception; // 댓글 삭제

    boolean addHeart(Integer userId, Long commentId) throws Exception;

}
