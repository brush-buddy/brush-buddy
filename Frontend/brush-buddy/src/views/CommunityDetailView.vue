<template>
  <div>
    <p class="communityTitle" default="blanktitle" >{{ community['title'] }}</p>
    <DraftDetailComponent />
    <div>
      <v-carousel hide-delimiters>
        <v-carousel-item
          v-for="(item, i) in im"
          :key="i"
          :src="item.imgUrl"
          cover
        ></v-carousel-item>
      </v-carousel>
    </div>
    <img :src="community" alt="" />
    <p>{{ community.contents }}</p>

    <p>{{ community }}</p>
    <div id="reply">
      <ReplyComponent />
    </div>
    <div class="blank">
    </div>
  </div>
  
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, inject, ref } from 'vue'
import ReplyComponent from '../components/DraftDetail/CReply.vue'
import axios from 'axios'
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

const im = ref<any>({ order: '-1', imageUrl: '' })
console.log('hi')
console.log(im.value)

console.log(route.params)
onMounted(() => {
  axios({
    baseURL: '',
    method: 'get',
    url: 'http://localhost:8080/api/v1/board/' + boardId, // URL에 한글이 포함될 경우 인코딩
    headers: {
      'Content-Type': 'application/json; charset=utf-8'
    }
  }).then(function (response: any) {
    console.log(response.data)
    community.value = response.data
  })
})
</script>

<style scoped>
#reply {
  margin-bottom: 6rem;
}

.blank{
  margin-bottom: 6rem;
}

</style>
