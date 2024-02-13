<template>
  <div class="palette" :style="{ backgroundColor: color }"></div>
</template>

<script setup lang="ts">
const props = defineProps(['color'])
</script>

<style scoped>
.palette {
  width: 2.3rem;
  height: 2.3rem;
  border-radius: 50%;
  box-shadow: 0px 1px 1px 1px rgba(0, 0, 0, 0.25);
  border: 1.5px solid rgb(201, 201, 201);
  margin-right: -0.8rem;
}
</style>
