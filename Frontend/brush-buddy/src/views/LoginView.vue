<script setup>
import {useRouter} from 'vue-router';
import { instance } from '../api/axios.ts'
import { useUserStore } from '../stores/user.ts'
const router = useRouter();

// 인가코드 받기
const uri = window.location.search.substring(1); //인가코드 추출
let params = new URLSearchParams(uri)

const user = useUserStore();

instance.get('/auth', {params: params}) // 
.then(({data}) => {
    user.setAccessToken('Bearer ' + data) // 받은 인가코드에 대해서
    instance.defaults.headers.common['Authorization'] = 'Bearer ' + data;
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