<template>
    <div id="cardWrapper">
        <div id="thumbnailBox">
            <div id="thumbnail">
                <!-- <img src="{{ draft.draftThumbnail }}" alt="thumbnail" /> -->
            </div>
        </div>
        <div id="timestamp">
            {{ draft.draftTimestamp.substring(0, 10)}} {{ draft.draftTimestamp.substring(11, 19) }}
        </div>
    </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue';
defineProps(["draft"]);
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
</style>
