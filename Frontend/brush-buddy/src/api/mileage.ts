import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

type Mileage = {
    date: String,
    amount: Number,
    after: Number,
    content: String,
}

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
    Mileage,
    MileageHistoryReqeustType,
    MileageSpendRequestType
}