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
  <div
    style="
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin-top: 3rem;
    "
  >
    <h1>결제를 실패했습니다.</h1>
    <img src="../../assets/images/fail.png" alt="" />
  </div>
</template>

<style scoped></style>
