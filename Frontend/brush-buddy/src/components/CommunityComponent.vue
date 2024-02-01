<template>
  <div>
    <h1>{{ boardThumbnail.boardTitle }}</h1>
    <img :src="boardThumbnail.thumbnail" alt="Thumbnail" />
    <p>Likes: {{ boardThumbnail.likeNumber }}</p>
    <p>Views: {{ boardThumbnail.views }}</p>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';
import { BoardThumbnail } from '../api/types';

const props = defineProps<{
  boardThumbnail: BoardThumbnail;
}>();
</script>
