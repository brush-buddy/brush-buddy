<script setup lang="ts">
import { localAxios } from '../api/axios'
import { usePayStore } from '@/stores/pay'
// import { storeToRefs } from 'pinia';
import { ref } from 'vue'

const user = navigator.userAgent
// const price = ref<Number>(1000)
const purchase = (price: Number) => {
  localAxios()
    .post(`pay/makePayment?mileage=${price}`)
    .then((res) => {
      console.log('결제 요청 성공')
      usePayStore().setTid(res.data.body.tid)
      if (user.indexOf('iPhone') > -1) {
        window.location.href = res.data.body.ios_app_scheme
      } else if (user.indexOf('Android') > -1) {
        window.location.href = res.data.body.android_app_scheme
      } else {
        window.location.href = res.data.body.next_redirect_pc_url
      }
    })
}
</script>

<template>
  <div style="display: flex; flex-direction: column; align-items: center; margin-top: 2rem">
    <h1 style="margin-bottom: 1rem">마일리지 충전</h1>
    <template v-for="item in [100, 300, 500, 1000, 3000, 5000]" :key="item">
      <div class="buttonRow">
        <p>{{ item }} 마일리지</p>
        <v-btn @click="purchase(item)">{{ item }}</v-btn>
      </div>
    </template>
  </div>
</template>

<style scoped>
.buttonRow {
  display: flex;
  width: 90vw;
  justify-content: space-between;
  align-items: center;
  margin: 0 2rem;
  padding: 2rem 1rem;
  border-top: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
}
</style>
