<template>
  <!-- <div style="height: 1rem;"></div> -->
  <div style="display: flex; justify-content: space-around">
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
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
// import { useUserStore } from '../stores/user';
// const userStore = useUserStore();
import { getBoardList } from '../api/board';
import InfiniteLoading from "infinite-loading-vue3-ts";
import CommunityComponent from "../components/CommunityComponent.vue";
import type { BoardThumbnail } from "../api/type.ts";
import type { BoardSearchParam, BoardListElement } from "../api/board"

const boardThumbnailDataFirst = ref<BoardThumbnail[]>([]);
const boardThumbnailDataSecond = ref<BoardThumbnail[]>([]);

// 무한 스크롤 로드 함수
async function load({ done }: { done: (status: string) => void }) {
  // const pageNum = Math.ceil(boardThumbnailDataFirst.value.length / 5) + 1;
  const pageNum =
    Math.ceil((boardThumbnailDataFirst.value.length + boardThumbnailDataSecond.value.length) / 5) +
    1;

    //검색 조건 
    const params : BoardSearchParam ={
      pageNum : pageNum,
      listNum : 5
    }
    const { data } = await getBoardList(params) // 게시판 리스트 조회
    const res: BoardListElement[] = data.boards; // 결과로 부터 게시판 데이터 추출

  res.forEach((board : BoardListElement,  i : number) => {
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

<style scoped>
.column {
  display: flex;
  width: 50%;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
