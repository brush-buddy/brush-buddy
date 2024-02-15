<script setup lang="ts">
import type { PaletteListType } from '@/api/palette'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const props = defineProps<{ paletteInfo: PaletteListType }>()
const imgSrc = ref<String>('')

onMounted(() => {
  imgSrc.value = props.paletteInfo.draftImage
})
</script>

<template>
  <div
    style="width: 10; height: 13rem; margin-top: 1.5rem"
    @click="router.push(`/palette/${paletteInfo.paletteId}`)"
  >
    <div class="paletteThumbnail">
      <img class="elevation-2" :src="paletteInfo.draftImage" alt="" />
      <div class="paletteBg">
        <template v-for="(color, i) in JSON.parse(paletteInfo.paletteColorCode)" :key="i">
          <div class="palettecomponent" v-bind:style="{ backgroundColor: color }"></div>
        </template>
      </div>
    </div>
    <div
      style="margin-top: 1rem; display: flex; justify-content: space-between; margin: 1rem 0.5rem"
    >
      <h3 style="">{{ paletteInfo.paletteName }}</h3>
      <div>
        <img src="" alt="" />
        <p>{{ paletteInfo.nickName }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.paletteThumbnail {
  > img {
    max-width: 10rem;
    max-height: 10rem;
    border-radius: 1rem;
    position: relative;
    z-index: 0;
  }
  position: relative;
}

.paletteBg {
  width: 10rem;
  height: 2rem;
  z-index: 1;
  background-color: #ffffff;
  /* opacity: 0.5; */
  position: absolute;
  border: 1px solid #e0e0e0;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
  border-radius: 1rem;
  /* transition: 0.5s; */
  cursor: pointer;
  bottom: -0.5rem;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.palettecomponent {
  width: 0.9rem;
  height: 0.9rem;
  border-radius: 0.5rem;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
}
</style>
