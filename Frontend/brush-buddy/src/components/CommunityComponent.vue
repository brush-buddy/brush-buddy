<template>
  <div class="communityComponent" @click="goToDetail">
    <v-img
      class="mx-auto w-100"
      lazy-src="../assets/logo.png"
      cover
      :src="boardThumbnail.thumbnail"
    >
      <template v-slot:placeholder>
        <div class="d-flex align-center justify-center fill-height">
          <v-progress-circular color="grey-lighten-4" indeterminate></v-progress-circular>
        </div>
      </template>
    </v-img>
    <!-- <v-img class="communityThumbnail" :src="boardThumbnail.thumbnail" alt="Thumbnail" /> -->
    <div
      style="display: flex; justify-content: flex-end; margin-top: 0.5rem; margin-bottom: 0.5rem"
    >
      <svg xmlns="http://www.w3.org/2000/svg" width="10" height="9" viewBox="0 0 10 9" fill="none">
        <path
          d="M9.24719 3.06994C9.24719 3.72816 8.99483 4.36038 8.54416 4.82806C7.50677 5.90494 6.50059 7.02786 5.42444 8.06571C5.17777 8.30014 4.78648 8.29159 4.55043 8.04656L1.45004 4.82806C0.51291 3.85521 0.51291 2.28466 1.45004 1.31183C2.39638 0.329435 3.93807 0.329435 4.8844 1.31183L4.99711 1.42881L5.10973 1.3119C5.56346 0.840632 6.18141 0.574829 6.82694 0.574829C7.47248 0.574829 8.09038 0.840607 8.54416 1.31183C8.99487 1.77954 9.24719 2.41173 9.24719 3.06994Z"
          stroke="black"
          stroke-width="0.7"
          stroke-linejoin="round"
        />
      </svg>
      <p class="thumbnailUnder">{{ boardThumbnail.likeNumber }}</p>
      <svg xmlns="http://www.w3 .org/2000/svg" width="14" height="9" viewBox="0 0 14 9" fill="none">
        <path
          d="M1.23334 4.66373C3.5 -0.254608 10.3 -0.254608 12.5667 4.66373"
          stroke="black"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
        <path
          d="M6.90003 7.31206C6.06544 7.31206 5.38892 6.63454 5.38892 5.79873C5.38892 4.96292 6.06544 4.2854 6.90003 4.2854C7.73462 4.2854 8.41114 4.96292 8.41114 5.79873C8.41114 6.63454 7.73462 7.31206 6.90003 7.31206Z"
          stroke="black"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
      </svg>
      <p class="thumbnailUnder">{{ boardThumbnail.views }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { BoardThumbnail } from '../api/type'
import { useRouter } from 'vue-router'
const router = useRouter()

const props = defineProps<{
  boardThumbnail: BoardThumbnail
}>()
console.log(props.boardThumbnail)
console.log('`````````````````')
async function goToDetail() {
  router.push({
    name: 'boardDetail',
    params: { id: props.boardThumbnail.boardId }
  })
}

// console.log(props.boardThumbnail.boardId);
</script>

<style scoped>
.thumbnailUnder {
  font-size: 0.5rem;
  margin-left: 0.5rem;
  margin-right: 0.5rem;
  margin-top: 0;
  margin-bottom: 0;
}
.communityComponent {
  width: 10rem;
}
.communityComponent img {
  width: 100%;
  border-radius: 10%;
  box-shadow: 0.1rem 0.1rem 0.1rem 0.1rem #c1c1c1;
}
</style>
