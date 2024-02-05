<script setup lang="ts">
import SinglePaletteComponent from '../components/SinglePaletteComponent.vue'
const props = defineProps({
  pipoUrl: String,
  pipoPalette: {}
})
import { ref, computed, watch } from 'vue'

const items = ref([
  { text: 'Nature', icon: 'mdi-nature' },
  { text: 'Nightlife', icon: 'mdi-glass-wine' },
  { text: 'November', icon: 'mdi-calendar-range' },
  { text: 'Portland', icon: 'mdi-map-marker' },
  { text: 'Biking', icon: 'mdi-bike' }
])
const shared = ref(true)
const friends = ref([])
</script>

<template>
  <div style="display: flex; justify-content: center; flex-direction: column; align-items: center">
    <img :src="pipoUrl" alt="" style="width: 15rem; margin-top: 2rem" />
    <div style="height: 3rem; display: flex; align-items: center">
      <p>색 추출 결과</p>
    </div>
    <div class="paletteColors">
      <template v-for="(value, key) in pipoPalette" :key="key">
        <SinglePaletteComponent :color="value" />
      </template>
    </div>

    <div style="display: flex; flex-direction: row; align-items: center">
      <p style="margin-right: 1rem">도안 공유</p>
      <v-switch v-model="shared" hide-details inset color="pink-lighten-1"></v-switch>
    </div>

    <div
      style="
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-bottom: 1rem;
        width: 20rem;
        justify-content: space-around;
      "
    >
      <p>도안 이름</p>
      <input
        type="text"
        style="width: 60vw; height: 2rem; background-color: #2300270a; padding: 0.1rem"
      />
    </div>
    <!-- https://vuetifyjs.com/en/components/chips/#custom-list-->

    <v-autocomplete
      v-model="friends"
      :items="items"
      chips
      closable-chips
      color="blue-grey-lighten-2"
      item-title="name"
      item-value="name"
      label="카테고리"
      multiple
      style="width: 20rem"
    >
      <template v-slot:chip="{ props, item }">
        <v-chip v-bind="props" :prepend-icon="item.raw.icon" :text="item.raw.text"></v-chip>
      </template>

      <template v-slot:item="{ props, item }">
        <v-list-item
          v-bind="props"
          :prepend-icon="item.raw.icon"
          :title="item.raw.text"
        ></v-list-item>
      </template>
    </v-autocomplete>
    {{ friends }}
    <div style="display: flex; justify-content: flex-end; width: 80vw">
      <v-btn color="pink-accent-1" size="small" @click="discard()" style="margin-right: 10px"
        >취소</v-btn
      >
      <v-btn color="purple-lighten-1" size="small" @click="save()">만들기</v-btn>
    </div>

    <div style="height: 100px"></div>
  </div>
</template>
<style scoped>
.paletteColors {
  display: flex;
  flex-direction: row;
  padding-right: 1.15rem;
}
</style>
