<script setup lang="ts">
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
onMounted(() => {
  setTimeout(() => {
    router.push('/diary')
  }, 3000)
})
</script>

<template>
  <div>결제 실패 되었습니다.</div>
</template>

<style scoped></style>
