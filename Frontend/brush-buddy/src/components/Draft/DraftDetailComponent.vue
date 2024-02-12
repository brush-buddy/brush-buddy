<template>
  <div class="rect">
    <div>
      <v-item-group v-model="selection" multiple>
        <v-item>
          <DraftThumbnailComponent @click="handleClick" />
          <v-btn
            :icon="isBookmarked ? 'mdi-bookmark-multiple' : 'mdi-bookmark-multiple-outline'"
          ></v-btn>
        </v-item>
      </v-item-group>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useBookmarksStore } from '../../stores/bookmark'
import { addBookmark, removeBookmark } from '../../api/draft'
import DraftThumbnailComponent from '../Draft/DraftThumbnailComponent.vue'

const props = defineProps<{
  draftId: number
}>()

const bookmarksStore = useBookmarksStore()
const isBookmarked = ref(bookmarksStore.getBookmarkState(props.draftId))

// 북마크 추가 삭제
const handleClick = () => {
  const draftId = props.draftId
  if (isBookmarked.value) {
    removeBookmark(draftId)
  } else {
    addBookmark(draftId)
  }
}
</script>

<style scoped>
#category {
  display: flex;
  flex-direction: row;
  justify-content: align-start;
  position: relative;
  left: 0.3rem;
  top: 0;
  z-index: 2;
  margin-bottom: 0.3rem;
}
#category1 {
  margin-right: 0.5rem;
}
#draft {
  position: relative;
  top: 0;
  margin-bottom: -2rem;
  z-index: 1;
}

.rect {
  position: relative;
  background: #ffffff;
  width: 18rem;
  /* height: 18rem; */
  border-radius: 2rem;
  margin: 0 auto;
}
</style>
../../stores/bookmark
