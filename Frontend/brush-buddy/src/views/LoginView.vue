<script setup>
import {useRouter} from 'vue-router';
import { instance } from '../api/axios.ts'
const router = useRouter();

// 인가코드 받기
const uri = window.location.search.substring(1);
let params = new URLSearchParams(uri)

instance.get('/auth', {params: params})
.then(({data}) => console.log(data))
.catch(() => console.log("fail"));

// instance.defaults.headers.common['Authorization'] = params.get('at');

router.push('/');

</script>

<template>
    <div>
        
    </div>
</template>

<style scoped>

</style>