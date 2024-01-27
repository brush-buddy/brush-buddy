package com.a205.brushbuddy.draft.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.a205.brushbuddy.draft.dto.request.DraftListRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.service.DraftService;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/draft")
public class DraftController {

	@Autowired
	private DraftService draftService;



	@GetMapping("/list")
	public ResponseEntity<Page<DraftListResponseDto>> getDrafts(
		@RequestParam(required = false) String search,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int page) {

		DraftListRequestDto draftListRequestDto = new DraftListRequestDto(search);
		Page<DraftListResponseDto> draftList = draftService.getDraftList(draftListRequestDto, PageRequest.of(page, size, Sort.by("draftId").descending()));

		return new ResponseEntity<>(draftList, HttpStatus.OK);
	}

}
