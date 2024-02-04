<script setup lang="ts">
import { defineComponent } from "vue";
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="bottom-navbar">
    <router-link to="/">
      <div class="navicon">
        <div class="navOption" style="display: flex; justify-content: center">
          <v-icon icon="mdi-home"></v-icon>
        </div>
        <p>홈</p>
      </div>
    </router-link>
    <router-link to="/search">
      <div class="navicon">
        <div class="navOption" style="display: flex; justify-content: center">
          <v-icon icon="mdi-camera"></v-icon>
        </div>
        <p>검색</p>
      </div>
    </router-link>
    <router-link to="/draft">
      <div class="navicon">
        <div class="navOption" style="display: flex; justify-content: center">
          <v-icon icon="mdi-camera-burst"></v-icon>
        </div>
        <p>도안 생성</p>
      </div>
    </router-link>
    <router-link to="/community">
      <div class="navicon">
        <div class="navOption" style="display: flex; justify-content: center">
          <v-icon icon="mdi-account-multiple"></v-icon>
        </div>
        <div><p>커뮤니티</p></div>
      </div>
    </router-link>
    <router-link to="/diary">
      <div class="navicon">
        <div class="navOption" style="display: flex; justify-content: center">
          <v-icon icon="mdi-circle-outline"></v-icon>
        </div>
        <p>그림기록</p>
      </div>
    </router-link>
  </div>
</template>

<style scoped>
.bottom-navbar {
  background-color: #fff;
  color: white;
  display: flex;
  justify-content: space-around;
  align-items: center;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100vw;
  height: 10vh; /* 적절한 높이로 조정하세요 */
}

.bottom-navbar a {
  text-decoration: none;
  color: rgb(0, 0, 0);
  padding: 2px;
}

.navicon {
  width: 4rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.navOption {
  width: 9rem;
}

p {
  font-size: 0.8rem;
  margin-top: 0.2rem;
  color: black;
}
svg {
  width: 1rem;
  height: 1.5rem;
  fill: white;
}
</style>
