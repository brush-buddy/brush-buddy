<template>
  <div style="padding: 1rem">
    <div style="height: 2rem"></div>
    <h1 class="communityTitle" default="blanktitle">{{ community['title'] }}</h1>
    <div
      style="
        display: flex;
        justify-content: flex-end;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        align-items: center;
      "
    >
      <svg xmlns="http://www.w3.org/2000/svg" width="10" height="9" viewBox="0 0 10 9" fill="none">
        <path
          d="M9.24719 3.06994C9.24719 3.72816 8.99483 4.36038 8.54416 4.82806C7.50677 5.90494 6.50059 7.02786 5.42444 8.06571C5.17777 8.30014 4.78648 8.29159 4.55043 8.04656L1.45004 4.82806C0.51291 3.85521 0.51291 2.28466 1.45004 1.31183C2.39638 0.329435 3.93807 0.329435 4.8844 1.31183L4.99711 1.42881L5.10973 1.3119C5.56346 0.840632 6.18141 0.574829 6.82694 0.574829C7.47248 0.574829 8.09038 0.840607 8.54416 1.31183C8.99487 1.77954 9.24719 2.41173 9.24719 3.06994Z"
          stroke="black"
          stroke-width="0.7"
          stroke-linejoin="round"
        />
      </svg>
      <p class="thumbnailUnder">{{ community.likeNumber }}</p>
      <svg xmlns="http://www.w3 .org/2000/svg" width="14" height="9" viewBox="0 0 14 9" fill="none">
        <path
          d="M1.23334 4.66373C3.5 -0.254608 10.3 -0.254608 12.5667 4.66373"
          stroke="black"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
        <path
          d="M6.90003 7.31206C6.06544 7.31206 5.38892 6.63454 5.38892 5.79873C5.38892 4.96292 6.06544 4.2854 6.90003 4.2854C7.73462 4.2854 8.41114 4.96292 8.41114 5.79873C8.41114 6.63454 7.73462 7.31206 6.90003 7.31206Z"
          stroke="black"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
      </svg>
      <p class="thumbnailUnder">{{ community.views }}</p>
    </div>
    <div>
      <v-carousel
        :continuous="false"
        :show-arrows="false"
        hide-delimiter-background
        delimiter-icon="mdi-square"
        class="elevation-2"
      >
        <v-carousel-item
          v-for="(item, i) in community.photo"
          :key="i"
          :src="item.imgUrl"
          cover
        ></v-carousel-item>
      </v-carousel>
    </div>
    <div>
      <div
        style="margin-top: 1rem; display: flex; justify-content: space-between; padding-right: 1rem"
      >
        <div>
          <template v-for="(item, i) in community.hashtag" :key="i">
            <v-chip size="small" color="success" style="margin: 0.3rem">
              {{ item }}
            </v-chip>
          </template>
        </div>

        <!-- <v-icon v-if="likeState" icon="mdi-thumb-up" @click=""></v-icon> -->
        <!-- <v-icon v-if="!likeState" icon="mdi-thumb-up-outline" @click=""></v-icon> -->
      </div>
    </div>

    <div style="margin-top: 1rem; margin-bottom: 1rem">
      <p>{{ community.contents }}</p>
    </div>
    <div id="reply">
      <ReplyComponent :board-id="Number(boardId)" @replyReload="reloadReplyList" />
    </div>

    <div
      style="display: flex; justify-content: center; flex-direction: column; align-items: center"
    >
      <template v-for="(item, i) in replyList" :key="i">
        <CReplyListComponent
          :replyList="item"
          :board-id="Number(boardId)"
          @replyReload="reloadReplyList"
        />
      </template>
    </div>

    <div class="blank"></div>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, inject, ref } from 'vue'
import ReplyComponent from '../components/DraftDetail/CReply.vue'
import { localAxios } from '../api/axios'
import { storeToRefs } from 'pinia'
import { getReplyList } from '../api/board'
import type { ReplyListElement, BoardSearchParam } from '../api/board'
import CReplyListComponent from '../components/DraftDetail/CReplyListComponent.vue'
// import { useLikeStore } from '../stores/boardlike'

// const { likeState } = storeToRefs(useLikeStore())
const route = useRoute()
const boardId = route.params.id
const community = ref<any>({
  boardId: -1,
  title: ' ',
  contents: ' ',
  thumbnail: ' ',
  photo: [],
  draftId: -1,
  likeNumber: 0,
  views: 0,
  hashtag: [],
  createdAt: '-'
})

const replyList = ref<ReplyListElement[]>([])

const params = ref<BoardSearchParam>({
  listNum: 10000,
  pageNum: 1
})
console.log(route.params)
onMounted(() => {
  localAxios()
    .get(`/board/${boardId}`)
    .then(function (response: any) {
      console.log(response.data)
      community.value = response.data
    })

  // console.log(parseInt(boardId))
  // useLikeStore().setLikeState(parseInt(boardId))
  // console.log(likeState)

  getReplyList(Number(boardId), params.value).then((res) => {
    replyList.value = res.data.replyList
  })
  console.log('reply')
  console.log(replyList.value)
})

const reloadReplyList = () => {
  console.log('reloadReplyList')
  getReplyList(Number(boardId), params.value).then((res) => {
    replyList.value = res.data.replyList
    console.log(replyList.value)
  })
}
</script>

<style scoped>
#reply {
  margin-bottom: 6rem;
  display: flex;
  justify-content: center;
}

.blank {
  margin-bottom: 6rem;
}

.thumbnailUnder {
  font-size: 0.9rem;
  margin-left: 0.5rem;
  margin-right: 0.5rem;
  margin-top: 0;
  margin-bottom: 0;
  display: flex;
  align-items: center;
}
</style>
