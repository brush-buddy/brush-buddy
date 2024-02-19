<template>
  <div
    style="
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin-top: 3rem;
    "
    v-if="successStatus"
  >
    <h1>결제 완료 되었습니다.</h1>
    <img src="../../assets/images/success.png" style="width: 10rem" alt="" />
  </div>
  <div
    style="
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin-top: 3rem;
    "
    v-if="!successStatus"
  >
    <h1>결제 취소 되었습니다.</h1>
    <img src="../../assets/images/fail.png" alt="" />
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue'
import { onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { localAxios } from '../../api/axios'
import { usePayStore } from '../../stores/pay'
import { storeToRefs } from 'pinia'
const { tid } = storeToRefs(usePayStore())
const route = useRoute()
const router = useRouter()
console.log(route.params.status)
const status = ref<String>('')
const searchParams = new URLSearchParams(location.search)
const pg_token = searchParams.get('pg_token')
for (const param of searchParams) {
  console.log(param)
}
const successStatus = ref(true)
onMounted(() => {
  console.log(tid.value)
  localAxios()
    .post(`pay/complete?pg_token=${pg_token}&tid=${tid.value}`)
    .then((res) => {
      console.log(res.data)
      successStatus.value = true
      status.value = '결제가 완료되었습니다.'
      setTimeout(() => {
        router.push('/diary')
      }, 3000)
    })
    .catch((err) => {
      console.log(err)
      successStatus.value = false
      status.value = '결제가 취소되었습니다.'
      setTimeout(() => {
        router.push('/diary')
      }, 3000)
    })
})
</script>

<style scoped lang="scss"></style>
