package com.a205.brushbuddy.machine.service;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.machine.domain.Machine;
import com.a205.brushbuddy.machine.domain.OwnerType;
import com.a205.brushbuddy.machine.dto.MachinePrintRequestDto;
import com.a205.brushbuddy.machine.dto.MachinePrintResponseDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;
import com.a205.brushbuddy.machine.repository.MachineRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.workplace.domain.Workplace;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService{
    public final MachineRepository machineRepository;

    @Override
    public MachineRegisterResponseDto registerMachine(MachineRegisterRequestDto requestDto) {
        Machine machine = Machine.builder()
                .machineId(requestDto.getMachineId())
                .workplaceId(Workplace.builder().workplaceId(requestDto.getWorkplaceId()).build())
                .machinePaintAmount(0)
                .owner(OwnerType.valueOf(requestDto.getOwner()))
                .user(User.builder().userId(requestDto.getUserId()).build())
                .build();
        machineRepository.save(machine);
        return null;
    }



    @Transactional
    @Override
    public boolean connectMachine(Integer userId, Long machineId) {
        //연결하려는 기기가 있는지 확인
        Machine machine= machineRepository.findById(machineId)
                .orElseThrow(()-> new BaseException(ErrorCode.NOT_FOUND_DATA));

        // 만약 이미 사용중인 기기가 있다면
        Optional<Machine> usedMachine= machineRepository.findByUser_UserId(userId);
        if(usedMachine.isPresent()){
            usedMachine.get().setUser(null); // 해당 userid 초기화
        }

        machine.setUser(User.builder().userId(userId).build());
        return true;
    }

    @Transactional
    @Override
    public boolean disconnectMachine(Integer userId) {
        Machine machine= machineRepository.findByUser_UserId(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.NOT_FOUND_DATA));
        machine.setUser(null);
        return true;
    }

    @Override
    public Long getLoginMahcineId(Integer userId) {
        Machine machine= machineRepository.findByUser_UserId(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.NOT_FOUND_DATA));
        return machine.getMachineId();
    }

    @Override
    public MachinePrintResponseDto convertRGB2CMYKW(Long machineId, MachinePrintRequestDto requestDto) {
        String color = requestDto.getRgbcode()
                .substring(1);

        int[] cmykw = rgbToCmyk(color);

        return MachinePrintResponseDto.builder()
                .id(machineId.toString())
                .color(MachinePrintResponseDto.ColorDTO.builder()
                        .c(cmykw[0])
                        .m(cmykw[1])
                        .y(cmykw[2])
                        .k(cmykw[3])
                        .w(cmykw[4])
                        .build())
                .build();
    }

    // input - output 메소드
    private static int[] rgbToCmyk(String color) {
        // data : 함수 사용을 위한 배열
        int r, g, b, w, k, c, m, y, data[];
        // per를 위한 double
        double doubleR, doubleG, doubleB, doubleW, doubleK, doubleC, doubleM, doubleY;

        // 입력 문자 10진수 수치화
        r = Integer.parseInt(color.substring(0, 2), 16);
        g = Integer.parseInt(color.substring(2, 4), 16);
        b = Integer.parseInt(color.substring(4), 16);
        // RGB 출력
        System.out.println("-----input RGB data-----");
        data = new int[] {r, g, b};
        hexPrint(data);
        decPrint(data);

        // 0 - 255
        k = 255 - max(r, g, b);// 빛의 삼원색 최댓값을 제외한 값 = K 최대 경우
        // k 값 조절
        k /= 2;
        w = min(r, g, b); // 전체에서 RGB의 수치의 최소값 = W 최대 경우

        // 무채색 (R = G = B) (C = M = Y = 0)
        if (r == g && g == b && b == r) {
            System.out.println("-----KW data-----");
            data = new int[] {k, w};
            decPrint(data);
            PerPrint(data);
            // 따라서 CMY 계산 생략하고 리턴
            return new int[] {0, 0, 0, toPercent(k), toPercent(w)};
        }

        // 색상 + 검은색, W = 0일 경우만 유효
        else if (w == 0) {
            System.out.println("-----RGBK data-----");
            data = new int[] {r, g, b, k};
            hexPrint(data);
            decPrint(data);
            PerPrint(data);
        }
        // 색상 + 흰색, 조건문 필요 없이 else로도 가능
        else if (w != 0) {
            System.out.println("-----RGBW data-----");
            data = new int[] {r - w, g - w, b - w, w};
            hexPrint(data);
            decPrint(data);
            PerPrint(data);
        }

        // CMYKW 변환을 위한 할당율로 변환 0.0 - 1.0
        doubleR = r / 255.0;
        doubleG = g / 255.0;
        doubleB = b / 255.0;
        doubleW = w / 255.0;
        doubleK = k / 255.0;

        if (k != 255) {
            // K = 흰색(255) - 빛의 3원색의 최댓값(= K 최소 경우)
            // C = B + G
            // M = R + B
            // Y = R + G
            doubleC = (1.0 - doubleR - doubleK) / (1 - doubleK);
            doubleM = (1.0 - doubleG - doubleK) / (1 - doubleK);
            doubleY = (1.0 - doubleB - doubleK) / (1 - doubleK);
            c = (int)(doubleC * 100 + 0.5);
            m = (int)(doubleM * 100 + 0.5);
            y = (int)(doubleY * 100 + 0.5);

            // 이전에 선언한 변수를 범위 수정 (0 - 255) -> (0 - 100)
            w = (int)(doubleW * 100 + 0.5);
            k = (int)(doubleK * 100 + 0.5);

            if (w == 0) {
                // W = 0 이면 CMY(+K)인 상태
                System.out.println("-----CMYK data-----");
                data = new int[] {c, m, y, k};
                decPrint(data);
                PerPrint(data);
            } else if (k == 0) {
                // K = 0 이면 CMY(+W)인 상태
                System.out.println("-----CMYW data-----");
                data = new int[] {c, m, y, w};
                decPrint(data);
                PerPrint(data);
            }
            // CMYKW 이용
            return new int[] {c, m, y, k, w};
        }
        // K 이용
        return new int[] {0, 0, 0, 255, 0};
    }

    // 수치 리턴 메소드
    private static String value(int[] arr, String form) {
        StringBuilder builder = new StringBuilder();
        if (form.startsWith("%3H")) {
            builder.append("Hex :\t");
        } else {
            builder.append("Dec :\t");
        }
        Arrays.stream(arr).forEach(e -> builder.append(String.format(form, e)));
        return builder.toString();
    }

    // 퍼센트 리턴 메소드
    private static String percent(int[] arr) {
        StringBuilder builder = new StringBuilder();
        int sum = Arrays.stream(arr).sum();
        builder.append("Per :\t");
        if (sum == 0) {
            for (int i = 0; i < arr.length; ++i) {
                builder.append("000\t");
            }
        } else {
            Arrays.stream(arr).forEach(e -> builder.append(String.format("%03d\t", (int)100.0 * e / sum)));
        }
        return builder.toString();
    }

    // 수치데이터 Hex 출력 메소드
    private static void hexPrint(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(value(arr, "%3H\t")).append('\n');
        System.out.print(sb.toString());
    };

    // 데이터데이터 Dec 출력 메소드
    private static void decPrint(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(value(arr, "%03d\t")).append('\n');
        System.out.print(sb.toString());
    };

    // 데이터데이터 Per 출력 메소드
    private static void PerPrint(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(percent(arr)).append('\n');
        System.out.print(sb.toString());
    };

    private static int min(int i, int j, int k) {
        return Math.min(Math.min(i, j), k);
    }

    private static int max(int i, int j, int k) {
        return Math.max(Math.max(i, j), k);
    }

    private static int toPercent(int i) {
        return (int)(i * 100.0 / 255.0 + 0.5);
    }

}
