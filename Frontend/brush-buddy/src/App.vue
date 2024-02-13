<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import NavigationComponent from './components/NavigationComponent.vue'

const kakaoLogin = () => {
  const params = {
    redirectUri: 'http://localhost:5173/'
  }
  window.Kakao.Auth.authorize(params)
}
</script>

<template>
  <div style="height: 10px; width: 100vw"></div>
  <RouterView />
  <NavigationComponent />
</template>

<style scoped>
.Wrapper {
  display: flex;
  flex-direction: column;
  /* height: 100vh; */
}
.contentWrapper {
  flex: 1;
}
</style>
