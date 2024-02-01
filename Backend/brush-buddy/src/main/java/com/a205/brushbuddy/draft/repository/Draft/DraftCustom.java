package com.a205.brushbuddy.draft.repository.Draft;

import com.a205.brushbuddy.draft.domain.Draft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface DraftCustom {

	Page<Draft> getDraftList(Pageable pageable, String search);

	Page<Draft> getDraftList(Pageable pageable);
}
