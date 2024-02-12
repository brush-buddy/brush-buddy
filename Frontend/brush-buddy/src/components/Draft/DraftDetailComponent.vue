<template>
  <div class="rect">
    <div>
      <v-item-group v-model="selection" multiple>
        <v-item>
          <img :src="imageThumbnail" style="width: 20rem" alt="" />
          <v-btn
            :icon="isBookmarked ? 'mdi-bookmark-multiple' : 'mdi-bookmark-multiple-outline'"
            @click="handleClick"
          ></v-btn>
        </v-item>
      </v-item-group>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useBookmarksStore } from '../../stores/bookmark'
import DraftThumbnailComponent from './DraftThumbnailComponent.vue'

const props = defineProps<{
  imageThumbnail: string
}>()

const bookmarksStore = useBookmarksStore()
const bookmarks = bookmarksStore.bookmarks
const addBookmark = (id) => bookmarksStore.addBookmark(id)
const removeBookmark = (id) => bookmarksStore.removeBookmark(id)

const isBookmarked = ref(true)

// 북마크 추가 삭제
const handleClick = () => {
  const draftId = props.draftId
  if (isBookmarked.value) {
    removeBookmark(draftId)
  } else {
    addBookmark(draftId)
  }
  isBookmarked.value = !isBookmarked.value
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
