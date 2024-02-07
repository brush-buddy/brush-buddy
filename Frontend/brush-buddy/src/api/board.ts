import { localAxios } from "./axios";

const getBoardList = (params:any , success:any , fail:any) => {
    localAxios().get('/board/list')
}

const writeBoard = () => {
    localAxios().post('/board')
}

export {getBoardList , writeBoard};