<template>
  <div style="display: flex; justify-content: space-around">
    <div>
      <div v-for="(card, i) in boardThumbnailDataFirst" :key="i">
        <CommunityComponent :boardThumbnail="card" />
      </div>
    </div>
    <div>
      <div v-for="(card, i) in boardThumbnailDataSecond" :key="i">
        <CommunityComponent :boardThumbnail="card" />
      </div>

      <div id="communityWrite">
        <v-btn
          icon="mdi-pencil-outline"
          color="purple-lighten-3"
          size="x-large"
          onclick="goWrite()"
        ></v-btn>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import CommunityComponent from '@/components/Community/CommunityComponent.vue'
import type { BoardThumbnail } from '../api/type.ts'

const router = useRouter()

const boardThumbnailDataFirst = ref<BoardThumbnail[]>([
  {
    boardId: '1',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/100/200',
    likeNumber: 100,
    views: 500
  },
  {
    boardId: '2',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/200/300',
    likeNumber: 100,
    views: 300
  },
  {
    boardId: '1',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/400/200',
    likeNumber: 100,
    views: 500
  },
  {
    boardId: '2',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/200/300',
    likeNumber: 100,
    views: 300
  }
])

const boardThumbnailDataSecond = ref<BoardThumbnail[]>([
  {
    boardId: '1',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/300/100',
    likeNumber: 100,
    views: 500
  },
  {
    boardId: '2',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/300/300',
    likeNumber: 100,
    views: 300
  },
  {
    boardId: '1',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/200/100',
    likeNumber: 300,
    views: 500
  },
  {
    boardId: '2',
    boardTitle: 'Example Board',
    thumbnail: 'https://picsum.photos/200/400',
    likeNumber: 100,
    views: 300
  }
])

const num = 1

onMounted(() => {
  axios({
    baseURL: '',
    method: 'get',
    url: 'http://localhost:8080/v1/api/board/list', // URL에 한글이 포함될 경우 인코딩
    headers: {
      'Content-Type': 'application/json; charset=utf-8'
    }
  }).then(function (response: any) {
    console.log(response.data)

    // boardThumbnailData.value = response.data;
  })

  const goWrite = () => {
    router.push('/community/write')
  }
})
</script>

<style scoped>
#communityWrite {
  position: fixed;
  bottom: 7rem;
  right: 2rem;
}
</style>
