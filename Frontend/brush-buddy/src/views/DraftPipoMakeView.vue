<script setup lang="ts">
import SinglePaletteComponent from '../components/SinglePaletteComponent.vue'
const props = defineProps({
  pipoUrl: String,
  pipoPalette: {}
})
import { ref } from 'vue'
const shared = ref(true)
</script>

<template>
  <div style="display: flex; justify-content: center; flex-direction: column; align-items: center">
    <img :src="pipoUrl" alt="" style="width: 10rem" />
    <div style="height: 3rem; display: flex; align-items: center">
      <p>색 추출 결과</p>
    </div>
    <div class="paletteColors">
      <template v-for="(value, key) in pipoPalette" :key="key">
        <SinglePaletteComponent :color="value" />
      </template>
      

      
    </div>
    <div style="display: flex; flex-direction: row; align-items: center;">
        <p style="margin-right: 1rem;">도안 공유</p>
        <v-switch v-model="shared" hide-details inset color = "pink-lighten-1"></v-switch>
      </div>
      <div style="height: 100px" ></div>
  </div>
</template>
<style scoped>
.paletteColors {
  display: flex;
  flex-direction: row;
  padding-right: 1.15rem;
}
</style>
