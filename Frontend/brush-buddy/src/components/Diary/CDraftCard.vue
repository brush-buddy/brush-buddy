<template>
    <div id="cardWrapper">
      <router-link :to="{ name: 'draftDetail', params: { id: draft.draftId } }">
        <div id="thumbnailBox">
            <div id="thumbnail">
                <img :src="draft.draftThumbnail">
            </div>
        </div>
        <div id="timestamp">
            {{ draft.draftTimestamp.substring(0, 10)}} {{ draft.draftTimestamp.substring(11, 19) }}
        </div>
      </router-link>
    </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';
const prop = defineProps(["draft"]);
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
  margin-left: 5vw;
  margin-bottom: 1vh;
}
a {
  text-decoration: none;
  color: black;
}
</style>
