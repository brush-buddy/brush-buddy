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
  <div style="height: 5rem; width: 100vw"></div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import CommunityComponent from '../components/community/CommunityComponent.vue'
import type { BoardThumbnail } from '../api/type'

const currentPage = ref(0)
const showloader = ref(false)
const boardThumbnailDataFirst = ref<BoardThumbnail[]>([])

const boardThumbnailDataSecond = ref<BoardThumbnail[]>([])

const pageCount = ref(1)

const scrollTriggerElement = ref(null)
const scrollTriggerElement2 = ref(null)

const scrollTrigger = () => {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.intersectionRatio > 0 && currentPage.value < pageCount.value) {
        console.log('load!')

        console.log(currentPage.value + ' ' + pageCount.value)
        showloader.value = true
        setTimeout(() => {
          axios
            .get(
              'http://localhost:8080/v1/api/board/list?direction=DESC&listNum=10&pageNum=' +
                currentPage.value
            )
            .then((response: any) => {
              console.log(response.data.boards)
              pageCount.value = response.data.totalPage

              for (let i = 0; i < response.data.boards.length; i++) {
                if (i % 2 === 1) boardThumbnailDataSecond.value.push(response.data.boards[i])
                else boardThumbnailDataFirst.value.push(response.data.boards[i])
              }
              currentPage.value += 1
              showloader.value = false
            })
          currentPage.value += 1
          showloader.value = false
        }, 3000) // simulate Ajax-Call ;-)
      }
    })
  })
  if (!(scrollTriggerElement.value === null || scrollTriggerElement2.value === null)) {
    observer.observe(scrollTriggerElement.value)
    observer.observe(scrollTriggerElement2.value)
  }
}

onMounted(() => {
  axios
    .get('http://localhost:8080/v1/api/board/list?direction=DESC&listNum=10&pageNum=0')
    .then((response: any) => {
      console.log('onload!')
      pageCount.value = response.data.totalPage
      console.log('total Page ' + pageCount.value)
      for (let i = 0; i < response.data.boards.length; i++) {
        if (i % 2 === 1) boardThumbnailDataSecond.value.push(response.data.boards[i])
        else boardThumbnailDataFirst.value.push(response.data.boards[i])
      }
      currentPage.value += 1
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

@keyframes animate {
  0% {
    transform: translate(-50%, -50%) rotate(0deg);
  }
  100% {
    transform: translate(-50%, -50%) rotate(360deg);
  }
}
</style>
