<script setup lang="ts">
import { localAxios } from '../api/axios'
import { usePayStore } from '../stores/pay'
// import { storeToRefs } from 'pinia';
import { ref } from 'vue'
const { setTid } = usePayStore()
const user = navigator.userAgent
const colorItem = ref<any>({
  100: '#efe5e4',
  300: '#ddb4d4',
  500: '#b765a8',
  1000: '#ddb5d0',
  3000: '#61478d',
  5000: '#8d3195'
})
const purchase = (price: Number) => {
  localAxios()
    .post(`pay/makePayment?mileage=${price}`)
    .then((res) => {
      console.log('결제 요청 성공')
      setTid(res.data.body.tid)
      setTimeout(() => {}, 10000)
      if (user.indexOf('iPhone') > -1 || user.indexOf('Android') > -1) {
        window.location.href = res.data.body.next_redirect_mobile_url
      } else {
        window.location.href = res.data.body.next_redirect_pc_url
      }
    })
}
</script>

<template>
  <div style="display: flex; flex-direction: column; align-items: center; margin-top: 2rem">
    <div style="display: flex; justify-content: flex-start; width: 90vw; margin-bottom: 1rem">
      <h1 style="margin-bottom: 1rem">마일리지 충전</h1>
    </div>
    <template v-for="item in [100, 300, 500, 1000, 3000, 5000]" :key="item">
      <div class="buttonRow">
        <p>{{ item }} 마일리지</p>
        <v-btn @click="purchase(item)" :color="colorItem[item]">{{ item }}</v-btn>
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
