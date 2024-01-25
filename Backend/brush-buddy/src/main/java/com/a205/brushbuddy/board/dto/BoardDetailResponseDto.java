package com.a205.brushbuddy.board.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class BoardDetailResponseDto {
    private Integer boardId;
    private String title;
    private String contents;
    private String thumbnail;
    private List<PhotoDTO> photo;
    private Integer likeNumber;
    private Integer views;
    private String createdAt;

    // 생성자, getter 및 setter 메서드는 필요에 따라 추가할 수 있습니다


    @Getter
    @Setter
    public static class PhotoDTO {
        private Integer order;
        private String imgUrl;

        // 생성자, getter 및 setter 메서드는 필요에 따라 추가할 수 있습니다.
    }

}
