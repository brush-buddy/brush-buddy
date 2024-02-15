package com.a205.brushbuddy.mileage.service;

import com.a205.brushbuddy.mileage.domain.MileageLog;
import com.a205.brushbuddy.mileage.repository.MileageLogRepository;
import jakarta.transaction.Transactional;
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
    private final MileageLogRepository mileageLogRepository;

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
    public Long requestOrderInfo(int price, int userId) {
        MileageLog mileageLog = new MileageLog();
        mileageLog.setUser(User.builder().userId(userId).build());
        mileageLog.setPrice(price);
        mileageLog.setMileageLogStatus("충전");

        // 주문 요청 후에 주문 번호를 가지고 옵니다.
        return mileageLogRepository.save(mileageLog).getMileageLogId();
    }


    // db 마일리지 tid set
    @Override
    @Transactional
    public void setMileageTid(Long mileageLogId, String tid) {
        MileageLog mileageLog = mileageLogRepository.findByMileageLogId(mileageLogId)
            .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        mileageLog.setTid(tid);
        mileageLogRepository.save(mileageLog);
    }


    // Tid 기준으로 마일리지 로그 가져오기 -> 마일리지 번호적용
    @Override
    public MileageLog getMileageLog(String tid) {
        return mileageLogRepository.findByTid(tid)
            .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }


    // 두번째 호출 이후에 결제 확정나면 db 적용
    @Override
    @Transactional
    public void addMileage(int userId, Long mileageLogId) {
        MileageLog mileageLog = mileageLogRepository.findByMileageLogId(mileageLogId)
            .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        User user = userRepository.findUserByUserId(userId);
        Mileage mileage = Mileage.builder()
            .user(User.builder().userId(userId).build())
            .mileageBefore(user.getUserMileage())
            .mileageAfter(user.getUserMileage() + mileageLog.getPrice())
            .mileageAmount(mileageLog.getPrice())
            .mileageContent("충전")
            .build();
        mileageRepository.save(mileage);

        user.setUserMileage(user.getUserMileage() + mileageLog.getPrice());
        userRepository.save(user);
        mileageLog.setMileageLogStatus("충전완료");
        mileageLogRepository.save(mileageLog);
    }

    @Override
    public Page<Mileage> getMileageHistory(int userId, Pageable pageable) {
        return mileageRepository.findAllByUserOrderByMileageTimestampDesc(userId, pageable);
    }
}
