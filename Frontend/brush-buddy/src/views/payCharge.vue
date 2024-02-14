<script setup lang="ts">
import { localAxios } from '../api/axios'
import { usePayStore } from '@/stores/pay'
// import { storeToRefs } from 'pinia';
import { ref } from 'vue'

const user = navigator.userAgent
const price = ref<Number>(1000)
const purchase = () => {
  localAxios()
    .post(`pay/makePayment?mileage=${price.value}`)
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
  <div><v-btn @click="purchase()"></v-btn></div>
</template>

<style scoped></style>
