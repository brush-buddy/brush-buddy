package com.a205.brushbuddy.draft.repository.DraftCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.a205.brushbuddy.draft.domain.DraftCategory;

@Repository
public interface DraftCategoryRepository extends DraftCategoryCustom, JpaRepository<DraftCategory, Long> {
	@Transactional
	@Modifying
	@Query(value = "insert into draft_category (draft_id, category_id) values (:draftId, :categoryId)", nativeQuery = true)
	void insertDraftCategory(@Param("draftId") Long draftId, @Param("categoryId") Long categoryId);
}
