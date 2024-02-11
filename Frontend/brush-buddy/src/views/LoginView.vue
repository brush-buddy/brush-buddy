<script setup lang="ts">
import {useRouter} from 'vue-router';
import { useUserStore } from '../stores/user'
import { login } from '../api/user'
const router = useRouter();

// 인가코드 받기
const uri = window.location.search.substring(1); //인가코드 추출
const params = new URLSearchParams(uri)

// 로그인 수행
login(params, 
        (data:string) => {
            const user = useUserStore();
            user.setAccessToken('Bearer ' + data) // 받은 인가코드에 대해서
            router.push('/');
        }, 
        (error:any) => {
            console.log("fail login");
        }
    );
</script>

<template>
    <div>
        
    </div>
</template>

<style scoped>

</style>