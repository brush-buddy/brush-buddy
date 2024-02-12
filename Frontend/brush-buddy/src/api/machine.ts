import { localAxios } from "./axios";

type MachineRequestType = {
    id : Number,
    color : String

}

export type {
    MachineRequestType
}

const printPaint = async (
    data : MachineRequestType
) => {
    return await localAxios().post("/machine/print", data)
}

export {
    printPaint
}