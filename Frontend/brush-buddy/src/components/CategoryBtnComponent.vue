<template>
    <svg width="50" height="20" viewBox="0 0 50 20" fill="none" xmlns="http://www.w3.org/2000/svg">
    <rect width="50" height="20" rx="8" fill="#FF7575"/>
    <text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" fill="white" font-size="12">물고기</text>
    </svg>
</template>
    
<script setup >
import {onMounted, ref} from 'vue'

const props = defineProps(['buttonText'])
const emits = defineEmits(['receive'])

const vo = ref('')

onMounted(() => {
    vo.value = props.buttonText // vo에 props로 받은 buttonText를 넣어줌.
})

</script>

<style scoped>

</style>