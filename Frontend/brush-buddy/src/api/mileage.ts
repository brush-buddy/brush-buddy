import { localAxios } from "./axios";
import type { AxiosResponse } from "axios";

type Mileage = {
    mileageId: number,
    userId: number,
    workplaceId: number,
    mileageTimestamp: String,
    mileageBefore: number,
    mileageAfter: number,
    mileageAmount: number,
    mileageContent: String,
}

type MileageHistoryReqeustParam = {
    pageNum?: number,
    listNum?: number,
}

type MileageSpendRequestParam = {
    workplaceId: number,
    mileageAmount: number,
    mileageContent: String,
}


const getMileageHistory =
    async (params: MileageHistoryReqeustParam):
        Promise<AxiosResponse> => { return await localAxios().get('/mileage/history', { params: params }) };

export type {
    Mileage,
    MileageHistoryReqeustParam,
    MileageSpendRequestParam,
}

export { 
    getMileageHistory,
}