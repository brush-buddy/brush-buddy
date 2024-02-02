<template>
  <div>
    <div>
      <CProfile />
    </div>
    <div class="sticky">
      <div id="tab">
        <router-link to="">
            <div class="tabButton">만든 도안</div>
        </router-link>
        <router-link to="">
            <div class="tabButton">구매 도안</div>
        </router-link>
        <router-link to="">
            <div class="tabButton">북마크 도안</div>
        </router-link>
        <router-link to="">
            <div class="tabButton" @click="getHeartList(0)">찜한 글</div>
        </router-link>
      </div>
    </div>
    <v-infinite-scroll :height="300" :items="items" :onLoad="load">
      <template v-for="(item, index) in items" :key="item">
          <CCard :board="item"/>
      </template>
    </v-infinite-scroll>
  </div>
</template>

<script setup lang="ts">
import CProfile from "../components/Diary/CProfile.vue";
import CCard from "../components/Diary/CCard.vue";
import axios from "axios";
import { RouterLink } from 'vue-router'
import { ref } from "vue";

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

const listNum = ref(3);
const pageNum = ref(0);
const heartList = ref([]);
const totalPage = ref(0);
const getHeartList = async (page: number): Promise<HeartListRes> => {
  console.log("getHeartList called");
  try {
    
    const heartListGet = await axios({
      method: "get",
      url: `http://localhost:8080/v1/api/mypage/heart/list?listNum=${listNum.value}&pageNum=${page}`,
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        // Authorization: localStorage.getItem("token")
      },
    });
    heartList.value = heartListGet.data.boards;
    totalPage.value = heartListGet.data.totalPage;
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

<style scoped>
.sticky {
  position: sticky;
  top: 0px;
  line-height: 4rem;
  background-color: #ffffff;
}
#tab {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
</style>
