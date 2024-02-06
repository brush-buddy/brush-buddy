<script setup lang="ts">
import {useRouter} from 'vue-router';
import { localAxios } from '../api/axios'
import { useUserStore } from '../stores/user'
const router = useRouter();

// 인가코드 받기
const uri = window.location.search.substring(1); //인가코드 추출
let params = new URLSearchParams(uri)

const user = useUserStore();

localAxios().get('/auth', {params: params}) // 
.then(({data}) => {
    user.setAccessToken('Bearer ' + data) // 받은 인가코드에 대해서
    router.push('/');
})
.catch(() => {console.log("fail")});

</script>

<template>
    <div>
        
    </div>
</template>

<style scoped>

</style>