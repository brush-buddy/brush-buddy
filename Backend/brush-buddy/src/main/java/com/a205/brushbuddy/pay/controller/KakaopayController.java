package com.a205.brushbuddy.pay.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a205.brushbuddy.mileage.domain.Mileage;
import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequest;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequest;
import com.a205.brushbuddy.pay.dto.response.KakaopayApproveResponse;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponse;
import com.a205.brushbuddy.pay.service.KakaopayService;
import com.a205.brushbuddy.user.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KakaopayController {
    @Value("${datetimeformatter.ofpattern}")
    private static DateTimeFormatter dateTimeFormatter;

    // 브러쉬버디 충전 내역용 workplaceid = 0
    @Value("${workplaceid}")
    private static int workplaceId;

    private final User user;
    private final KakaopayService kakaopayService;

    //// 충전 페이지를 넘어갈 때 이전 url 혹은 구매 시도 아이템 정보 등을 model에 선언 필요
    //// 우선 Url로 작성, 필요의 경우 수정
    private String redirectUrl;

    private String partnerOrderId;
    private int totalAmount;
    private String tid;

    public KakaopayController(User user, KakaopayService kakaopayService) {
        this.user = user;
        this.kakaopayService = kakaopayService;
    }

    // 결제 요청 시작
    @PostMapping("${kakaopay.controller.ready.url}")
    @ResponseBody
    public KakaopayReadyResponse ready(Model model) {
        //// 규칙성, 보안 등의 이유로 변경 가능
        //// itemName = kakaopay 전송 항목 = 충전페이지 선택 항목
        String itemName = "브러쉬버디_" + model.getAttribute("itemName");
        partnerOrderId = user.getUserId() + '_' + itemName + '_' + LocalDateTime.now().format(dateTimeFormatter);
        totalAmount = (int)model.getAttribute("totalAmount");

        redirectUrl = model.getAttribute("redirectUrl").toString();

        KakaopayReadyRequest kakaopayReadyRequest = new KakaopayReadyRequest();
        kakaopayReadyRequest.setPartnerOrderId(partnerOrderId);
        kakaopayReadyRequest.setTotalAmount(totalAmount);
        kakaopayReadyRequest.setPartnerUserId(String.valueOf(user.getUserId()));
        kakaopayReadyRequest.setItemName(itemName);

        //// 취소/오류페이지 작성의 경우, 해당 페이지 이동을 위해 return 값 변경
        //// 혹은 Model에 객체 선언 후 화면에 출력 등 가능
        //// 지금은 단순히 안내와 마일리지 변경없이 이전 페이지로 복귀
        //// Sping 초기화 값은 resources 참조
        kakaopayReadyRequest.setApprovalUrl(redirectUrl);
        kakaopayReadyRequest.setCancelUrl(redirectUrl);
        kakaopayReadyRequest.setFailUrl(redirectUrl);

        model.addAttribute("kakaopayReadyRequest", kakaopayReadyRequest);
        log.info(kakaopayReadyRequest.toString());

        KakaopayReadyResponse kakaopayReadyResponse = kakaopayService.sendReadyRequest(kakaopayReadyRequest);
        tid = kakaopayReadyResponse.getTid();
        model.addAttribute("kakaopayReadyResponse", kakaopayReadyResponse);
        log.info(kakaopayReadyResponse.toString());

        return kakaopayReadyResponse;
    }

    // 결제 완료 이후
    @GetMapping("${kakaopay.ready.request.url.approval}")
    public String completed(Model model,
        @RequestParam("pg_token")
        String pgToken) {

        log.info("completed 시작");

        model.addAttribute("pgToken", pgToken);
        KakaopayApproveRequest kakaopayApproveRequest = new KakaopayApproveRequest();
        kakaopayApproveRequest.setTid(tid);
        kakaopayApproveRequest.setPartnerOrderId(partnerOrderId);
        kakaopayApproveRequest.setPartnerOrderId(String.valueOf(user.getUserId()));
        kakaopayApproveRequest.setPgToken(pgToken);

        model.addAttribute("kakaopayApproveRequest", kakaopayApproveRequest);
        log.info(kakaopayApproveRequest.toString());

        KakaopayApproveResponse kakaopayApproveResponse = kakaopayService.sendApproveRequest(kakaopayApproveRequest);
        model.addAttribute("KakaopayApproveResponse", kakaopayApproveResponse);
        log.info(kakaopayApproveResponse.toString());

        log.info("completed kakaopay 종료");

        log.info("completed JPA 시작");

        //// JPA 충전 기능을 API 내부가 아닌 호출 전후에 해야하는가
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("brush-buddy");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Mileage mileage = new Mileage();
            mileage.setUserId(user.getUserId());
            mileage.setWorkplaceId(workplaceId);
            mileage.setMileageBefore(user.getUserMileage());
            mileage.setMileageAmount(user.getUserMileage() + totalAmount);
            user.setUserMileage(user.getUserMileage() + totalAmount);
            mileage.setMileageAfter(user.getUserMileage());
            mileage.setMileageContent("마일리지 충전 : " + partnerOrderId);
            log.info(mileage.toString());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            log.error("결제 이후 마일리지 반영 오류" + e.toString());
            System.err.println("결제 이후 마일리지 반영 오류" + e.toString());
            return "redirect:" + redirectUrl;
        } finally {
            em.close();
            emf.close();
        }

        log.info("작업 데이터 출력");
        log.info(((KakaopayReadyRequest)model.getAttribute("kakaopayReadyRequest")).toString());
        log.info(((KakaopayReadyResponse)model.getAttribute("kakaopayReadyResponse")).toString());
        log.info(((String)model.getAttribute("pgToken")).toString());
        log.info(((KakaopayApproveRequest)model.getAttribute("kakaopayApproveRequest")).toString());
        log.info(((KakaopayApproveResponse)model.getAttribute("kakaopayApproveResponse")).toString());

        //// 처리 이후 정보 삭제
        model.addAttribute("kakaopayReadyRequest", null);
        model.addAttribute("kakaopayReadyResponse", null);
        model.addAttribute("pgToken", null);
        model.addAttribute("kakaopayApproveRequest", null);
        model.addAttribute("kakaopayApproveResponse", null);

        log.info("작업 데이터 삭제");
        log.info(((KakaopayReadyRequest)model.getAttribute("kakaopayReadyRequest")).toString());
        log.info(((KakaopayReadyResponse)model.getAttribute("kakaopayReadyResponse")).toString());
        log.info(((String)model.getAttribute("pgToken")).toString());
        log.info(((KakaopayApproveRequest)model.getAttribute("kakaopayApproveRequest")).toString());
        log.info(((KakaopayApproveResponse)model.getAttribute("kakaopayApproveResponse")).toString());

        log.info("completed 종료");
        return "redirect:" + redirectUrl;
    }
}
