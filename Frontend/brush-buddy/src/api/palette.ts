import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

type SearchParam = {
    search? : String, // 제목, 내용 기준 검색
    order? : String, // 나열할 기준 (DB컬럼명)
    listNum? : Number, // 페이지 당 가져올 개수 
    pageNum? : Number    // 탐색할 페이지 수  (1 ~ n)
}

type NewPaletteType = {
    draftId : Number,
    paletteName: String
}

type PaletteDetailType = {
    draftImage : String,
    paletteName : String,
    paletteColorCode : Object,
    paletteModifiedTime : Date,
    paletteCreatedAt : Date
}
type PaletteModifyRequestType = {
    paletteId : Number,
    paletteName : String,
    paletteColorCode : Object,
}

export type {
    SearchParam,
    NewPaletteType,
    PaletteDetailType,
    PaletteModifyRequestType
    }

//게시글 리스트 조회 api
const getBoardList = async (
    params: SearchParam
    ) : Promise<AxiosResponse> => {
   return await localAxios().get(`/board/list`, {params : params})
}

// 팔레트 새로 만들기
const newPalette = async (
    data: NewPaletteType
): Promise<AxiosResponse> => {
    return await localAxios().post(`/palette`, data);
}


// 팔레트 상세정보 조회
const detailPalette= async (
    paletteId : Number
): Promise<AxiosResponse> => {
    return await localAxios().get(`/palette/${paletteId}`);
}


//팔레트 복제
const duplicatePalette= async (
    paletteId : Number
): Promise<AxiosResponse> => {
    return await localAxios().post(`/palette/${paletteId}/duplicate`);
}


//팔레트 수정
const modifyPalette= async (
    paletteId : Number,
    data : PaletteModifyRequestType
): Promise<AxiosResponse> => {
    return await localAxios().put(`/palette/${ paletteId }`, data);
}

//팔레트 삭제
const deletePalette= async (
    paletteId : Number
): Promise<AxiosResponse> => {
    return await localAxios().delete(`/palette/${paletteId}`);
}


export {
    getBoardList,
    newPalette,
    detailPalette,
    duplicatePalette,
    modifyPalette,
    deletePalette
}
