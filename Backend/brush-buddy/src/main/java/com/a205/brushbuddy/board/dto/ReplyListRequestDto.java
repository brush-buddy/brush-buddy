package com.a205.brushbuddy.board.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyListRequestDto {
    @Builder.Default
    private Integer pageNum = 1;

    @Builder.Default
    private Integer listNum = 20;
}
