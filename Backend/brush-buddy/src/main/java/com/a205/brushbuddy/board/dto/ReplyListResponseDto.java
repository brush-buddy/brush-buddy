package com.a205.brushbuddy.board.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class ReplyListResponseDto {
    private Integer resultCode;
    private List<CommentDTO> commentList;
    private Integer pageNum;
    private Integer length;


    @Getter
    @Setter
    public static class CommentDTO {
        private Integer userId;
        private String nickname;
        private String contents;
        private String createdAt;

    }
}
