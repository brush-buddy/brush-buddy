package com.a205.brushbuddy.mileage.service;

import org.springframework.stereotype.Service;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mileage.dto.request.MileageSpendRequestDto;
import com.a205.brushbuddy.mileage.repository.InsertMileageRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class MileageSpendService {
	private InsertMileageRepository insertMileageRepository;

	public void spendMileage(MileageSpendRequestDto mileageSpendRequestDto) {
		int mileageBefore = mileageSpendRequestDto.getMileageBefore();
		int mileageAmount = mileageSpendRequestDto.getMileageAmount();
		int mileageAfter = mileageBefore - mileageAmount;
		if (mileageAfter < 0) {
			// 마일리지 부족, 충전을 위한 에러
			throw new BaseException(ErrorCode.BAD_REQUEST);
		}

		Long userId = mileageSpendRequestDto.getUserId();
		Integer workplaceId = mileageSpendRequestDto.getWorkplaceId();
		String mileageContent = mileageSpendRequestDto.getMileageContent();
		insertMileageRepository.insertSpentMileage(userId, workplaceId, mileageBefore, mileageAfter, mileageAmount,
			mileageContent);
	}
}
