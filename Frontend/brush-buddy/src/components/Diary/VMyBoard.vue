<template>
    <div>
      <v-infinite-scroll :items="items" :onLoad="load">
        <div v-for="(item, index) in items" :key="index">
          <CCard :board="item" />
        </div>
      </v-infinite-scroll>
    </div>
    <div id="navarea"></div>
  </template>
  <script setup lang="ts">
  import CCard from "./CCard.vue";
  import { ref } from "vue";
  import {localAxios} from "../../api/axios";
  
  // madeDraft 불러오기
  interface HeartListRes {
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
  interface HeartList {
    boardId: number;
      boardTitle: string;
      createdAt: string;
      likeNumber: number;
      thumbnail: string;
      views: number;
  }
  
  const listNum = ref(3);
  const pageNum = ref(1);
  const firstCall = ref([
      localAxios().get('/mypage/myboard/list?listNum=3&pageNum=1')
      .then(function (response: any) {
        console.log(response.data);
        items.value = response.data.boards;
      })
      .catch(function (error: any) {
        console.log(error.message);
      }),
  ]);
  const totalPage = ref(0);
  const getHeartList = async (page: number): Promise<HeartListRes> => {
    try {
      const heartListGet = await localAxios().get(`/mypage/myboard/list?listNum=${listNum.value}&pageNum=${page}`);
      return heartListGet.data.boards;
    } catch (err: any) {
      console.log("api 호출 중 오류 발생", err);
      return Promise.reject(err);
    }
  };
  
  // 무한 스크롤 구현
  const items = ref<HeartList[]>([]);
  
  const api = async () => {
    pageNum.value = pageNum.value + 1;
    return new Promise<number>((resolve) => {
      setTimeout(() => {
        resolve(pageNum.value);
      }, 1000);
    });
  };
  
  type InfiniteScrollSide = any;
  type InfiniteScrollStatus = any;
  const load = async (options: {
    side: InfiniteScrollSide;
    done: (status: InfiniteScrollStatus) => void;
  }): Promise<void> => {
    try {
      // Perform API call
      const res = await api();
      const resList = await getHeartList(res);
      if (resList && Array.isArray(resList) && resList.length > 0) {
        resList.forEach((res: HeartList) => items.value.push(res));
      }
      if (totalPage.value > pageNum.value) {
        options.done("ok");
      } else {
        options.done("empty");
      }
    } catch (error) {
      console.error("Error during load:", error);
      options.done("error");
    }
  };
  </script>
  
  <style scoped>
  #navarea {
    height: 10vh;
    width: 100%;
  }</style>
  