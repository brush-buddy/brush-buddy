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
  paletteCreatedAt: '',
  isAdmin: false,
  nickName: ''
})
const paletteTitle = ref()
const paletteColorInfo = ref()
const maxColorKey = ref(-1)
const isAdmin = ref(false)
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
  isAdmin.value = response.data.isAdmin
})
const color = ref('')

const setColor = (str: string) => {
  color.value = str
}

const dialog = ref(false)
const modifyState = ref(false)
const appendColorCode = ref('')

const iconPush = (key: string) => {
  if (!modifyState.value && isAdmin.value) {
    dialog.value = true
    setColor(key)
  } else if (modifyState.value) {
    paletteColorInfo.value = paletteColorInfo.value.filter(
      (element: string[]) => element[1] !== key
    )
  } else {
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

import { printPaint } from '@/api/machine'
import { useUserStore } from '@/stores/user'

const printColor = async (color: String) => {
  const data = {
    rgbcode: color
  }
  dialog.value = false
  await printPaint(data)
    .then((res) => {
      dialog.value = false
      alert('출력이 완료되었습니다.')
    })
    .catch((err) => {
      alert('기기정보를 입력하세요')
      router.push('/diary/palette')
    })
}

const userStore = useUserStore()

const downloadPalette = () => {
  localAxios()
    .post(`palette/${paletteId}/duplicate`)
    .then((res) => {
      alert('팔레트 복사가 완료 되었습니다.')
    })
}
</script>

<template>
  <div style="display: flex; justify-content: center; margin-top: 2rem">
    <v-card style="padding: 1rem; width: 80vw" elevation="5">
      <v-img :src="paletteInfo.draftImage" style="max-height: 60vh; margin: 1rem"></v-img>

      <h2 v-if="!modifyState" style="margin-bottom: 1rem">{{ paletteTitle }}</h2>
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
            <p style="line-height: 100$">{{ key }}</p>
            <v-icon v-if="modifyState" icon="mdi-trash-can" color="grey" size="x-small"></v-icon>
          </div>
        </template>
        <v-dialog v-model="dialog" width="auto">
          <v-card v-if="!modifyState && isAdmin">
            <v-card-text> 연결된 기기 {{}}</v-card-text>

            <div style="padding: 1rem; display: flex; align-items: center">
              <v-btn
                icon="mdi-format-color-fill"
                size="x-large"
                :color="color"
                style="margin: 1rem"
                @click="printColor(color)"
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
      <div style="margin-top: 1rem; display: flex; justify-content: space-between">
        <v-icon v-if="!modifyState" icon="mdi-tray-arrow-down" @click="downloadPalette()"></v-icon>

        <div class="discardModify" style="display: flex; justify-content: flex-end">
          <v-dialog v-model="removedialog" persistent width="auto">
            <template v-slot:activator="{ props }">
              <v-btn
                v-if="paletteInfo.isAdmin"
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
            v-if="!modifyState && paletteInfo.isAdmin"
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
            v-if="modifyState && paletteInfo.isAdmin"
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
      </div>
    </v-card>
  </div>
  <footer style="height: 8rem; width: 100vw"></footer>
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
