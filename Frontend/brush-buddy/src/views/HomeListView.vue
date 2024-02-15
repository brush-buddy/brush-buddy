<script setup lang="ts">
import PaletteThumbnailComponent from '@/components/Palette/PaletteThumbnailComponent.vue'
import { localAxios } from '@/api/axios'
import type { PaletteListType, SearchParam } from '../api/palette'
import { getBoardListAll } from '../api/palette'
import { ref, onMounted } from 'vue'

const paletteList = ref<PaletteListType[]>([])
const firstColumn = ref<PaletteListType[]>([])
const secondColumn = ref<PaletteListType[]>([])
onMounted(() => {
  const searchP = ref<SearchParam>({
    pageNum: 1,
    listNum: 100000
  })
  getBoardListAll(searchP.value).then((res) => {
    paletteList.value = res.data.palettes
    for (let i = 0; i < paletteList.value.length; i++) {
      if (i % 2 === 0) {
        firstColumn.value.push(paletteList.value[i])
      } else {
        secondColumn.value.push(paletteList.value[i])
      }
    }
  })
})
</script>

<template>
  <div style="display: flex; justify-content: space-evenly">
    <div>
      <template v-for="(paletteInfo, i) in firstColumn" :key="i">
        <PaletteThumbnailComponent :paletteInfo="paletteInfo" />
      </template>
    </div>
    <div>
      <template v-for="(paletteInfo, i) in secondColumn" :key="i">
        <PaletteThumbnailComponent :paletteInfo="paletteInfo" />
      </template>
    </div>
  </div>
</template>

<style scoped></style>
