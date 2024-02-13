import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

type KakaopayReadyRequestType = {
    cid: String,
    partnerOrderId: String,
    partnerUserId: String,
    itemName: String,
    quantity: Number,
    totalAmount: Number,
    taxFreeAmount: Number,
    redirectUrl: String,
    approvalUrl: String,
    cancelUrl: String,
    failUrl: String,
}

type KakaopayReadyResponseType = {
    tid: String,
    nextRedirectPcUrl: String,
    nextRediretAppUrl: String,
    nextRedirectMobileUrl: String,
    androidAppScheme: String,
    iosAppScheme: String,
}

type KakaopayApproveRequestType = {
    cid: String,
    tid: String,
    partnerOrderId: String,
    partnerUserId: String,
    pgToken: String,
}

export type {
    KakaopayReadyRequestType,
    KakaopayReadyResponseType,
    KakaopayApproveRequestType
}
