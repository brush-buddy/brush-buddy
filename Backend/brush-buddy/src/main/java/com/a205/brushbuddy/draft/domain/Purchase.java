package com.a205.brushbuddy.draft.domain;

import java.sql.Timestamp;

import com.a205.brushbuddy.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "purchase")
public class Purchase {

	@EmbeddedId
	private PurchaseId purchaseId;

	@Column(name = "purchase_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp purchaseTimestamp;

	@Column(name = "purchase_price", nullable = false)
	private int purchasePrice;


}
