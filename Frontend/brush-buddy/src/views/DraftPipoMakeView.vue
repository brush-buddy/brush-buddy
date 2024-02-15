<script setup lang="ts">
import SinglePaletteComponent from '../components/common/SinglePaletteComponent.vue'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
import { useImageStore } from '../stores/image'
import { localAxios } from '../api/axios'
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const { isAI, pipoPalette, pipoUrl, colorUrl, prompt } = storeToRefs(useImageStore())

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
  { text: '도시', icon: 'mdi-city' },
  { text: '여행', icon: 'mdi-wallet-travel' },
  { text: '연인', icon: 'mdi-heart' }
])
console.log(isAI)
console.log(pipoPalette)
console.log(pipoUrl)
console.log(prompt)

const shared = ref(true)
const categories = ref([])
const title = ref('')
const save = () => {
  localAxios()
    .post('/draft', {
      draftFileLink: pipoUrl.value,
      palette: pipoPalette.value,
      draftIsAI: isAI.value,
      paletteTitle: title.value,
      draftPrompt: prompt.value,
      draftShare: shared.value,
      categoryList: categories.value,
      imageFile: colorUrl.value
    })
    .then((response: any) => {
      router.push('/diary')
    })
}
const dialog = ref(false)

const discard = () => {
  router.push('/draft')
}

onMounted(() => {
  console.log(pipoUrl)
})
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
      <template v-for="([key, value], index) in Object.entries(pipoPalette)" :key="index">
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
        placeholder="팔레트 이름"
        v-model="title"
        style="width: 20rem; height: 2rem; background-color: #2300270a; padding: 1rem"
      />
    </div>
    <!-- https://vuetifyjs.com/en/components/chips/#custom-list-->

    <v-autocomplete
      v-model="categories"
      :items="items"
      chips
      closable-chips
      color="blue-grey-lighten-2"
      item-title="text"
      item-value="text"
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

    <div style="display: flex; justify-content: flex-end; width: 80vw">
      <v-btn
        color="pink-accent-1"
        size="small"
        @click="discard()"
        variant="tonal"
        style="margin-right: 10px"
        >취소</v-btn
      >
      <div>
        <v-dialog v-model="dialog" persistent width="auto">
          <template v-slot:activator="{ props }">
            <v-btn color="purple-lighten-1" size="small" variant="tonal" v-bind="props" style=""
              >만들기</v-btn
            >
          </template>
          <v-card
            style="padding: 1rem; display: flex; justify-content: center; justify-content: c\;"
          >
            <div style="display: flex; justify-content: center; flex-direction: column">
              <div style="display: flex; justify-content: center; margin-bottom: 0.5rem">
                <img :src="pipoUrl" alt="" style="max-height: 15rem; min-width: 15rem" />
              </div>
              <v-spacer style="height: 1rem"></v-spacer>
              <card-title>
                <span class="text-h5">{{ title }}</span>
              </card-title>
              <v-spacer style="height: 1rem"></v-spacer>
              <div class="paletteColors" style="padding: 0.5rem">
                <template v-for="([key, value], index) in Object.entries(pipoPalette)" :key="index">
                  <SinglePaletteComponent :color="value" />
                </template>
              </div>

              <div style="display: flex; justify-content: flex-end; margin-bottom: 0.5rem">
                <v-chip color="green">
                  <p>공유</p>
                  <v-icon v-if="shared" icon="mdi-share"></v-icon>
                  <v-icon v-if="!shared" icon="mdi-share-off"></v-icon>
                </v-chip>
              </div>
              <div style="display: flex; justify-content: flex-end">
                <template v-for="(value, key) in categories" :key="key">
                  <v-chip style="margin-left: 0.5rem">{{ value }}</v-chip>
                </template>
              </div>
            </div>
            <v-spacer></v-spacer>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="red-darken-1"
                size="small"
                variant="tonal"
                prepend-icon="mdi-trash-can"
                @click="(dialog = false), router.push('/draft')"
              >
                취소하기
              </v-btn>
              <v-btn
                color="green-darken-1"
                size="small"
                variant="tonal"
                @click="dialog = false"
                prepend-icon="mdi-pencil"
              >
                재작성하기
              </v-btn>
              <v-btn
                color="purple-lighten-1"
                size="small"
                variant="tonal"
                @click="save(), (dialog = false)"
                prepend-icon="mdi-content-save"
                >저장하기</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
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
