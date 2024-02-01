<template>
  <div style="display: flex; justify-content: space-around">
    <div style="width: 50%">
      <v-infinite-scroll :height="800" :items="boardThumbnailDataFirst" :onLoad="load">
        <template v-for="(card, i) in boardThumbnailDataFirst" :key="i">
          <CommunityComponent :boardThumbnail="card" />
        </template>
        <div style="height: 200px"></div>
      </v-infinite-scroll>
    </div>
    <div style="width: 50%">
      <v-infinite-scroll :height="800" :items="boardThumbnailDataSecond" :onLoad="load">
        <template v-for="(card, i) in boardThumbnailDataSecond" :key="i">
          <CommunityComponent :boardThumbnail="card" />
        </template>
        <div style="height: 200px"></div>
      </v-infinite-scroll>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import axios from "axios";
import InfiniteLoading from "infinite-loading-vue3-ts";
import CommunityComponent from "../components/CommunityComponent.vue";
import type { BoardThumbnail } from "../api/type.ts";

const boardThumbnailDataFirst = ref<BoardThumbnail[]>([]);
const boardThumbnailDataSecond = ref<BoardThumbnail[]>([]);

// 비동기 API 함수
async function api(pageNum: number, listNum: number = 5): Promise<BoardThumbnail[]> {
  try {
    const response = await axios({
      baseURL: "",
      method: "get",
      url: `http://localhost:8080/v1/api/board/list?listNum=${listNum}&pageNum=${pageNum}`,
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
    });
    return response.data.boards;
  } catch (error) {
    console.error("API 호출 중 오류 발생:", error);
    return [];
  }
}

// 무한 스크롤 로드 함수
async function load({ done }: { done: (status: string) => void }) {
  // const pageNum = Math.ceil(boardThumbnailDataFirst.value.length / 5) + 1;
  const pageNum =
    Math.ceil((boardThumbnailDataFirst.value.length + boardThumbnailDataSecond.value.length) / 5) +
    1;
  const res = await api(pageNum);

  res.forEach((board, i) => {
    if (i % 2 === 0) {
      boardThumbnailDataFirst.value.push(board);
    } else {
      boardThumbnailDataSecond.value.push(board);
    }
  });

  done("ok");
}

onMounted(async () => {
  await load({ done: () => {} });
});
</script>
