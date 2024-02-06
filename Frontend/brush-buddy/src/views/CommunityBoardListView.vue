<template>
  <CWriteBoard/>
  <!-- <div style="height: 1rem;"></div> -->
  <!-- <div style="display: flex; justify-content: space-around">
    <div>
    <div class = "columns" style="display: flex; flex-direction: column; justify-content: center;">
        <template v-for="(card, i) in boardThumbnailDataFirst" :key="i">
          <CommunityComponent :boardThumbnail="card" />
        </template>

    </div>
  </div>
  <div>
    <div  class = "columns" style="display: flex; flex-direction: column; justify-content: center;">
        <template v-for="(card, i) in boardThumbnailDataSecond" :key="i">
          <CommunityComponent :boardThumbnail="card" />
        </template>    
    </div>
  </div>  
  </div> -->
</template>

<script setup lang="ts">
import CWriteBoard from "../components/Community/CWriteBoard.vue";
// import { ref, onMounted } from "vue";
// import axios from "axios";
// import InfiniteLoading from "infinite-loading-vue3-ts";
// import CommunityComponent from "../components/CommunityComponent.vue";
// import type { BoardThumbnail } from "../api/type.ts";

// const boardThumbnailDataFirst = ref<BoardThumbnail[]>([]);
// const boardThumbnailDataSecond = ref<BoardThumbnail[]>([]);

// // 비동기 API 함수
// async function api(pageNum: number, listNum: number = 5): Promise<BoardThumbnail[]> {
//   try {
//     const response = await axios({
//       baseURL: "",
//       method: "get",
//       url: `http://localhost:8080/v1/api/board/list?listNum=${listNum}&pageNum=${pageNum}`,
//       headers: {
//         "Content-Type": "application/json; charset=utf-8",
//       },
//     });
//     return response.data.boards;
//   } catch (error) {
//     console.error("API 호출 중 오류 발생:", error);
//     return [];
//   }
// }

// // 무한 스크롤 로드 함수
// async function load({ done }: { done: (status: string) => void }) {
//   // const pageNum = Math.ceil(boardThumbnailDataFirst.value.length / 5) + 1;
//   const pageNum =
//     Math.ceil((boardThumbnailDataFirst.value.length + boardThumbnailDataSecond.value.length) / 5) +
//     1;
//   const res = await api(pageNum);

//   res.forEach((board, i) => {
//     if (i % 2 === 0) {
//       boardThumbnailDataFirst.value.push(board);
//     } else {
//       boardThumbnailDataSecond.value.push(board);
//     }
//   });

//   done("ok");
// }

// onMounted(async () => {
//   await load({ done: () => {} });
// });
</script>

<style scoped>
.column {
  display: flex;
  width: 50%;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
