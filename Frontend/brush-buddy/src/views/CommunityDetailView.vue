<template>
    <div>
        
        <p class = "communityTitle">{{community["title"]}}</p>
        
        <div>
            
        </div>
        <img :src="community" alt="">
        <p>{{community.contents}}</p>

        <p>{{community}}</p>
    </div>
</template>


<script setup lang="ts">
import { useRoute } from 'vue-router';
import { onMounted, inject, ref } from 'vue';
import axios from 'axios';
const route = useRoute()
const boardId = route.params.id
const community = ref<any>({ "boardId": -1, 
        "title": " ", "contents": " ", "thumbnail": " ", "photo": [], "draftId": -1, "likeNumber": 0, "views": 0, "hashtag": [], "createdAt": "-" })
console.log(route.params)
onMounted(() => {
  axios({
    baseURL: '',
    method: 'get',
    url: 'http://localhost:8080/api/v1/board/' + boardId, // URL에 한글이 포함될 경우 인코딩
    headers: {
      'Content-Type': 'application/json; charset=utf-8'
    }
  }).then(function (response : any) {
    console.log(response.data)
    community.value = response.data;
  })
})

</script>

<style scoped>

</style>