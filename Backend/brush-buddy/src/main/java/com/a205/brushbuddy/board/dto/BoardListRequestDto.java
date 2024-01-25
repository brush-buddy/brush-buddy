package com.a205.brushbuddy.board.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardListRequestDto {
    private String search;
    private String order;
    private Integer listNum;
    private Integer pageNum;
}
