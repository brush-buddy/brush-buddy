package com.a205.brushbuddy.mypage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MypageGeneratedDraftListRequestDto {
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
