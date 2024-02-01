<template>
  <div style = "width: 10rem; height: 13rem;">
  <CommunityComponent :boardThumbnail="boardThumbnailData[0]"></CommunityComponent>
  <CommunityComponent :boardThumbnail="boardThumbnailData[1]"></CommunityComponent>
</div>


</template>

<script setup lang="ts">
import { ref } from 'vue';
import CommunityComponent from '../components/CommunityComponent.vue';
import { BoardThumbnail } from '../api/types';

const boardThumbnailData = ref<BoardThumbnail[]>([{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/300/200',
  likeNumber: 100,
  views: 500
},{
  boardId: '2',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/300',
  likeNumber: 100,
  views: 300
}]);
</script>
