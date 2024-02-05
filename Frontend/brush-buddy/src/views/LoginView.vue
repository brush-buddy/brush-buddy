<script setup>
import {useRouter} from 'vue-router';
import { instance } from '../api/axios.ts'
const router = useRouter();

const uri = window.location.search.substring(1);
const params = new URLSearchParams(uri)

console.log(params.get('at'))
console.log(params.get('rt'))

// const code = new URLSearchParams(uri).get("code");
// console.log(uri);
// const params = {
//     "code" : code
// }
// instance.get('/auth', {params: params})
// .then(({data}) => console.log(data))
// .catch(() => console.log("fail"));

instance.defaults.headers.common['Authorization'] = params.get('at');

router.push('/');

</script>

<template>
    <div>
        
    </div>
</template>

<style scoped>

</style>