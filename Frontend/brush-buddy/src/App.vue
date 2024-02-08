<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import NavigationComponent from "./components/NavigationComponent.vue";

const kakaoLogin = () => {
      const params = {
        redirectUri:'http://localhost:5173/',
      }
      window.Kakao.Auth.authorize(params)
      
    }
</script>

<template>
  <RouterView />
  <!-- <DraftPipoMakeView pipo-url="https://picsum.photos/200/300?random=1" :pipo-palette="palette" :isAI="isAI" :prompt="prompt"/> -->
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
