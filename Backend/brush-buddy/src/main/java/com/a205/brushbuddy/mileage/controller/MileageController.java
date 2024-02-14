package com.a205.brushbuddy.mileage.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mileage.domain.Mileage;
import com.a205.brushbuddy.mileage.dto.request.MileageHistoryReqeustDto;
import com.a205.brushbuddy.mileage.dto.request.MileageSpendRequestDto;
import com.a205.brushbuddy.mileage.dto.response.MileageHistoryResponseDto;
import com.a205.brushbuddy.mileage.service.MileageService;
import com.a205.brushbuddy.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/api/v1/milage")
@RequiredArgsConstructor
public class MileageController {
    private final MileageService mileageService;
    private final JwtUtil jwtUtil;

    @PostMapping("/spend")
    public ResponseEntity<?> spendMileage(@RequestBody MileageSpendRequestDto mileageSpendRequestDto,
        HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request).orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
        // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        mileageService.spendMileage(userId, mileageSpendRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    public ResponseEntity<MileageHistoryResponseDto> getHistory(MileageHistoryReqeustDto mileageHistoryReqeustDto,
        HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request).orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
        Pageable pageable = PageRequest.of(mileageHistoryReqeustDto.getPageNum() - 1,
            mileageHistoryReqeustDto.getListNum());

        Page<Mileage> result = mileageService.getMileageHistory(userId, pageable);
        MileageHistoryResponseDto mileageHistoryResponseDto = MileageHistoryResponseDto.builder()
            .history(result)
            .length(result.getNumberOfElements())
            .pageNum(result.getNumber() + 1)
            .totalPage(result.getTotalPages())
            .build();

        return new ResponseEntity<MileageHistoryResponseDto>(mileageHistoryResponseDto, HttpStatus.OK);
    }

    @PostMapping("/make-payment")
    public String makePayment(HttpServletRequest request, @RequestParam("mileage") int mileage) {

        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(
                        ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        try {
            URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/ready");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "SECRET_KEY csM1qC31YmUpHg2Rt74s3EOOBKqdzHWI");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            // 구매 정보를 들고 옵니다.
            Long mileageNumber = mileageService.requestOrderInfo(mileage, userId);


            // 구매정보의 pk를 order id로 두고 보내줍니다.
            String params =
                    "cid=TC0ONETIME&" +
                            "partner_order_id=" + mileageNumber +
                            "&partner_user_id=" + userId +
                            "&item_name=마일리지_충전" +
                            "&quantity=1" +
                            "&total_amount=" + mileage +
                            "&tax_free_amount=0" +
                            "&approval_url="+ "http://localhost:5173/pay/success" +
                            "&cancel_url=" + "http://localhost:5173/pay/cancel" +
                            "&fail_url=" + "http://localhost:5173/pay/fail";
            OutputStream os = conn.getOutputStream();
            DataOutputStream writer = new DataOutputStream(os);
            writer.writeBytes(params);
            writer.flush();
            writer.close();

            int responseCode = conn.getResponseCode();

            InputStream is;
            if(responseCode== 200) {
                is = conn.getInputStream();
            } else {
                is = conn.getErrorStream();
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            return br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
