package com.a205.brushbuddy.mileage.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mileage.dto.MileageDto;
import com.a205.brushbuddy.mileage.dto.request.MileageHistoryRequestDto;
import com.a205.brushbuddy.mileage.repository.SelectMileageRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class MileageHistoryService {
	private SelectMileageRepository selectMileageRepository;

	public List<MileageDto> getMileageList(MileageHistoryRequestDto mileageHistoryRequestDto) {
		if (!mileageHistoryRequestDto.inRange()) {
			System.err.println("term between start and end is out of range");
			System.err.println("limit of term(year): " + mileageHistoryRequestDto.getCountOfYear());
			return null;
		}
		// Category category = mileageHistoryRequestDto.getCategory();
		String category = mileageHistoryRequestDto.getCategory();
		Long userId = mileageHistoryRequestDto.getUserId();
		Timestamp start = mileageHistoryRequestDto.getStart();
		Timestamp end = mileageHistoryRequestDto.getEnd();
		boolean isValid = mileageHistoryRequestDto.isValid();
		if (!isValid) {
			throw new BaseException(ErrorCode.BAD_REQUEST);
		}
		switch (category) {
			case "all":
				return selectMileageRepository.findAllMileageBetweenStartAndEnd(userId, start, end);
			case "charged":
				return selectMileageRepository.findChargedMileageBetweenStartAndEnd(userId, start, end);
			case "spent":
				return selectMileageRepository.findSpentMileageBetweenStartAndEnd(userId, start, end);
			default:
				throw new BaseException(ErrorCode.BAD_REQUEST);
		}
	}
}
