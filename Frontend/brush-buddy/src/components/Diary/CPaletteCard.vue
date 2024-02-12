<template>
  <RouterLink :to="{ name: 'draftDetail', params: { id: palette.paletteId } }">
    <div id="cardWrapper">
    {{ palette }}
    <!-- <div v-for=""> -->
      <!-- <SinglePaletteComponent :color="value" /> -->
    <!-- </div> -->
    </div>
  </RouterLink>
</template>
<script setup lang="ts">
import { RouterLink } from 'vue-router';
import SinglePaletteComponent from '../common/SinglePaletteComponent.vue';
const prop = defineProps(["palette"]);
console.log("prop ",prop.palette.paletteColorCode);
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
