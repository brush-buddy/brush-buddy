package com.a205.brushbuddy.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentWriteRequestDto {
    private String contents;
}
