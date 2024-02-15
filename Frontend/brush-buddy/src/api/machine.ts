import { localAxios } from "./axios";

type MachineRequestType = {
    rgbcode : String
}

export type {
    MachineRequestType
}
// 출력 요청 
const printPaint = async (
    data : MachineRequestType
) => {
    return await localAxios().post("/machine/print", data)
}

//기기 연결 요청
const connectMachine = async (
    machineId : Number
) => {
    return await localAxios().post(`/machine/${machineId}`)
}

//기기 해제 요청
const disconnectMachine = async (
) => {
    return await localAxios().delete(`/machine/disconnect`)
}

export {
    printPaint,
    connectMachine,
    disconnectMachine
}