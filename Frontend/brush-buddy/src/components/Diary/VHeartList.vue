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
import axios from "axios";
import type { HeartList, HeartListRes } from "../../api/type.ts";
// import { InfiniteScrollStatus } from 'vuetify';

const listNum = ref(3);
const pageNum = ref(1);
// const firstCall = ref([]);
const firstCall = ref([
  axios({
    baseURL: "",
    method: "get",
    url: "http://localhost:8080/api/v1/mypage/heart/list?listNum=3&pageNum=1",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
  })
    .then(function (response: any) {
      console.log("first call ", response.data.boards);
      items.value = response.data.boards;
      console.log("first call value", items.value);
      // boardThumbnailData.value = response.data;
    })
    .catch(function (error: any) {
      console.log(error.message);
    }),
]);
const totalPage = ref(0);
const getHeartList = async (page: number): Promise<HeartListRes> => {
  console.log("getHeartList called");
  try {
    const heartListGet = await axios({
      method: "get",
      url: `http://localhost:8080/api/v1/mypage/heart/list?listNum=${listNum.value}&pageNum=${page}`,
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        // Authorization: localStorage.getItem("token")
      },
    });
    totalPage.value = heartListGet.data.totalPage;
    return heartListGet.data.boards;
  } catch (err: any) {
    console.log("api 호출 중 오류 발생", err);
    return Promise.reject(err);
  }
};

// 무한 스크롤 구현
const items = ref<HeartList[]>([]);

// pageNum update
const api = async () => {
  pageNum.value = pageNum.value + 1;
  return new Promise<number>((resolve) => {
    setTimeout(() => {
      resolve(pageNum.value);
    }, 1000);
  });
};


type InfiniteScrollSide =  any
type InfiniteScrollStatus =  any
const load = async (options: {
  side: InfiniteScrollSide;
  done: (status: InfiniteScrollStatus) => void;
}): Promise<void> => {
  try {
    // Perform API call
    const res = await api();
    const resList = await getHeartList(res);
    console.log("resList", resList);
    if (resList && Array.isArray(resList) && resList.length > 0) {
      resList.forEach((res: HeartList) => items.value.push(res));
    }

    console.log("items.value", items.value);
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
}
</style>
