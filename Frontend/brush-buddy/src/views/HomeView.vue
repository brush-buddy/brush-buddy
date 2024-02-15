<script setup lang="ts">
import { useUserStore } from '../stores/user'
import { ref } from 'vue'
import HomeListView from './HomeListView.vue'

const login = () => {
  console.log('login')

  const redirect_uri = encodeURIComponent(`${import.meta.env.VITE_APP_KAKAO_REDIRECT_URL}`)
  const client_id = `${import.meta.env.VITE_APP_KAKAO_API_REST_KEY}` // TODO: 키 삽입
  const URI = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${client_id}&redirect_uri=${redirect_uri}`

  const params = {
    redirectUri: `${import.meta.env.VITE_APP_KAKAO_REDIRECT_URL}`
  }

  window.Kakao.Auth.authorize(params)
}
const active = ref(false)
const userStore = useUserStore()
import { isLogin } from '../api/user'

import { onMounted } from 'vue'
onMounted(async () => {
  await isLogin()
    .then(({ data }) => {
      active.value = data
    })
    .catch((error) => {
      console.log('isLogin error')
    })
})
</script>

<template>
  <HomeListView v-if="active" />

  <div v-else id="background">
    <div id="home">
      <img src="../assets/logo.png" alt="logo" id="logo" />

      <img src="../assets/kakao.png" alt="kakao" @click="login" />
    </div>
  </div>
</template>

<style scoped>
#background {
  background-image: url('../assets/background.png');
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
  /* align-items: center; */
}
#home {
  width: 18rem;
  height: 25rem;
  flex-shrink: 0;
  margin-top: 20vh;
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
  width: 10rem;
  height: 10rem;
}
</style>
