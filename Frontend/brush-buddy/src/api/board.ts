import { instance } from "./axios";

const getBoardList = (params:any , success:any , fail:any) => {
    instance.get(params.get)
}