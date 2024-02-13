<template>
  <v-item-group multiple>
    <v-item>
      <div class="rect">
        <div class="thumb">
          <img :src="imageThumbnail" class="elevation-5" />
        </div>
        <div class="icon">
          <v-icon
            v-if="isBookmarked"
            icon="mdi-bookmark-multiple"
            @click="removeBookmark(props.draftId)"
            size="x-large"
            color="white"
          ></v-icon>
          <v-icon
            v-if="!isBookmarked"
            icon="mdi-bookmark-multiple-outline"
            @click="addBookmark(props.draftId)"
            size="x-large"
            color="white"
          ></v-icon>
        </div>
        <div class="color">
          <PaletteComponent :draft-id="props.draftId" :draft-color-code="props.draftColorCode" />
        </div>
      </div>
    </v-item>
  </v-item-group>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, ref } from 'vue'
import { useBookmarksStore } from '../../stores/bookmark'
import { localAxios } from '../../api/axios'

import { storeToRefs } from 'pinia'
const { removeBookmark, addBookmark } = useBookmarksStore()
const BookmarkStore = useBookmarksStore()
const { isBookmarked } = storeToRefs(BookmarkStore)

import DraftThumbnailComponent from '../Draft/DraftThumbnailComponent.vue'
import PaletteComponent from '../DraftDetail/PaletteComponent.vue'

const props = defineProps<{
  draftId: number
  draftColorCode: string
  imageThumbnail: string
}>()

// 북마크 추가 삭제
// const handleClick = () => {
//   if (isBookmarked) {
//     removeBookmark(props.draftId)
//   } else {
//     addBookmark(props.draftId)
//   }
// }
</script>

<style scoped>
.rect {
  position: relative;
  background: #ffffff;
  width: 20rem;
  display: flex;
  justify-content: center;
  margin: 1rem 0 0 0;
  /* border-radius: 10rem; */
}

img {
  position: relative;
  width: 20rem;
  height: 100%;
  object-fit: cover;
  z-index: 1;
  border-radius: 1rem;
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
  bottom: -2rem;
  margin-top: 1rem;
  padding: 0 1rem;
  width: 20rem;
  z-index: 2;
  flex-wrap: wrap;
}

.thumb {
  position: relative;
  display: flex;
  justify-content: center;
}
</style>
