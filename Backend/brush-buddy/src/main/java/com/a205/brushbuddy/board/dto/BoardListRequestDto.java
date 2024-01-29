package com.a205.brushbuddy.board.dto;

import lombok.*;
import org.springframework.data.domain.Sort;

@Setter
@Getter

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListRequestDto {

    private String search;

    @Builder.Default
    private String order = "boardId";

    @Builder.Default
    private Sort.Direction direction = Sort.Direction.DESC;

    @Builder.Default
    private Integer listNum = 20;

    @Builder.Default
    private Integer pageNum = 1;
}
