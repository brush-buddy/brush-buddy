<script setup lang="ts">
import { useRoute } from 'vue-router'
import { localAxios } from '../api/axios'
import { ref } from 'vue'
import { onMounted } from 'vue'
import router from '@/router'

const route = useRoute()
const paletteId = route.params.id
const removedialog = ref(false)
const paletteInfo = ref({
  draftImage: '',
  paletteName: '',
  paletteColorCode: '',
  paletteModifiedTime: '',
  paletteCreatedAt: ''
})
const paletteTitle = ref()
const paletteColorInfo = ref()
const maxColorKey = ref(-1)
onMounted(async () => {
  dialog.value = false
  const response = await localAxios().get(`/palette/${paletteId}`)
  paletteInfo.value = response.data
  paletteColorInfo.value = Object.entries(JSON.parse(response.data.paletteColorCode))
  const listKeys = ref<number[]>([])
  Object.keys(JSON.parse(response.data.paletteColorCode))
  Object.keys(JSON.parse(response.data.paletteColorCode)).forEach((element: string) => {
    listKeys.value.push(parseInt(element))
  })
  maxColorKey.value = Math.max(...listKeys.value) + 1

  paletteTitle.value = response.data.paletteName
})
const color = ref('')

const setColor = (str: string) => {
  color.value = str
}

const dialog = ref(false)
const modifyState = ref(false)
const appendColorCode = ref('')

const iconPush = (key: string) => {
  if (!modifyState.value) {
    dialog.value = true
    setColor(key)
  } else {
    paletteColorInfo.value = paletteColorInfo.value.filter(
      (element: string[]) => element[1] !== key
    )
  }
}

const saveColor = () => {
  paletteColorInfo.value.push([maxColorKey.value, appendColorCode.value])
  maxColorKey.value++
}

const saveServer = () => {
  paletteColorInfo.value.forEach((element: any) => {
    console.log(element)
  })
  localAxios().put(`/palette/${paletteId}`, {
    paletteName: paletteTitle.value,

    paletteColorCode: paletteColorInfo.value.reduce(
      (obj: string[], [key, value]: [number, string]) => {
        obj[key] = value
        return obj
      },
      {} as Record<number, string>
    )
  })
}

const removeItem = () => {
  localAxios().delete(`/palette/${paletteId}`)
  router.push('/diary')
}

import { printPaint  } from '@/api/machine'

const printColor = (color:String) => {
  const data = {
    id : 1,
    color : color
  }
  
  printPaint(data)
}
</script>

<template>
  <div style="display: flex; justify-content: center">
    <v-card style="padding: 1rem; width: 80vw" elevation="5">
      <v-img :src="paletteInfo.draftImage" style="height: 60vh; margin: 1rem"></v-img>

      <h2 v-if="!modifyState">{{ paletteTitle }}</h2>
      <div v-if="modifyState">
        <v-text-field type="text" v-model="paletteTitle" append-inner-icon="mdi-pencil" />
      </div>
      <div class="flex-container">
        <template v-for="([key, value], index) in paletteColorInfo" :key="index">
          <div
            v-bind:style="{ backgroundColor: value }"
            style="
              width: 2rem;
              height: 2rem;
              margin: 0.1rem;
              display: flex;
              justify-content: center;
              align-items: center;
            "
            @click="iconPush(value)"
          >
            <v-icon v-if="modifyState" icon="mdi-trash-can" color="grey" size="x-small"></v-icon>
          </div>
        </template>
        <v-dialog v-model="dialog" width="auto">
          <v-card v-if="!modifyState">
            <v-card-text> 연결된 기기 {{}}</v-card-text>

            <div style="padding: 1rem; display: flex; align-items: center">
              <v-btn
                icon="mdi-format-color-fill"
                size="x-large"
                :color="color"
                style="margin: 1rem"
              ></v-btn>
              <div>
                <p>출력하시겠습니까?</p>
                <div style="display: flex; align-items: center">
                  <v-icon icon="mdi-hand-pointing-left" size="x-large"></v-icon>
                </div>
              </div>
            </div>
            <v-card-actions style="display: flex; justify-content: flex-end; width: 80vw">
              <v-btn color="primary" @click="dialog = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <div style="display: flex; justify-content: flex-end">
        <v-dialog width="400">
          <template v-slot:activator="{ props }">
            <div v-if="modifyState">
              <v-btn v-bind="props" icon="mdi-select-color" style="margin: 1rem"> </v-btn>
            </div>
          </template>
          <template v-slot:default="{ isActive }">
            <v-card>
              <div style="display: flex; justify-content: center">
                <v-color-picker
                  style="margin: 2rem"
                  v-model="appendColorCode"
                  elevation="3"
                  :modes="['hex']"
                ></v-color-picker>
              </div>

              <v-card-actions style="display: flex; justify-content: flex-end">
                <v-btn color="primary" @click="(isActive.value = false), saveColor()"
                  >저장하기</v-btn
                >
                <v-btn color="primary" @click="isActive.value = false">닫기</v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
      </div>

      <div class="discardModify" style="display: flex; justify-content: flex-end; margin-top: 1rem">
        <v-dialog v-model="removedialog" persistent width="auto">
          <template v-slot:activator="{ props }">
            <v-btn
              class="buttonUnder"
              color="red-darken-1"
              size="small"
              variant="tonal"
              v-bind="props"
              prepend-icon="mdi-trash-can"
            >
              삭제하기
            </v-btn>
          </template>
          <v-card>
            <v-card-text>정말 삭제하시겠습니까? 삭제하시면 복구가 불가능합니다.</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn color="green-darken-1" variant="text" @click="removedialog = false">
                취소
              </v-btn>
              <v-btn
                color="red-darken-1"
                variant="text"
                @click="(removedialog = false), removeItem()"
              >
                삭제
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-btn
          v-if="!modifyState"
          class="buttonUnder"
          color="green-darken-1"
          size="small"
          variant="tonal"
          prepend-icon="mdi-pencil"
          @click="modifyState = true"
        >
          수정하기
        </v-btn>
        <v-btn
          v-if="modifyState"
          class="buttonUnder"
          color="purple-darken-1"
          size="small"
          variant="tonal"
          prepend-icon="mdi-pencil"
          @click="(modifyState = false), saveServer()"
        >
          수정 완료하기
        </v-btn>
      </div>
    </v-card>
  </div>
</template>

<style scoped>
.buttonUnder {
  margin-left: 1rem;
}

.flex-container {
  display: flex;
  flex-wrap: wrap;
}
</style>
