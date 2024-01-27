package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.dto.*;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<Board> getBoardList(Map<String, String> param); // 게시판 리스트 조회 및 검색 조회
    boolean writeBoard(BoardWriteRequestDto requestDto); // 게시판 작성 메소드

    BoardDetailResponseDto getDetail(Long boardId); // 게시판 상세 조회 메소드

    boolean modifyBoard(BoardModifyRequestDto requestDto); // 게시판 수정

    boolean deleteBoard(String userId, Long boardId); // 게시판 삭제

    List<CommentListResponseDto> getComments(Long BoardId, CommentWriteRequestDto requestDto); // 댓글 목록 조회

    boolean writeComment(CommentWriteRequestDto requestDto); // 댓글 작성

    boolean deleteComment(Long boardId, Long commentId); // 댓글 삭제

    boolean addHeart(String userId, Long commentId);

}
