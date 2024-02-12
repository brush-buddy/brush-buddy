package com.a205.brushbuddy.mypage.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MypageMyBoardListResponseDto {
    private List<BoardDTO> boards;
    private Integer pageNum;
    private Integer length;
    private Integer totalPage; // 전체 페이지

    @Getter
    @Setter
    @Builder
    public static class BoardDTO {
        private Long boardId;
        private String boardTitle;
        private String thumbnail;
        private Integer likeNumber;
        private Integer views;
        private String createdAt;

    }
}
