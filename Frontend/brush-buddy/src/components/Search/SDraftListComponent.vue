<template>
  <div style="display: flex; justify-content: space-around">
    <div style="display: flex; flex-direction: column; align-items: center">
      <div v-for="(card, i) in communityThumbnailDataFirst" :key="i">
        <DraftThumbnailComponent :draftThumbnail="card" />
      </div>

      <footer>
        <div ref="scrollTriggerElement2" id="scroll-trigger"></div>
        <div class="circle-loader" v-if="showloader"></div>
      </footer>
    </div>
    <div style="display: flex; flex-direction: column; align-items: center">
      <div v-for="(card, i) in communityThumbnailDataSecond" :key="i">
        <DraftThumbnailComponent :draftThumbnail="card" />
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
import { ref, onMounted } from 'vue'
import { localAxios } from '../../api/axios'
import DraftThumbnailComponent from '../Draft/DraftThumbnailComponent.vue'
import type { DraftThumbnail } from '../../api/draft'

const props = defineProps<{
  searchValue: string
}>()
const communityThumbnailDataFirst = ref<DraftThumbnail[]>([])
const communityThumbnailDataSecond = ref<DraftThumbnail[]>([])

const pageCount = ref(1)
const currentPage = ref(0) // page는 1번부터 시작
const showloader = ref(false)

const scrollTriggerElement = ref(null)
const scrollTriggerElement2 = ref(null)

const scrollTrigger = () => {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.intersectionRatio > 0 && currentPage.value < pageCount.value) {
        showloader.value = true
        setTimeout(() => {
          localAxios()
            .get(
              props.searchValue == ''
                ? '/draft/list?size=10&page=' + currentPage.value
                : '/draft/list?size=10&page=' + currentPage.value + '&search=' + props.searchValue
            )
            .then((response: any) => {
              if (response.data.content == undefined) {
                return
              }

              pageCount.value = response.data.totalPages

              for (let i = 0; i < response.data.content.length; i++) {
                if (i % 2 === 1) communityThumbnailDataSecond.value.push(response.data.content[i])
                else communityThumbnailDataFirst.value.push(response.data.content[i])
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
    .get(
      props.searchValue == ''
        ? '/draft/list?size=10&page=0'
        : '/draft/list?size=10&page=0' + currentPage.value + '&search=' + props.searchValue
    )
    .then((response: any) => {
      if (response.data.content == undefined) {
        return
      }
      // 총 페이지 수 설정
      pageCount.value = response.data.totalPages

      for (let i = 0; i < response.data.content.length; i++) {
        if (i % 2 === 1) communityThumbnailDataSecond.value.push(response.data.content[i])
        else communityThumbnailDataFirst.value.push(response.data.content[i])
      } // 아이디 순서대로 정렬해서 적재

      currentPage.value = 1
    })
  scrollTrigger()
})

const searchList = () => {
  communityThumbnailDataFirst.value = []
  communityThumbnailDataSecond.value = []
  console.log(`community search called with searchValue: ${props.searchValue}`)
  localAxios()
    .get('/draft/list?size=10&page=0&search=' + props.searchValue)
    .then((response: any) => {
      if (response.data.content == undefined) {
        return
      }
      pageCount.value = response.data.totalPages
      for (let i = 0; i < response.data.content.length; i++) {
        if (i % 2 === 1) communityThumbnailDataSecond.value.push(response.data.content[i])
        else communityThumbnailDataFirst.value.push(response.data.content[i])
      }
      currentPage.value = 2
    })
}

defineExpose({
  searchList
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
