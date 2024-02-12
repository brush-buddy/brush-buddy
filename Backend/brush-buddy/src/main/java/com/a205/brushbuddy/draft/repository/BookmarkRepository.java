package com.a205.brushbuddy.draft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.a205.brushbuddy.draft.domain.Bookmark;
import com.a205.brushbuddy.draft.domain.BookmarkId;

import jakarta.persistence.ManyToOne;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {

	@Transactional
	@Modifying
	@Query(value = "insert into bookmark (user_id, draft_id) values (:userId, :draftId)", nativeQuery = true)
	void insertBookmark(@Param("userId") int userId, @Param("draftId")Long draftId);

	@Transactional
	@Modifying
	@Query(value = "delete from bookmark where user_id = :userId and draft_id = :draftId", nativeQuery = true)
	void deleteBookmark(@Param("userId")int userId, @Param("draftId")Long draftId);


	Optional<Bookmark> findByBookmarkId_User_UserId_AndBookmarkId_Draft_DraftId(Integer userId, Long draftId);


}
