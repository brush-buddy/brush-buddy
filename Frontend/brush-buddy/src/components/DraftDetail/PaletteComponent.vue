<script setup lang="ts">
import { useRoute } from 'vue-router'
import { localAxios } from '../../api/axios'
import { ref, watch, onMounted } from 'vue'
// import { onMounted } from 'vue'
import router from '../../router'

const props = defineProps<{
  draftId: number
  draftColorCode: string
}>()

const paletteColorInfo = ref([] as [string, string][])

watch(
  () => props.draftColorCode,
  (newVal) => {
    try {
      const parsedData = JSON.parse(newVal)
      paletteColorInfo.value = Object.entries(parsedData)
      // console.log(paletteColorInfo.value)
    } catch (error) {
      console.error('Error parsing JSON:', error)
    }
  }
)
</script>

<template>
  <div
    style="display: inline-flex; justify-content: center; align-items: flex-end; margin-right: -6px"
  >
    <div class="flex-container">
      <template v-for="([key, value], index) in paletteColorInfo" :key="index">
        <div
          v-bind:style="{ backgroundColor: value }"
          style="
            width: 2.3rem;
            height: 2.3rem;
            margin: 0.1rem;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-right: -12px;
            border-radius: 50%;
            box-shadow: 0px 1px 1px 1px rgba(0, 0, 0, 0.25);
            border: 1.5px solid rgb(201, 201, 201);
          "
          class="elevation-2"
        ></div>
      </template>
    </div>
  </div>
</template>

<style scoped>
.flex-container {
  display: flex;
  flex-wrap: wrap;
}
</style>
