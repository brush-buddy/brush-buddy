package com.a205.brushbuddy.board.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class ReplyListResponseDto {
    private List<replyDTO> replyList;
    private Integer pageNum;
    private Integer length;
    private Integer totalPage; // 전체 페이지

    @Getter
    @Setter
    @Builder
    public static class replyDTO {
        private Long replyId;
        private Integer userId;
        private String nickname;
        private String contents;
        private String createdAt;
        private Boolean isMine; // 나의 댓글 인가
    }
}
