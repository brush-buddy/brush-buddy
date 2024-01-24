package com.a205.brushbuddy.draft.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;

import lombok.NonNull;

@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {
	@NonNull Page<Draft> findAll(@NonNull Pageable pageable);

	Draft findByDraftId(Long draftId);
}
