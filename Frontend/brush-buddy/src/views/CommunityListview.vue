<template>
  <div>
    <CommunityComponent :board-thumbnail="boardThumbnailData[1]" />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import CommunityComponent from '../components/CommunityComponent.vue';
import { BoardThumbnail } from '../api/types';

const boardThumbnailData = ref<BoardThumbnail[]>([{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/300',
  likeNumber: 100,
  views: 500
},{
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'https://picsum.photos/200/300',
  likeNumber: 100,
  views: 300
}]);
</script>
