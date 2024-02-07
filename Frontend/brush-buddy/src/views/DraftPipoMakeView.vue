<script setup lang="ts">
import SinglePaletteComponent from '../components/SinglePaletteComponent.vue'
const props = defineProps({
  pipoUrl: String,
  pipoPalette: {},
  isAI: Boolean,
  prompt: String
})
import { ref, computed, watch } from 'vue'

const items = ref([
  { text: '음식', icon: 'mdi-food-fork-drink' },
  { text: '애니메이션 캐릭터', icon: 'mdi-qqchat' },
  { text: '로봇', icon: 'mdi-robot-love-outline' },
  { text: '나무', icon: 'mdi-tree' },
  { text: '바다', icon: 'mdi-waves' },
  { text: '눈', icon: 'mdi-snowflake' },
  { text: '과일', icon: 'mdi-food-apple' },
  { text: '패션', icon: 'mdi-sunglasses' },
  { text: '봄', icon: 'mdi-flower' },
  { text: '강변', icon: 'mdi-sail-boat' },
  { text: '별자리', icon: 'mdi-creation-outline' },
  { text: '애완동물', icon: 'mdi-cat' },
  { text: '자연', icon: 'mdi-nature' },
  { text: '자연', icon: 'mdi-city' },
  { text: '여행', icon: 'mdi-wallet-travel' }
])
const shared = ref(true)
const friends = ref([])

const save = () => {
  console.log('저장')
}

const discard = () => {
  console.log('취소')
}
</script>

<template>
  <div style="display: flex; justify-content: center; flex-direction: column; align-items: center">
    <img
      :src="pipoUrl"
      alt="도안 이미지"
      style="
        width: 18rem;
        margin-top: 2rem;
        border-radius: 1rem;
        filter: drop-shadow(0px 0px 10px 0px #6e6e6e10);
      "
    />

    <div style="height: 3rem; display: flex; align-items: center">
      <p>색 추출 결과</p>
    </div>
    <div class="paletteColors">
      <template v-for="(value, key) in pipoPalette" :key="key">
        <SinglePaletteComponent :color="value" />
      </template>
    </div>

    <div
      style="
        display: flex;
        flex-direction: row;
        align-items: center;
        width: 20rem;
        justify-content: flex-end;
      "
    >
      <p style="margin-right: 1rem">도안 공유</p>
      <div>
        <v-switch
          v-model="shared"
          hide-details
          inset
          color="pink-lighten-1"
          style="margin-right: 1rem"
        ></v-switch>
      </div>
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
      <input
        type="text"
        placeholder="도안 이름"
        style="width: 20rem; height: 2rem; background-color: #2300270a; padding: 1rem"
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
      bg-color="white"
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
      <v-btn
        color="pink-accent-1"
        size="small"
        @click="discard()"
        variant="tonal"
        style="margin-right: 10px"
        >취소</v-btn
      >
      <v-btn color="purple-lighten-1" size="small" variant="tonal" @click="save()">만들기</v-btn>
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
input:focus {
  outline: none;
}
v-switch.d-inline-block {
  width: 1rem;
}
</style>
