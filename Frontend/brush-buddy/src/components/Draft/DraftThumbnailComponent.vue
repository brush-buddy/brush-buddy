<template>
  <div class="communityComponent" @click="goToDetail">
    <v-img
      class="mx-auto w-100 elevation-2"
      lazy-src="../assets/logo.png"
      cover
      rounded="lg"
      :src="props.draftThumbnail.draftThumbnail"
    >
      <template v-slot:placeholder>
        <div class="d-flex align-center justify-center fill-height">
          <v-progress-circular color="grey-lighten-4" indeterminate></v-progress-circular>
        </div>
      </template>
    </v-img>

    <div
      style="
        display: flex;
        justify-content: flex-end;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        align-items: center;
      "
    >
      <v-icon icon="mdi-download-outline" size="small"></v-icon>
      <p class="thumbnailUnder">{{ draftThumbnail.draftDownload }}</p>
      <v-icon icon="mdi-bookmark-outline" size="small"></v-icon>
      <p class="thumbnailUnder">{{ draftThumbnail.draftBookmark }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { DraftThumbnail } from '../../api/draft'
import { useRouter } from 'vue-router'
const router = useRouter()

const props = defineProps<{
  draftThumbnail: DraftThumbnail
}>()

async function goToDetail() {
  router.push({
    name: 'draftDetail',
    params: { id: props.draftThumbnail.draftId }
  })
}

// console.log(props.DraftThumbnail.boardId);
</script>

<style scoped>
.thumbnailUnder {
  font-size: 0.8rem;
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
