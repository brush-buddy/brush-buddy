package com.a205.brushbuddy.draft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.a205.brushbuddy.draft.domain.Category;
import com.a205.brushbuddy.draft.repository.DraftCategory.DraftCategoryCustom;

public interface CategoryRepository extends JpaRepository<Category, Long>, DraftCategoryCustom {
	List<Category> findByCategoryContentIn(List<String> categoryContent);
	Category findByCategoryContent(String categoryContent);

	List<Category> findCategoryContentByCategoryIdIn(List<Long> categoryId);

}
