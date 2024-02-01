package com.a205.brushbuddy.board.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BoardModifyRequestDto {
    private String title;
    private String contents;
    private List<PhotoDTO> photo;
    private Long draftId;


    @Setter
    @Getter
    public static class PhotoDTO {
        private Integer order;
        private String img;  // 이미지를 Base64로 받는 경우, String으로 처리할 수 있습니다
    }

}
