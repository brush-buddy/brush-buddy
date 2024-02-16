<template>
  <div style="padding: 1rem">
    <div style="height: 2rem"></div>
    <h1 class="communityTitle" default="blanktitle">{{ community['title'] }}</h1>
    <div
      style="
        display: flex;
        justify-content: space-between;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        align-items: center;
      "
    >
      <div style="display: flex">
        <div
          style="
            background-color: rgb(255, 237, 200);
            width: 1.5rem;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 50%;
          "
        >
          <img src="../../src/assets/logo.png" alt="" style="width: 1rem" />
        </div>
        <p class="thumbnailUnder">{{ community.authorNickname }}</p>
      </div>
      <div style="display: flex; justify-content: flex-end; align-items: center">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="10"
          height="9"
          viewBox="0 0 10 9"
          fill="none"
        >
          <path
            d="M9.24719 3.06994C9.24719 3.72816 8.99483 4.36038 8.54416 4.82806C7.50677 5.90494 6.50059 7.02786 5.42444 8.06571C5.17777 8.30014 4.78648 8.29159 4.55043 8.04656L1.45004 4.82806C0.51291 3.85521 0.51291 2.28466 1.45004 1.31183C2.39638 0.329435 3.93807 0.329435 4.8844 1.31183L4.99711 1.42881L5.10973 1.3119C5.56346 0.840632 6.18141 0.574829 6.82694 0.574829C7.47248 0.574829 8.09038 0.840607 8.54416 1.31183C8.99487 1.77954 9.24719 2.41173 9.24719 3.06994Z"
            stroke="black"
            stroke-width="0.7"
            stroke-linejoin="round"
          />
        </svg>
        <p class="thumbnailUnder">{{ likeNumber }}</p>
        <svg
          xmlns="http://www.w3 .org/2000/svg"
          width="14"
          height="9"
          viewBox="0 0 14 9"
          fill="none"
        >
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
    </div>
    <div id="carousel">
      <template v-for="(item, i) in community.photo" :key="i">
        <img :src="item.imgUrl" class="carouselImg" alt="" />
      </template>
    </div>
    <div>
      <div
        style="margin-top: 1rem; display: flex; justify-content: space-between; padding-right: 1rem"
      >
        <div>
          <template v-for="(item, i) in community.hashtag" :key="i">
            <v-chip size="small" :color="getRandomColor()" style="margin: 0.3rem">
              {{ item }}
            </v-chip>
          </template>
        </div>
        <div style="width: 5rem; display: flex; justify-content: flex-end">
          <v-icon
            v-if="community.isMine"
            icon="mdi-trash-can"
            color="red"
            size="x-large"
            @click="deleteCommunity()"
            style="margin-right: 0.5rem"
          >
          </v-icon>
          <v-icon
            v-if="community.isMine"
            icon="mdi-pencil"
            color="yellow-darken-2"
            size="x-large"
            @click="modifyview = true"
            style="margin-right: 0.5rem"
          >
          </v-icon>
          <v-icon
            v-if="isLike"
            icon="mdi-thumb-up"
            @click="removeLikeState(Number(boardId)), likeNumber--"
            size="x-large"
          ></v-icon>
          <v-icon
            v-if="!isLike"
            icon="mdi-thumb-up-outline"
            @click="addLikeState(Number(boardId)), likeNumber++"
            size="x-large"
          ></v-icon>
        </div>
        <v-dialog v-model="modifyview" width="auto">
          <v-card>
            <v-text-field v-model="titleModel" label="Title" variant="underlined"></v-text-field>
            <TextAreaComponent style="height: 10rem; margin-top: 1rem" v-model="contentModel" />
            <v-combobox
              v-model="hashtagModel"
              chips
              clearable
              label="set category"
              multiple
              variant="solo"
            >
            </v-combobox>
            <v-card-actions>
              <v-btn color="primary" @click="modifyview = false">취소</v-btn>
              <v-btn color="pink-darken-2" @click="modifyview = false">수정</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
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
import { onMounted, ref } from 'vue'
import ReplyComponent from '../components/DraftDetail/CReply.vue'
import { localAxios } from '../api/axios'

import { storeToRefs } from 'pinia'
import { getReplyList } from '../api/board'
import type { ReplyListElement, BoardSearchParam } from '../api/board'
import CReplyListComponent from '../components/DraftDetail/CReplyListComponent.vue'
import { useRouter } from 'vue-router'

const router = useRouter()
import { useLikeStore } from '../stores/boardlike'

const { setLikeState, removeLikeState, addLikeState } = useLikeStore()
const likeStore = useLikeStore()
const { isLike } = storeToRefs(likeStore)
const likeNumber = ref(0)
const modifyview = ref(false)

function getRandomColor() {
  return '#' + Math.floor(Math.random() * 16777215).toString(16)
}

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
  createdAt: '-',
  isHeart: false,
  isMine: false,
  authorNickname: '-'
})

const hashtagModel = ref<string[]>([])
const titleModel = ref<string>('')
const contentModel = ref<string>('')

const replyList = ref<ReplyListElement[]>([])

const params = ref<BoardSearchParam>({
  listNum: 10000,
  pageNum: 1
})

onMounted(() => {
  localAxios()
    .get(`/board/${boardId}`)
    .then(function (response: any) {
      community.value = response.data
      hashtagModel.value = response.data.hashtag
      titleModel.value = response.data.title
      contentModel.value = response.data.contents
      console.log(response.data)
      setLikeState(response.data.isHeart)
      likeNumber.value = response.data.likeNumber
    })

  getReplyList(Number(boardId), params.value).then((res) => {
    replyList.value = res.data.replyList
  })
})

// 댓글 리스트 리로드
const reloadReplyList = () => {
  console.log('reloadReplyList')
  getReplyList(Number(boardId), params.value).then((res) => {
    replyList.value = res.data.replyList
    console.log(replyList.value)
  })
}

// 삭제

const deleteCommunity = () => {
  localAxios()
    .delete(`/board/${boardId}`)
    .then(function (response: any) {
      console.log(response.data)
      alert('삭제되었습니다.')
      location.href = '/community'
    })
}

// const modifyCommunity = () => {
//   localAxios()
//     .put(`/board/${boardId}`, {
//       title: titleModel.value,
//       contents: contentModel.value,
//       hashtag: hashtagModel.value
//     })
//     .then(function (response: any) {
//       console.log(response.data)
//       alert('수정되었습니다.')
//       location.href = '/community'
//     })
// }
</script>

<style scoped>
#reply {
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

.carouselImg {
  height: 10rem;
  margin: 0.5rem;
}

#carousel {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  .item.urlImg {
    flex: 0 0 auto;
  }
}

#carousel::-webkit-scrollbar {
  display: none;
}
</style>
