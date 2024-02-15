<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
const fadein = ref(false)
const buttonFadein = ref(false)
import { useImageStore } from '../../stores/image'
import { useRouter } from 'vue-router'
const host = import.meta.env.VITE_APP_AI_SERVER_URL
const router = useRouter()
const { setImage } = useImageStore()
const nextFadein = () => {
  buttonFadein.value = true
}

onMounted(() => {
  fadein.value = true
})

const dialog = ref(false)
const file = ref([])
const loadingState = ref(false)
const makeImage = () => {
  loadingState.value = true
  let fileData = new FormData()
  fileData.append('image', file.value[0])

  if (file.value) {
    axios
      .post(host + '/draft/pipo-local', fileData, {
        headers: {
          'content-type': 'multipart/form-data'
        }
      })
      .then((res: any) => {
        setImage(false, res.data.palette, res.data.number_image, res.data.color_image, 'draft')
        dialog.value = false
        loadingState.value = false
        router.push('/draft/write')
      })
      .catch((err) => {
        console.log(err)
        loadingState.value = false
        router.push('/draft')
      })
  }
}
const preview = ref('../../assets/images/empty.png')

const previewFile = (e: any) => {
  console.log(e[0])
  const reader = new FileReader()
  reader?.readAsDataURL(e[0])
  reader.onloadend = () => {
    preview.value = reader.result as string
  }
}
</script>

<template>
  <div class="page">
    <Transition @after-enter="nextFadein" name="fade">
      <div v-show="fadein" class="logo">
        <div class="icons-with-boxes">
          <img src="@/assets/images/glass_boxes.svg" />
          <img class="logo-img" src="@/assets/logo.png" />
        </div>
      </div>
    </Transition>

    <div class="button-box">
      <Transition name="slide-fade">
        <div v-show="buttonFadein" class="button-group">
          <router-link to="/draft/aiprompt" style="text-decoration: none; color: inherit">
            <v-btn class="option-button" prepend-icon="mdi-palette-outline" color="primary"
              >새 이미지 생성하기</v-btn
            >
          </router-link>
          <v-dialog v-model="dialog" persistent width="18rem">
            <template v-slot:activator="{ props }">
              <v-btn
                v-bind="props"
                class="option-button"
                prepend-icon="mdi-palette-outline"
                color="primary"
                >내 갤러리에서 불러오기</v-btn
              >
            </template>
            <v-card style="padding: 1rem">
              <v-card-title>
                <span class="text-h5">사진</span>
              </v-card-title>
              <div v-if="!loadingState">
                <v-row style="display: flex; justify-content: center; padding: 1rem">
                  <img :src="preview" style="width: 4rem" />
                </v-row>

                <v-row style="display: flex; justify-content: center; margin: 1rem">
                  <template v-if="file.length === 0"></template>

                  <v-file-input
                    accept="image/png, image/jpeg, image/bmp"
                    placeholder="Pick an avatar"
                    prepend-icon="mdi-camera"
                    label="Image"
                    style="width: 80%"
                    v-model="file"
                    color="pink-darken-1"
                    background-color="pink-darken-1"
                    @change="previewFile(file)"
                  ></v-file-input>
                </v-row>
              </div>
              <div v-if="loadingState" style="display: flex; justify-content: center">
                <img src="../../assets/icon/loading.gif" alt="로딩 중..." style="width: 10rem" />
              </div>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="pink-darken-1" variant="tonal" @click="(dialog = false), (file = [])">
                  취소
                </v-btn>
                <v-btn color="purple-darken-1" variant="tonal" @click="makeImage()"> 만들기 </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </Transition>
    </div>
  </div>
</template>

<style scoped>
.page {
  height: 85vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 5rem;
}
.logo {
  justify-self: center;
}
.logo-img {
  display: flex;
  width: 7.125rem;
  height: 7.125rem;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;

  position: absolute;
  bottom: 1.7rem;
  left: 1.7rem;
}

.icons-with-boxes {
  width: 12.125rem;
  height: 12.3125rem;
  flex-shrink: 0;
  position: relative;
}

.button-group {
  display: inline-flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1.875rem;
}

.button {
  display: flex;
  width: 15.0625rem;
  align-items: flex-start;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.7s ease;
}

.fade-enter-from, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}

.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateY(30px);
  opacity: 0;
}
.btn-container {
  justify-content: center;
}

.option-button {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 0.875rem 1.25rem;
  border-radius: 0.475rem;
  background: linear-gradient(180deg, #f6b4bf 0%, #dcc4ee 100%);
  gap: 0.625rem;
  text-decoration: none;
}
.inner-message {
  color: var(--system-white, #fff);
  font-family: 'Abril Fatface';
  font-size: 1.0625rem;
  font-style: normal;
  font-weight: 400;
  line-height: 1.375rem; /* 129.412% */
  letter-spacing: -0.02688rem;
  text-decoration: none;
}
</style>
