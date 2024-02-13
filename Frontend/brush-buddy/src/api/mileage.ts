import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

type MileageHistoryReqeustType = {
    listNum: Number,
    pageNum: Number,
}

type MileageSpendRequestType = {
    workplaceId: Number,
    mileageAmount: Number,
    mileageContent: String,
}

export type {
    MileageHistoryReqeustType,
    MileageSpendRequestType
}