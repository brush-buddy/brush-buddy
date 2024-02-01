<template>
  <div>
    <CommunityComponent :board-thumbnail="boardThumbnailData" />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import CommunityComponent from '../components/CommunityComponent.vue';
import { BoardThumbnail } from '../api/types';

const boardThumbnailData = ref<BoardThumbnail>({
  boardId: '1',
  boardTitle: 'Example Board',
  thumbnail: 'image-url.jpg',
  likeNumber: 100,
  views: 500
});
</script>
