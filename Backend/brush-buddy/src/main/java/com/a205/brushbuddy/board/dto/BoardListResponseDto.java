package com.a205.brushbuddy.board.dto;

import java.util.List;

public class BoardListResponseDto {
    private List<BoardDTO> boards;
    private Integer pageNum;
    private Integer length;

    // 생성자, getter 및 setter 메서드는 필요에 따라 추가할 수 있습니다.

    public static class BoardDTO {
        private Long boardId;
        private String boardTitle;
        private String thumbnail;
        private Long likeNumber;
        private Long views;
        private String createdAt;

        // 생성자, getter 및 setter 메서드는 필요에 따라 추가할 수 있습니다.
    }
}
