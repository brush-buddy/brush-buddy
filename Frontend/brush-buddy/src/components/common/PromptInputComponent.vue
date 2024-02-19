<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { localAxios } from '../../api/axios'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useImageStore } from '../../stores/image'

const host = import.meta.env.VITE_APP_AI_SERVER_URL

const router = useRouter()
const { setImage } = useImageStore()
const leftCnt = ref(0)
const prompt = ref<string>('')
const dialog = ref(false)

const loadingState = ref(true)
const imageSrc = ref('../../assets/icon/loading.gif')
const makeImage = () => {
  console.log(prompt.value)
  if (prompt.value == '') {
    alert('내용을 입력해주세요')
    loadingState.value = false
    dialog.value = false
    return
  }
  dialog.value = true
  loadingState.value = true
  localAxios()
    .post('/draft/ai-generation', { prompt: prompt.value })
    .then((response) => {
      console.log(response.data)
      imageSrc.value = JSON.parse(response.data.body).image_url
      leftCnt.value = JSON.parse(response.data.body).left_cnt
      loadingState.value = false
    })
}
const makePipo = () => {
  loadingState.value = true

  axios.post(host + '/draft/pipo-s3', { url: imageSrc.value }).then((response) => {
    setImage(
      true,
      response.data.palette,
      response.data.number_image,
      response.data.color_image,
      prompt.value
    )
    dialog.value = false
    loadingState.value = false
    router.push('/draft/write')
  })
}
// ai 호출 횟수 가져오기
onMounted(async () => {
  await localAxios()
    .get('/draft/get_cnt')
    .then((response) => {
      console.log(JSON.parse(response.data.body).left_cnt)
      leftCnt.value = JSON.parse(response.data.body).left_cnt
    })
})
//-그려줘라고 입력하면 그림을 만들어드려요
</script>

<template>
  <div>호출 횟수가 {{ leftCnt }}번 남았어요!</div>
  <div class="input-box-container">
    <!-- <div class="input-container"> -->

    <input
      type="text"
      v-model="prompt"
      class="search-input-box"
      placeholder="-그려줘라고 입력하면 그림을 만들어드려요"
      style="width: 18rem"
      v-on:keyup.enter="makeImage()"
    />
    <div>
      <v-btn
        @click="prompt != '' ? makeImage() : (dialog = false)"
        icon="mdi-arrow-up"
        size="small"
        color="success"
      ></v-btn>
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent width="auto">
          <v-card>
            <div style="display: flex; justify-content: center">
              <img
                src="../../assets/icon/loading.gif"
                alt=""
                style="margin: 2rem; width: 10rem"
                v-show="loadingState"
              />
              <!-- <div class="">
                <CButton :text="left_cnt + '/20'" :color="'#f6b4bf'" />
              </div> -->
              <img
                :src="imageSrc"
                alt=""
                v-show="!loadingState"
                style="margin: 2rem; width: 15rem"
              />
            </div>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="green-darken-1"
                variant="text"
                @click="(dialog = false), (loadingState = false)"
                prepend-icon="mdi-delete-empty"
              >
                취소
              </v-btn>
              <v-btn
                color="green-darken-1"
                variant="text"
                @click="(loadingState = true), makePipo()"
                v-bind:disabled="loadingState"
                prepend-icon="mdi-brush-variant"
              >
                도안 만들기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </div>
    <!-- </div> -->
  </div>
</template>

<style scoped>
.left_cnt {
  font-size: 1.5rem;
  font-weight: bold;
  margin: 1rem;
}

.input-box-container {
  display: flex;
  width: 21.5rem;
  height: 2.125rem;
  flex-direction: row;
  align-items: center;
  flex-shrink: 0;
  justify-content: space-around;
}

.input-box-sub-container {
  display: flex;
  flex-direction: column;

  align-self: stretch;
}

.search-input-box {
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  align-self: stretch;
  border-radius: 0.625rem;
  opacity: 0.5;
  background: var(--system-grey4, #d2d2d7);
  color: var(--label-primary, #000);
  text-align: center;
  font-family: ABeeZee;
  font-size: 1.0625rem;
  font-style: normal;
  font-weight: 400;
}
input[type='text']::placeholder {
  font-size: 0.8rem;
}
</style>
