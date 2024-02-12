<template>
  <div id="cardWrapper">
    <RouterLink :to="{ name: 'draftDetail', params: { id: draft.draftId } }">
      <div id="thumbnailBox">
        <div id="thumbnail">
          <img :src="draft.draftThumbnail" alt="thumbnail" />
        </div>
      </div>
      <div id="info">
        <div id="timestamp">
          {{ draft.draftTimestamp.substring(0, 10) }} {{ draft.draftTimestamp.substring(11, 19) }}
        </div>
        <div id="inner">
          <v-icon icon="mdi-download-outline" size="small"></v-icon>
          {{ draft.draftDownload }}&nbsp;&nbsp;
          <v-icon icon="mdi-bookmark-outline" size="small"></v-icon>
          {{ draft.draftBookmark }}
        </div>
      </div>
    </RouterLink>
  </div>
</template>
<script setup lang="ts">
// import { defineProps } from 'vue';
const props = defineProps(['draft'])
console.log('incard', props)
</script>
<style scoped>
a {
  text-decoration: none;
  color: black;
}
#cardWrapper {
  border-style: solid;
  border-width: 0 0 1px;
  border-color: gray;
  padding: 1vh 0 1vh;
}
#thumbnailBox {
  display: flex;
  justify-content: center;
  align-items: center;
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
  margin-left: 5vw;
}
#info {
  display: flex;
  justify-content: space-between;
  margin-right: 5vw;
}
#inner {
  display: flex;
  align-items: center;
}
</style>
