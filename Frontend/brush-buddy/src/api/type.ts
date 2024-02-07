
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

export interface BoardThumbnail{
        boardId : string;
        boardTitle : string;
        thumbnail : string;
        likeNumber : number;
        views : number;
}

export interface HeartList {
    boardId: number;
    boardTitle: string;
    createdAt: string;
    likeNumber: number;
    thumbnail: string;
    views: number;

}

export interface HeartListRes {
    boards: {
      boardId: number;
      boardTitle: string;
      createdAt: string;
      likeNumber: number;
      thumbnail: string;
      views: number;
    }[];
    pageNum: number;
    length: number;
    totalPage: number;
  }
