<template>
    <v-infinite-scroll :height="300" :items="items" @load="load">
    <div v-for="(item, index) in items" :key="item">
        <CCard :board="item"/>
    </div>
    </v-infinite-scroll>
</template>
<script setup lang="ts">
import CCard from "./CCard.vue";
import { ref, onMounted, onBeforeMount } from "vue";
import axios from "axios";


// HeartList 불러오기
interface HeartListRes {
  boards: {
    boardId: number;
    boardTitle: string;
    createdAt: string;
    likeNumber: number;
    thumbnail: string;
    views: number;
  };
  pageNum: number;
  length: number;
  totalPage: number;
}


// onMounted
// onBeforeMount(() => {
//     getHeartList(pageNum.value);
// });

const listNum = ref(3);
const pageNum = ref(0);
const heartList = ref([]);
const firstCall = ref([
      axios({
    baseURL: '',
    method: 'get',
    url: 'http://localhost:8080/api/v1/mypage/heart/list?listNum=3&pageNum=1', // URL에 한글이 포함될 경우 인코딩
    headers: {
      'Content-Type': 'application/json; charset=utf-8'
    }
  }).then(function (response : any) {
    console.log("최초호출");
    console.log(response.data)
    heartList.value = response.data.boards;
    // boardThumbnailData.value = response.data;
  })
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
    heartList.value = heartListGet.data.boards;
    totalPage.value = heartListGet.data.totalPage;
    // pageNum.value = pageNum.value + 1;
    return heartListGet.data.boards;
  } catch (err) {
    console.log("api 호출 중 오류 발생", err);
    return Promise.reject(err);
  }
};

// 무한 스크롤 구현 
const items = ref<number[]>([])

const api = async () => {
    pageNum.value = pageNum.value + 1;
    console.log("pageNum ", pageNum.value);
    console.log("listNum ", listNum.value);
  return new Promise<number>(resolve => {
    setTimeout(() => {
      resolve(pageNum.value);
    }, 1000)
  })
}

const load = async ({ done }: { done: (status: string) => void }) => {
    try{
            // Perform API call => pageNum update
            const res = await api()
            const resList = await getHeartList(res)
            items.value.push(...resList)
            console.log("items: ", items.value);

        if(totalPage.value > pageNum.value){
            done('ok')
        }else{
            done('empty')
        }
    } catch(error){
        console.error("Error during load:", error);
        done('error')
    }

}
</script>
<style scoped></style>