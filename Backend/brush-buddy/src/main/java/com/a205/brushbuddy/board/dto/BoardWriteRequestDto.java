package com.a205.brushbuddy.board.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoardWriteRequestDto {
    private Integer boardId;
    private String title;
    private String contents;
    private List<PhotoDTO> photo;
    private Integer draftId;

    // 생성자, getter 및 setter 메서드는 필요에 따라 추가할 수 있습니다.

    public static class PhotoDTO {
        private Integer order;
        private String img;  // 이미지를 Base64로 받는 경우, String으로 처리할 수 있습니다.

        // 생성자, getter 및 setter 메서드는 필요에 따라 추가할 수 있습니다.
    }
}
