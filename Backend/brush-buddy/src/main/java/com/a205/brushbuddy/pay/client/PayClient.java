package com.a205.brushbuddy.pay.client;

import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequestDto;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequestDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payClient", url="https://open-api.kakaopay.com")
public interface PayClient {

    @PostMapping(value="/online/v1/payment/ready", headers = {"Authorization=SECRET_KEY DEVDD8E3EA2EE9078082C6CA690874EC758C34FC"
    ,"Content-Type=application/json"})
    ResponseEntity<KakaopayReadyResponseDto> makePayment(@RequestBody KakaopayApproveRequestDto kakaopayApproveRequestDto);


    @PostMapping(value="/online/v1/payment/approve", headers = {"Authorization=SECRET_KEY DEVDD8E3EA2EE9078082C6CA690874EC758C34FC"
            ,"Content-Type=application/json"})
    ResponseEntity<KakaopayReadyResponseDto> savePayment(@RequestBody KakaopayReadyRequestDto kakaopayReadyRequestDto);
}
