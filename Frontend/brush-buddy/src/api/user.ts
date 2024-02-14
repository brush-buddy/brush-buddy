import type { AxiosResponse } from "axios";
import { useUserStore } from "../stores/user";
import { localAxios, instance } from "./axios";

// 인가코드 전송하여 우리서버로 접속하는 메소드
function login(params: Object, success: (data:string) => void, fail: (error:string)=>void) {
  localAxios().get('/auth', {params : params})
  .then(({data}) => success(data))
  .catch(fail);
}

async function isLogin(){
  return await instance.get("/auth/isLogin");
}

// 리프레쉬 토큰 재발급 
async function getRefresh() : Promise<AxiosResponse>{
    return await instance.get('/auth/refresh');
}

// 로그아웃  
async function signOut() {
    const userStore = useUserStore();
    userStore.setAccessToken(''); // accessToken 날리기
    await instance.post("/auth/logout"); // 로그아웃 요청
    window.location.href = '/'; // 처음 위치로 돌아가기
}

// 회원 탈퇴 
async function withDraw(){
    const userStore = useUserStore();
    await localAxios().delete("/auth"); // 탈퇴 요청
    userStore.setAccessToken(''); // accessToken 날리기
    window.location.href = '/';// 처음 위치로 돌아가기
}

// 회원정보 조회 
const getUserInfo = async () : Promise<AxiosResponse> => {
 return await localAxios().get('/user');
}

  export {login, getRefresh, signOut, withDraw, getUserInfo, isLogin}