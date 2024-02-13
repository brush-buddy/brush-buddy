<template>
  <div class="rect">
    <v-item-group v-model="selection" multiple>
      <v-item>
        <div class="thumb">
          <img :src="imageThumbnail" style="width: 90vw" alt="" />
        </div>
        <div class="icon">
          <v-btn
            :icon="isBookmarked ? 'mdi-bookmark-multiple' : 'mdi-bookmark-multiple-outline'"
            @click="handleClick"
          ></v-btn>
        </div>
        <div class="fixed_width">
          <div v-for="(color, index) in paletteColors" :key="index" class="palette">
            <SinglePaletteComponent :color="color" />
          </div>
        </div>
        <v-for key="">
          <v-item-title>{{ props.draftColorCode }}</v-item-title>
        </v-for>
      </v-item>
    </v-item-group>
  </div>
</template>

<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
import { addBookmark, removeBookmark } from '../../api/draft'
import { useBookmarksStore } from '../../stores/bookmark'
import DraftThumbnailComponent from '../Draft/DraftThumbnailComponent.vue'
const { isBookmarked } = storeToRefs(useBookmarksStore())

const props = defineProps<{
  draftId: number
  draftColorCode: string
  imageThumbnail: string
}>()

const bookmarksStore = useBookmarksStore()

// 북마크 추가 삭제
const handleClick = () => {
  if (isBookmarked) {
    removeBookmark(props.draftId)
    isBookmarked.value = !isBookmarked.value
  } else {
    addBookmark(props.draftId)
    isBookmarked.value = !isBookmarked.value
  }
}
</script>

<style scoped>
.thumb {
  position: relative;
}

.icon {
  position: absolute;
  right: 0;
  top: 0;
  margin: 0.5rem 0.5rem 0 0;
  z-index: 3;
}

.rect {
  position: relative;
  background: #ffffff;
  width: 90vw;
  border-radius: 10rem;
}
</style>
../../stores/bookmark
