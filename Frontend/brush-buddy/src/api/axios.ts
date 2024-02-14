import axios, { type AxiosResponse } from 'axios';
import { useUserStore } from '../stores/user';
import { getRefresh, signOut } from './user';
const url = import.meta.env.VITE_APP_SERVER_URL

function localAxios(){
  const userStore = useUserStore();

  const instance = axios.create({
    baseURL: url,
    withCredentials: true,
    
  });
  instance.interceptors.request.use(
    function (config) {
      const accessToken = userStore.accessToken // access 토큰 가지고 온다.
      if (accessToken) { // 토큰이 있다면 
        config.headers['Authorization'] = accessToken; // 토큰 삽입
      }
      return config; 
    },
    function (error) { // 에러 핸들러
      // Do something with request error
      return Promise.reject(error);
    }
  )
    // Add a response interceptor
  instance.interceptors.response.use(
    function (response) {
      // Any status code that lie within the range of 2xx cause this function to trigger
      // Do something with response data
      return response;
    },
    async function (error) {
      // Any status codes that falls outside the range of 2xx cause this function to trigger
      // Do something with response error
      const { response: errorResponse } = error;
      
      const originalRequest = error.config;

      // 인증 에러 발생시
      if (errorResponse.status === 401) {
        return await resetTokenAndReattemptRequest(error); // 토큰 삭제 및 재인증
      }

      return Promise.reject(error);
    }
  );

  async function resetTokenAndReattemptRequest(error : any) {
    try {
      const { response: errorResponse } = error;

      // subscribers에 access token을 받은 이후 재요청할 함수 추가 (401로 실패했던)
      // retryOriginalRequest는 pending 상태로 있다가
      // access token을 받은 이후 onAccessTokenFetched가 호출될 때
      // access token을 넘겨 다시 axios로 요청하고
      // 결과값을 처음 요청했던 promise의 resolve로 settle시킨다.
      const retryOriginalRequest = new Promise((resolve, reject) => {
        userStore.addSubscriber(async (accessToken : string) => {
          try {
            errorResponse.config.headers['Authorization'] =
              'Bearer ' + accessToken;
            resolve(instance(errorResponse.config));
          } catch (err) {
            reject(err);
          }
        });
      });

      // refresh token을 이용해서 access token 요청
      
      if (!userStore.isRefreshing) {
        userStore.setRefresh(true); // 문닫기 (한 번만 요청)

        const { data } = await getRefresh();

        userStore.setAccessToken('Bearer '+data); // refresh token은 쿠키에 담긴다.
        // storeUserToken('refresh', data.refresh);

        userStore.setRefresh(false); // 문열기 (초기화)
        userStore.onAccessTokenFetched(data);
      }

      return retryOriginalRequest; // pending 됐다가 onAccessTokenFetched가 호출될 때 resolve
    } catch (error) {
      await signOut();
      return Promise.reject(error);
    }
  }
  return instance;
}

const instance = axios.create({
  baseURL: url,
  withCredentials: true,
});

export  { localAxios, instance };