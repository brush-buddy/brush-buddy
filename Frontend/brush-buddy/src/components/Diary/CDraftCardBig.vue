<template>
  <div id="cardWrapper">
    <router-link :to="{ name: 'draftDetail', params: { id: draft.draftId } }">
      <div id="thumbnailBox">
        <div id="thumbnail">
          <img :src="draft.draftThumbnail" alt="" />
        </div>
      </div>
      <div id="info">
        <div id="timestamp">
          {{ draft.draftTimestamp.substring(0, 10) }} {{ draft.draftTimestamp.substring(11, 19) }}
        </div>
        <div id="inner">
          <v-icon icon="mdi-download-outline" size="small"></v-icon>
          {{ draft.draftDownload }}&nbsp;
          <v-icon icon="mdi-bookmark-outline" size="small"></v-icon>
          {{ draft.draftBookmark }}
        </div>
      </div>
    </router-link>
  </div>
</template>
<script setup lang="ts">
import { RouterLink } from 'vue-router';
const props = defineProps(['draft'])
console.log('incard', props)
</script>
<style scoped>
#cardWrapper {
  border-style: solid;
  border-width: 0 0 1px;
  border-color: gray;
}
#thumbnailBox {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1vh;
}
#thumbnail {
  background-color: #ffffff;
  width: 90vw;
  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}
#timestamp {
  font-size: small;
  color: gray;
}
#info {
  display: flex;
  justify-content: space-between;
  margin: 1vh 5vw 1vh;
}
#inner {
  display: flex;
}
a {
  text-decoration: none;
  color: black;
}
</style>
