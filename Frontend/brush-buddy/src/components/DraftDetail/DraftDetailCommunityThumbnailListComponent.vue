<template>
  <div>
    <h2 style="margin-left: 1rem">이 도안으로 만든 그림들</h2>
    <div style="display: flex; justify-content: space-around; margin-top: 1rem">
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
  </div>
  <div style="height: 5rem; width: 100vw"></div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { localAxios } from '../../api/axios'
import CommunityComponent from '../../components/Community/CommunityComponent.vue'
import type { BoardThumbnail } from '../../api/type'

const props = defineProps({ draftId: Number })

const boardThumbnailDataFirst = ref<BoardThumbnail[]>([])
const boardThumbnailDataSecond = ref<BoardThumbnail[]>([])

const pageCount = ref(1)
const currentPage = ref(1) // page는 1번부터 시작
const showloader = ref(false)

const scrollTriggerElement = ref(null)
const scrollTriggerElement2 = ref(null)

// 스크롤 시에 추가적인 데이터를 불러오는 함수
const scrollTrigger = () => {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.intersectionRatio > 0 && currentPage.value < pageCount.value) {
        showloader.value = true
        setTimeout(() => {
          localAxios()
            .get(`/draft/${props.draftId}/boardList?listNum=10&pageNum=${currentPage.value}`)
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

// 첫 페이지 로딩 시에 데이터를 불러오는 함수
onMounted(() => {
  localAxios()
    .get(`/draft/${props.draftId}/boardList?listNum=10&pageNum=1`)
    .then((response: any) => {
      // 총 페이지 수 설정
      pageCount.value = response.data.totalPage
      for (let i = 0; i < response.data.boards.length; i++) {
        if (i % 2 === 1) boardThumbnailDataSecond.value.push(response.data.boards[i])
        else boardThumbnailDataFirst.value.push(response.data.boards[i])
      } // 아이디 순서대로 정렬해서 적재

      currentPage.value = 1
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
