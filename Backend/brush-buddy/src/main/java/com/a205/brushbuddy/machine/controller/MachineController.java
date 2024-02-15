package com.a205.brushbuddy.machine.controller;


import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.machine.dto.MachinePrintRequestDto;
import com.a205.brushbuddy.machine.dto.MachinePrintResponseDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;
import com.a205.brushbuddy.machine.service.MachineService;
import com.a205.brushbuddy.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/api/v1/machine")
@RequiredArgsConstructor
public class MachineController {
    private final MachineService machineService;
    private final JwtUtil jwtUtil;

    // DB에 기기 정보를 저장한다.
    @PostMapping("/register")
    public ResponseEntity<?> registerMachine(@RequestBody MachineRegisterRequestDto requestDto){
        MachineRegisterResponseDto dto = machineService.registerMachine(requestDto);
        return ResponseEntity.ok(dto);
    }

    // 물감을 출력한다.
    // redis로 pub하여 구독 중인 fastapi가 클라이언트에게 요청을 보낸다.
    @PostMapping("/print")
    public ResponseEntity<?> print(@RequestBody MachinePrintRequestDto requestDto, HttpServletRequest request) throws JsonProcessingException {
        // 연결된 기기 ID 가져오기
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        Long machineId = machineService.getLoginMahcineId(userId);

        // WebSocket를 통해 서버에 연결하기 위한 URI
        URI url = UriComponentsBuilder.newInstance()
                .scheme("ws")
                .host("i10a205.p.ssafy.io")
                .path("/ws/print/"+ machineId)
                .port(3004)
                .build().encode(StandardCharsets.UTF_8).toUri();
        log.info(requestDto.toString());
//        // 색깔을 String 형태로 변환
        MachinePrintResponseDto responseDto = machineService.convertRGB2CMYKW(machineId,requestDto);
        ObjectMapper objectMapper = new ObjectMapper();
        String cmd = objectMapper.writeValueAsString(responseDto);

        // WebSocketClient를 통해 서버에 연결
        WebSocketClient client = new ReactorNettyWebSocketClient(HttpClient.from(TcpClient.create(ConnectionProvider.newConnection())));

        // 서버에 연결 후 메시지를 보내고 다시 받음
        client.execute(url, session -> {
            String command = "CMD " + cmd;
            log.info(command);

            return session.send(Mono.just(session.textMessage(command)))
                    // 서버로부터의 메시지를 받음
                    .then(session.receive()
                            .map(WebSocketMessage::getPayloadAsText)
                            .doOnNext(message -> {
                                System.out.println("Received message: " + message);
                            })
                            .then());
        }).subscribe();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{machineId}")
    public ResponseEntity<?> qrcode(@PathVariable Long machineId, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        machineService.connectMachine(userId, machineId);
        return ResponseEntity.ok("기기가 연결되었습니다.");
    }

    @DeleteMapping("/disconnect")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
       machineService.disconnectMachine(userId);
        return ResponseEntity.ok("기기가 연결이 해제되었습니다.");
    }
}
