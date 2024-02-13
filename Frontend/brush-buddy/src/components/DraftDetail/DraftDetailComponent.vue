<template>
  <v-item-group multiple>
    <v-item>
      <div class="rect">
        <div>
          <!-- <CButton :text="" /> -->
        </div>
        <div class="thumb">
          <img :src="imageThumbnail" />
        </div>
        <div class="icon">
          <v-btn
            :icon="isBookmarked ? 'mdi-bookmark-multiple' : 'mdi-bookmark-multiple-outline'"
            @click="handleClick"
          ></v-btn>
        </div>
        <div class="color">
          <PaletteComponent :draft-id="props.draftId" :draft-color-code="props.draftColorCode" />
        </div>
      </div>
    </v-item>
  </v-item-group>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
import { addBookmark, removeBookmark } from '../../api/draft'
import { useBookmarksStore } from '../../stores/bookmark'
const { isBookmarked } = storeToRefs(useBookmarksStore())
import DraftThumbnailComponent from '../Draft/DraftThumbnailComponent.vue'
import PaletteComponent from '../DraftDetail/PaletteComponent.vue'

const props = defineProps<{
  draftId: number
  draftColorCode: string
  imageThumbnail: string
}>()

// 북마크 추가 삭제
const handleClick = () => {
  if (isBookmarked) {
    removeBookmark(props.draftId)
  } else {
    addBookmark(props.draftId)
  }
}
</script>

<style scoped>
.rect {
  position: relative;
  background: #ffffff;
  width: 20rem;
  display: flex;
  justify-content: center;
  margin: 0;
  /* border-radius: 10rem; */
}

img {
  position: relative;
  width: 20rem;
  height: 100%;
  object-fit: cover;
  z-index: 1;
  border-radius: 3rem;
}

.icon {
  position: absolute;
  right: 2rem;
  top: 2rem;
  /* margin: 2rem 2rem 0 0; */
  z-index: 2;
}

.color {
  position: absolute;
  left: -1rem;
  bottom: 0;
  margin-top: 1rem;
  padding: 0 2rem 0 2rem;
  /* margin: 0 0  -1rem; */
  z-index: 2;
}

.thumb {
  position: relative;
  display: flex;
  justify-content: center;
}
</style>
