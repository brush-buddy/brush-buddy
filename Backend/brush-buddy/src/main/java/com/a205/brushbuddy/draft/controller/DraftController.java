package com.a205.brushbuddy.draft.controller;

import com.a205.brushbuddy.draft.dto.request.DraftCategoryModifyRequestDto;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.a205.brushbuddy.draft.dto.request.DraftListRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftCreateResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftDetailResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.service.DraftService;
import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/draft")
public class DraftController {

	@Autowired
	private DraftService draftService;

	private final JwtUtil jwtUtil;

	@Operation(description = "도안 리스트 ")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 리스트 반환 성공")
	})
	@ResponseBody
	@GetMapping("/list")
	public ResponseEntity<Page<DraftListResponseDto>> getDrafts(
		@RequestParam(required = false) String search,
		@RequestParam(defaultValue = "5") int size,
		@RequestParam(defaultValue = "0") int page
	) {
		if(search == null) {
			DraftListRequestDto draftListRequestDto = new DraftListRequestDto(search);
			Page<DraftListResponseDto> draftList = draftService.getDraftList(PageRequest.of(page, size, Sort.by("draftId").descending()));
			return new ResponseEntity<>(draftList, HttpStatus.OK);
		}else{
			Page<DraftListResponseDto> draftList = draftService.getDraftListByCategory(PageRequest.of(page, size, Sort.by("draftId").descending()), search);

			return new ResponseEntity<>(draftList, HttpStatus.OK);
		}

	}

	@Operation(description = "도안 상세정보")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 리스트 반환 성공")
	})
	@ResponseBody
	@GetMapping("/{draftId}")
	public ResponseEntity<DraftDetailResponseDto> getDraftDetail(@PathVariable("draftId") long draftId) {

		DraftDetailResponseDto draft = draftService.getDraftDetail(draftId);

		return new ResponseEntity<>(draft, HttpStatus.OK);
	}
	
	// 도안 및 팔레트 저장

	@Operation(description = "도안 작성 ")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 리스트 반환 성공")
	})
	@ResponseBody
	@PostMapping("/")
	public ResponseEntity<DraftCreateResponseDto> createDraft(@RequestBody DraftCreateRequestDto draftCreateDto, HttpServletRequest request) throws
		JsonProcessingException {
		Integer userId = jwtUtil.getUserId(request)
				.orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
		return new ResponseEntity<>(draftService.createDraft(userId, draftCreateDto), HttpStatus.OK);

	}


	// 도안 정보(해시태그) 수정
	@Operation(description = "도안 정보 해시태그 수정 ")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 수정 성공"),

	})
	@ResponseBody
	@PostMapping("/{draftId}")
	public ResponseEntity<String> updateDraft(@PathVariable long draftId, @RequestBody DraftCategoryModifyRequestDto draftCategoryModifyRequestDto, HttpServletRequest request) {
		Integer userId = jwtUtil.getUserId(request)
				.orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

		boolean check = draftService.updateDraft(draftId, draftCategoryModifyRequestDto);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}


	// 도안 삭제
	@Operation(description = "도안 정보 삭제")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 삭제 성공"),
			@ApiResponse(responseCode = "401", description = "권한 없음")
	})
	@ResponseBody
	@DeleteMapping("/{draftId}")
	public ResponseEntity<String> deleteDraft(@PathVariable Long draftId, HttpServletRequest request) throws Exception{
		Integer userId = jwtUtil.getUserId(request)
				.orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

		draftService.deleteDraft(userId, draftId);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}


	// 북마크
	@Operation(description = "도안 북마크 작성")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 북마크 추가")
	})
	@ResponseBody
	@PostMapping("/{draftId}/bookmark")
	public ResponseEntity<String> createBookmark(@PathVariable Long draftId, HttpServletRequest request) throws Exception{
		Integer userId = jwtUtil.getUserId(request)
				.orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
		draftService.createBookmarkDraft(userId, draftId);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@Operation(description = "도안 북마크 삭제")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 리스트 반환 성공")
	})
	@ResponseBody
	@DeleteMapping("/{draftId}/bookmark")
	public ResponseEntity<String> deleteBookmark(@PathVariable Long draftId, HttpServletRequest request) {
		Integer userId = jwtUtil.getUserId(request)
				.orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
		draftService.deleteBookmarkDraft(userId, draftId);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@Operation(description = "도안 구매")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "도안 구매 성공"),
			@ApiResponse(responseCode = "403", description = "도안 잔액 부족")
	})
	@ResponseBody
	@PostMapping("/{draftId}/purchase")
	public ResponseEntity<String> buyDraft(@PathVariable Long draftId, HttpServletRequest request) throws Exception{
		Integer userId = jwtUtil.getUserId(request)
				.orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

		draftService.buyDraft(userId, draftId);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}


}
