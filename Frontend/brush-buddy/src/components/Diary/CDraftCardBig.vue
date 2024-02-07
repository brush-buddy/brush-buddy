<template>
  <div id="cardWrapper">
    <div id="thumbnailBox">
      <div id="thumbnail">
        <!-- <img src="{{ props.draft.draftThumbnail }}" alt="thumbnail" /> -->
      </div>
    </div>
    <div id="info">
      <div id="timestamp">
        {{ draft.draftTimestamp }}
      </div>
      <div id="inner">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <title>download-circle-outline</title>
          <path
            d="M8 17V15H16V17H8M16 10L12 14L8 10H10.5V6H13.5V10H16M12 2C17.5 2 22 6.5 22 12C22 17.5 17.5 22 12 22C6.5 22 2 17.5 2 12C2 6.5 6.5 2 12 2M12 4C7.58 4 4 7.58 4 12C4 16.42 7.58 20 12 20C16.42 20 20 16.42 20 12C20 7.58 16.42 4 12 4Z"
          />
        </svg>
        <div>
          {{ draft.draftDownload }}
        </div>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <title>download-circle-outline</title>
          <path
            d="M8 17V15H16V17H8M16 10L12 14L8 10H10.5V6H13.5V10H16M12 2C17.5 2 22 6.5 22 12C22 17.5 17.5 22 12 22C6.5 22 2 17.5 2 12C2 6.5 6.5 2 12 2M12 4C7.58 4 4 7.58 4 12C4 16.42 7.58 20 12 20C16.42 20 20 16.42 20 12C20 7.58 16.42 4 12 4Z"
          />
        </svg>
        <div>
          {{ draft.draftBookmark }}
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
// import { defineProps } from 'vue';
const props = defineProps(["draft"]);
console.log("incard", props);
</script>
<style scoped>
#cardWrapper {
  border-style: solid;
  border-width: 0 0 1px;
  border-color: gray;
  padding: 0.5rem 0 1rem;
}
#thumbnailBox {
  display: flex;
  justify-content: center;
  align-items: center;
}
#thumbnail {
  background-color: #ffffff;
  width: 90vw;
  padding: 1rem;
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
  margin-left: 1vh;
}
#info {
  display: flex;
  justify-content: space-between;
}
#inner {
  display: flex;
}
</style>
