<template>
  <div style="display: flex; justify-content: space-around">
    <div style="display: flex; flex-direction: column; align-items: center">
      <div v-for="(card, i) in boardThumbnailDataFirst" :key="i">
        <CommunityComponent :boardThumbnail="card" />
      </div>

      <footer>
        <div ref="scrollTriggerElement2" id="scroll-trigger"></div>
        <div class="circle-loader" v-if="showloader"></div>
      </footer>
    </div>
    <div style="display: flex; flex-direction: column; align-items: center">
      <div v-for="(card, i) in boardThumbnailDataSecond" :key="i">
        <CommunityComponent :boardThumbnail="card" />
      </div>

      <footer>
        <div ref="scrollTriggerElement" id="scroll-trigger"></div>
        <div class="circle-loader" v-if="showloader"></div>
      </footer>
    </div>
  </div>
  <router-link to="/community/write">
    <div id="goWriteButton">
      <CWriteButton />
    </div>
  </router-link>
  <div style="height: 5rem; width: 100vw"></div>
</template>

<script setup lang="ts">
import { ref, inject, onMounted } from 'vue'
import { localAxios } from '../api/axios'
import { useRouter } from 'vue-router'
import CommunityComponent from '../components/Community/CommunityComponent.vue'
import type { BoardThumbnail } from '../api/type'
import CWriteButton from '../components/Community/CWriteButton.vue'

const boardThumbnailDataFirst = ref<BoardThumbnail[]>([])
const boardThumbnailDataSecond = ref<BoardThumbnail[]>([])

const pageCount = ref(2)
const currentPage = ref(0) // page는 1번부터 시작
const showloader = ref(false)

const scrollTriggerElement = ref(null)
const scrollTriggerElement2 = ref(null)

const scrollTrigger = () => {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.intersectionRatio > 0 && currentPage.value < pageCount.value) {
        console.log('load...')

        showloader.value = true
        setTimeout(() => {
          localAxios()
            .get('/board/list?direction=DESC&listNum=10&pageNum=' + currentPage.value)
            .then((response: any) => {
              pageCount.value = response.data.totalPage

              for (let i = 0; i < response.data.boards.length; i++) {
                if (i % 2 === 1) boardThumbnailDataSecond.value.push(response.data.boards[i])
                else boardThumbnailDataFirst.value.push(response.data.boards[i])
              }

              showloader.value = false
            })
          currentPage.value += 1
          showloader.value = false
        }, 3000)
      }
    })
  })
  if (
    // 내릴 필요가 없을 경우
    !(scrollTriggerElement.value === null || scrollTriggerElement2.value === null)
  ) {
    observer.observe(scrollTriggerElement.value)
    observer.observe(scrollTriggerElement2.value)
  }
}

onMounted(() => {
  localAxios()
    .get('/board/list?direction=DESC&listNum=10&pageNum=1')
    .then((response: any) => {
      console.log('onload!')
      // 총 페이지 수 설정
      pageCount.value = response.data.totalPage
      for (let i = 0; i < response.data.boards.length; i++) {
        if (i % 2 === 1) boardThumbnailDataSecond.value.push(response.data.boards[i])
        else boardThumbnailDataFirst.value.push(response.data.boards[i])
      } // 아이디 순서대로 정렬해서 적재

      currentPage.value = 2
    })
  scrollTrigger()
})
</script>

<style scoped>
article {
  margin: 0 auto;
  width: 100%;
}

article section {
  width: 100%;
  /* margin-bottom: 20px; */
  /* border-radius: 10px; */
  background-color: #efefef;
  color: #04525a;
  overflow: hidden;
}

article section p {
  margin: 0;
}

footer {
  position: relative;
  width: 50vw;
  height: 100px;
}

footer #scroll-trigger {
  height: 50px;
}

.circle-loader {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 5px solid rgba(255, 255, 255, 0.2);
  border-top: 5px solid #fff;
  animation: animate 1.5s infinite linear;
}
#goWriteButton {
  position: fixed;
  bottom: 10vh;
  right: 3vw;
}
@keyframes animate {
  0% {
    transform: translate(-50%, -50%) rotate(0deg);
  }
  100% {
    transform: translate(-50%, -50%) rotate(360deg);
  }
}
</style>
