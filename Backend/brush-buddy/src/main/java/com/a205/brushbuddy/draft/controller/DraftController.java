package com.a205.brushbuddy.draft.controller;

import com.a205.brushbuddy.draft.domain.Draft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.a205.brushbuddy.draft.dto.request.DraftListRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftCreateResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftDetailResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.service.DraftService;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/draft")
public class DraftController {

	@Autowired
	private DraftService draftService;


	@GetMapping("/list")
	public ResponseEntity<Page<DraftListResponseDto>> getDrafts(
		@RequestParam(required = false) String search,
		@RequestParam(defaultValue = "3") int size,
		@RequestParam(defaultValue = "0") int page) {

		DraftListRequestDto draftListRequestDto = new DraftListRequestDto(search);
		Page<DraftListResponseDto> draftList = draftService.getDraftList(PageRequest.of(page, size, Sort.by("draftId").descending()));
		System.out.println(draftList);
		System.out.println("====================================");
		return new ResponseEntity<>(draftList, HttpStatus.OK);
	}

	@GetMapping("/{draftId}")
	public ResponseEntity<DraftDetailResponseDto> getDraftDetail(@PathVariable("draftId") long draftId) {

		DraftDetailResponseDto draft = draftService.getDraftDetail(draftId);

		return new ResponseEntity<>(draft, HttpStatus.OK);
	}
	
	// 도안 및 팔레트 저장
	@Transactional
	@PostMapping("/")
	public ResponseEntity<DraftCreateResponseDto> createDraft(@RequestBody DraftCreateRequestDto draftCreateDto) {
		int userId = 1;

		return new ResponseEntity<>(draftService.createDraft(1,draftCreateDto), HttpStatus.OK);

	}


	// 도안 정보(해시태그) 수정
	@PostMapping("/{draftId}")
	public ResponseEntity<Draft> updateDraft(@RequestParam Draft draft) {
		// Draft updatedDraft = draftService.updateDraft(draft);
		// return new ResponseEntity<>(updatedDraft, HttpStatus.OK);
		return null;
	}


	// 도안 삭제
	@DeleteMapping("/{draftId}")
	public ResponseEntity<String> deleteDraft(@RequestParam int draftId) {
		// draftService.deleteDraft(draftId);
		// return new ResponseEntity<>("success", HttpStatus.OK);
		return null;
	}


}
