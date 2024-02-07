
export interface BoardListRes{
    boards:{
        boardId : number;
        boardTitle : string;
        thumbnail : string;
        likeNumber : number;
        views : number;
    };
    pageNum: number;
    length: number;
    totalPage: number;
}

export interface BoardThumbnail {
    boardId : string;
    boardTitle : string;
    thumbnail : string;
    likeNumber : number;
    views : number;
}