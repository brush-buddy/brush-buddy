<template>
  <div>{{ status }}</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { localAxios } from '../../api/axios'
import { usePayStore } from '../../stores/pay'
import { storeToRefs } from 'pinia'
const { tid } = storeToRefs(usePayStore())
const route = useRoute()
console.log(route.params.status)
const status = ref<String>('')
const searchParams = new URLSearchParams(location.search)
const pg_token = searchParams.get('pg_token')
for (const param of searchParams) {
  console.log(param)
}
onMounted(() => {
  console.log(tid.value)
  localAxios()
    .post(`pay/complete?pg_token=${pg_token}&tid=${tid.value}`)
    .then((res) => {
      console.log(res.data)
      status.value = '결제가 완료되었습니다.'
    })
    .catch((err) => {
      console.log(err)
      status.value = '결제가 취소되었습니다.'
    })
})
</script>

<style scoped lang="scss"></style>
