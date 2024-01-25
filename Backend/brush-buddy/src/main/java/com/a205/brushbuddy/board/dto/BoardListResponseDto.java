package com.a205.brushbuddy.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class BoardListResponseDto {
    private List<BoardDTO> boards;
    private Integer pageNum;
    private Integer length;


    @Getter
    @Setter
    public static class BoardDTO {
        private Long boardId;
        private String boardTitle;
        private String thumbnail;
        private Long likeNumber;
        private Long views;
        private String createdAt;

    }
}
