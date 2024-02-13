package com.a205.brushbuddy.draft.repository.DraftCategory;

import java.util.List;
import java.util.Optional;

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

	@Transactional
	@Modifying
	@Query(value = "delete from draft_category where draft_id = :draftId", nativeQuery = true)
	void deleteDraftCategory(@Param("draftId") Long draftId);

	@Query(value = "select draft_id from draft_category where category_id = :categoryId", nativeQuery = true)
	List<Long> findDraftIdByCategoryId(@Param("categoryId") Long categoryId);

	@Query(value = "select category_id from draft_category where draft_id = :draftId", nativeQuery = true)
	List<Long> findCategoryIdByDraftId(@Param("draftId") Long draftId);
}
