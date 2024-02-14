import type { AxiosResponse } from "axios";
import { localAxios } from "./axios";

type BoardSearchParam = {
    search? : String, // 제목, 내용 기준 검색
    order? : String, // 나열할 기준 (DB컬럼명)
    listNum? : Number, // 페이지 당 가져올 개수 
    pageNum? : Number    // 탐색할 페이지 수  (1 ~ n)
}

type BoardImage = {
    order : Number,
    img: String
}

type BoardDetail = {
    title : String,
    contents : String,
    hashtags : String[],
    photo : {
        order : Number,
        img: String
    }[],
    draftId? : Number
}

type BoardListElement = {
    boardId : Number,
    title : String,
    thumbnail : String,
    likeNumber : Number,
    view : Number,
    createdAt : Date
}

type ReplyListElement = {
    replyId : Number,
    userId : Number,
    nickname: String,
    contents: String,
    createdAt: Date,
    isMine: Boolean,
}

export type {
        BoardSearchParam,
        BoardImage,
        BoardDetail,
        BoardListElement,
        ReplyListElement
    }

//게시글 리스트 조회 api
const getBoardList = async (
    params: BoardSearchParam
    ) : Promise<AxiosResponse> => {
   return await localAxios().get('/board/list', {params : params})
}

// 게시글 작성 api
const writeBoard = async (
    data: BoardDetail
    ) : Promise<AxiosResponse> => {
        console.log("writeBoard called ",data)
    return await localAxios().post('/board', data)
}

// 게시글 상세정보 api
const detailBoard = async (
    boardId : Number
): Promise<AxiosResponse> => {
    return await localAxios().get(`/board/${boardId}`)
}

// 게시글 수정 api
const modifyBoard = async (
    boardId : Number,
    data : BoardDetail
): Promise<AxiosResponse> => {
    return await localAxios().put(`/board/${boardId}`, data)
}

//게시글 삭제 api
const deleteBoard =  async (
    boardId : Number
) : Promise<AxiosResponse> => {
    return await localAxios().delete(`/board/${boardId}`)
}

//게시글 댓글 리스트 조회 api
const getReplyList =  async (
    boardId : Number,
    params : BoardSearchParam
) : Promise<AxiosResponse> => {
    return await localAxios().get(`/board/${boardId}/replies`, {params :params})
}

//댓글 달기 api
const writeReply =  async (
    boardId : Number,
    contents : String
): Promise<AxiosResponse> => {
    return await localAxios().post(`/board/${boardId}/replies`, {contents : contents})
}

//댓글 삭제 api
const deleteReply =  async (
    boardId : Number,
    replyId : Number
): Promise<AxiosResponse> => {
    return await localAxios().delete(`/board/${boardId}/replies/${replyId}`)
}

//좋아요 추가 api
const addHeart =  async (
    boardId : Number,
): Promise<AxiosResponse> => {
    return await localAxios().post(`/board/${boardId}/heart`)
}

//좋아요 취소 api
const removeHeart =  async (
    boardId : Number,
): Promise<AxiosResponse> => {
    return await localAxios().delete(`/board/${boardId}/heart`)
}



export {
    getBoardList,
    writeBoard,
    detailBoard,
    modifyBoard,
    deleteBoard,
    getReplyList,
    writeReply,
    deleteReply,
    addHeart,
    removeHeart
};