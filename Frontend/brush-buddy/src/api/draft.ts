import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

type BoardGetParam = {
    listNum? : Number, // 페이지 당 가져올 개수 
    pageNum? : Number    // 탐색할 페이지 수  (1 ~ n)
}

type DraftPurchase = {
    draftId: number;
    draftThumbnail: string;
    draftTimestamp: string;
    draftDownload: number;
    draftBookmark: number;
  }
  type DraftMade = {
    draftId: number;
    draftThumbnail: string;
    draftTimestamp: string;
  }
export type {
    BoardGetParam,
    DraftPurchase,
    DraftMade
}
// 만든 도안 조회
const getMadeList = async (
    params: BoardGetParam
    ) : Promise<AxiosResponse> => {
   return await localAxios().get('/mypage/generate/list', {params : params})
}
// 구매 도안 조회
const getPurchaseList = async (
    params: BoardGetParam
    ) : Promise<AxiosResponse> => {
   return await localAxios().get('/mypage/payments/list', {params : params})
}
export {
    getMadeList,
    getPurchaseList
};