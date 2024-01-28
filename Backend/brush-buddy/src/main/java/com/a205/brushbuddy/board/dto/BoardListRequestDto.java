package com.a205.brushbuddy.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
@Getter
@Builder
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
