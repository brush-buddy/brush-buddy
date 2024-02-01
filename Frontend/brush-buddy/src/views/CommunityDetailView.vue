<template>
    <div>
        
        <p class = "communityTitle">{{community.title}}</p>
        
        <div>

        </div>
        <!-- <img :src="community.photo[0]" alt=""> -->
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
const community = ref<any>(null)
console.log(route.params)
onMounted(() => {
  axios({
    baseURL: '',
    method: 'get',
    url: 'http://localhost:8080/v1/api/board/' + boardId, // URL에 한글이 포함될 경우 인코딩
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