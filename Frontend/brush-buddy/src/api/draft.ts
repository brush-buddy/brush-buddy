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

//북마크 추가 api
const addBookmark = async (
    draftId: Number,
): Promise<AxiosResponse> => {
    return await localAxios().post(`/draft/${draftId}/bookmark`)
}

//북마크 취소 api
const removeBookmark = async (
    draftId: Number,
): Promise<AxiosResponse> => {
    return await localAxios().delete(`/draft/${draftId}/bookmark`)
}

export {
    getMadeList,
    getPurchaseList,
    addBookmark,
    removeBookmark
};
    
interface DraftPipoInfo { 
    pipoUrl? : String,
    pipoPalette?: JSON,
    isAI?: Boolean,
    prompt?: String
}

interface DraftThumbnail { 
    draftBookmark?: number,
    draftDownload?: number,
    draftId?: number,
    draftThumbnail?: string 
}
export type {DraftPipoInfo, DraftThumbnail}
