package com.a205.brushbuddy.draft.repository.Draft;


import com.a205.brushbuddy.draft.domain.Draft;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {
	@NonNull Page<Draft> findAllByDraftIsDeletedIsFalseAndDraftIsPublicIsTrue(@NonNull Pageable pageable);
	Draft findByDraftId(Long draftId);
	Page<Draft> findAllByDraftIdIn(List<Long> DraftIdList, Pageable pageable);


}
