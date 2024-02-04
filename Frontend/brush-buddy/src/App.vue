
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
  <div class="Wrapper">
    <div class="contentWrapper">
        <RouterView/>
        <NavigationComponent />
    </div>
  </div>
</template>

<style scoped>
NavigationComponent {
  /* width: 375px;
  height: 50px;
  flex-shrink: 0;
  z-index: 999; */
}
.Wrapper {
  display: flex;
  flex-direction: column;
  /* height: 100vh; */
}
.contentWrapper {
  flex : 1;

}

</style>
