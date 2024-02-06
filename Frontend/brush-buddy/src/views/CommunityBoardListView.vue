<template>
  <div style="display: flex; justify-content: space-around;">
    <div>
    <div v-for="(card, i) in boardThumbnailDataFirst" :key="i">
        <CommunityComponent :boardThumbnail="card"/>
    </div>
    </div>
    <div>
    <div v-for="(card, i) in boardThumbnailDataSecond" :key="i">
        <CommunityComponent :boardThumbnail="card"/>
    </div>
  </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted} from 'vue';
import axios from "axios";
import CommunityComponent from '../components/CommunityComponent.vue';
import type{ BoardThumbnail } from '../api/type.ts';
// import { useUserStore } from '../stores/user';
// const userStore = useUserStore();
import { localAxios } from '../api/axios';

const boardThumbnailDataFirst = ref<BoardThumbnail[]>([{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/100/200',
  likeNumber: 100,
  views: 500
},{
  boardId: '2',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/300',
  likeNumber: 100,
  views: 300
},{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/400/200',
  likeNumber: 100,
  views: 500
},{
  boardId: '2',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/300',
  likeNumber: 100,
  views: 300
}]);

const boardThumbnailDataSecond = ref<BoardThumbnail[]>([{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/300/100',
  likeNumber: 100,
  views: 500
},{
  boardId: '2',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/300/300',
  likeNumber: 100,
  views: 300
},{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/100',
  likeNumber: 300,
  views: 500
},{
  boardId: '2',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/400',
  likeNumber: 100,
  views: 300
}]);

const num = 1;


onMounted(() => {
  localAxios().get('/board/list')
  .then(function (response : any) {
    console.log(response.data)
  })
})


</script>
