package com.a205.brushbuddy.mileage.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mileage.domain.Mileage;
import com.a205.brushbuddy.mileage.dto.request.MileageSpendRequestDto;
import com.a205.brushbuddy.mileage.repository.MileageRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MileageServiceImpl implements MileageService {
    private final MileageRepository mileageRepository;
    private final UserRepository userRepository;

    @Override
    public Mileage spendMileage(int userId, MileageSpendRequestDto mileageSpendRequestDto) {
        User user = userRepository.findUserByUserId(userId);
        int mileageAfter = user.getUserMileage() - mileageSpendRequestDto.getMileageAmount();
        if (mileageAfter < 0) {
            // 마일리지 부족, 충전을 위한 에러
            throw new BaseException(ErrorCode.BAD_REQUEST);
        }
        Mileage mileage = Mileage.builder()
            .user(User.builder().userId(userId).build())
            .workplaceId(mileageSpendRequestDto.getWorkplaceId())
            .mileageBefore(user.getUserMileage())
            .mileageAfter(mileageAfter)
            .mileageAmount(mileageSpendRequestDto.getMileageAmount())
            .mileageContent(mileageSpendRequestDto.getMileageContent())
            .build();

        mileageRepository.save(mileage);
        return mileage;
    }

    @Override
    public Page<Mileage> getMileageHistory(int userId, Pageable pageable) {
        return mileageRepository.findAllByUserOrderByMileageTimestampDesc(userId, pageable);
    }
}
