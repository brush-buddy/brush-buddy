package com.a205.brushbuddy.draft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.a205.brushbuddy.draft.domain.Purchase;
import com.a205.brushbuddy.draft.domain.PurchaseId;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseId> {

	@Transactional
	@Modifying
	@Query(value = "insert into purchase (user_id, draft_id, purchase_price) values (:userId, :draftId, :purchasePrice)", nativeQuery = true)
	void insertPurchase(@Param("userId") int userId, @Param("draftId") long draftId, @Param("purchasePrice") int purchasePrice);

	Optional<Purchase> findAllByPurchaseId_Draft_DraftIdAndPurchaseId_User_UserId(long draftId, int userId);
}
