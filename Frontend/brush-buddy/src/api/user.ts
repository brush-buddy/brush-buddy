import type { AxiosResponse } from "axios";
import { useUserStore } from "../stores/user";
import { instance } from "./axios";

function postRefresh() : Promise<AxiosResponse>{
    return instance.get('/auth/refresh');
  }
  
async function signOut() {
    const userStore = useUserStore();
    userStore.setAccessToken(''); // accessToken 날리기
    await instance.post("/auth/logout");
    window.location.href = '/';
  }

  export {postRefresh, signOut}