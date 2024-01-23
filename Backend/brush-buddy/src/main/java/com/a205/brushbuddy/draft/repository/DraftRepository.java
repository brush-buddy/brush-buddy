package com.a205.brushbuddy.draft.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;

public interface DraftRepository extends JpaRepository<Draft, Long> {
	@Query("SELECT new com.a205.brushbuddy.draft.dto.response.DraftListResponseDto(d.draftId, d.draftThumbnail, d.draftTimestamp, d.draftDownload, d.draftBookmark) FROM Draft d")
	Page<DraftListResponseDto> selectAllDraft(Pageable pageable);


}
