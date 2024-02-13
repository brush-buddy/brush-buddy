<template>
  <div class="rect">
    <div id="category"></div>
    <div id="draft">
      <DraftDetailComponent />
    </div>
    <div id="palette"></div>
  </div>
</template>

<script setup lang="ts">
import DraftDetailComponent from '../DraftDetail/DraftDetailComponent.vue'
import { ref } from 'vue'
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
#palette {
  position: relative;
  margin-bottom: 0.5rem;
  z-index: 2;
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
