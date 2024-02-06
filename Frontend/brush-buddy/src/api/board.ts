import { instance } from "./axios";

const getBoardList = (params, success, fail) => {
    instance.get(params.get)
}