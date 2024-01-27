<script setup lang="ts">
import NavigationComponent from "@/components/NavigationComponent.vue";
</script>

<template>
  <div id="background">
    <div id="home">
      <img src="@/assets/logo.png" alt="logo" id="logo" />

      <img src="@/assets/kakao.png" alt="kakao" />
    </div>
  </div>
</template>

<style scoped>
#background {
  background-image: url("../assets/background.png");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  height: 100vh;
  width: 100vw;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;

  display: flex;
  justify-content: center;
  align-items: center;
}
#home {
  width: 292px;
  height: 486px;
  flex-shrink: 0;

  border-radius: 20px;
  border: 1px solid #fff;
  background: linear-gradient(
    166deg,
    rgba(255, 255, 255, 0.8) 6.85%,
    rgba(255, 255, 255, 0.24) 95.19%
  );
  box-shadow: 0px 4px 34px 0px rgba(0, 0, 0, 0.25);
  backdrop-filter: blur(5px);
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

#logo {
  width: 200px;
  height: 200px;
}
</style>
