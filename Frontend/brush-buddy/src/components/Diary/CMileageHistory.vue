<template>
    <div v-if="mileageHistory.length==0">
        내역이 없습니다.
    </div>
    <div v-else>
        <ul>
            <li v-for="item in mileageHistory" :key="item.mileageId">
                {{ item.mileageTimestamp.toString().substring(0,19) }}&nbsp;&nbsp;&nbsp;{{ item.mileageAmount }}&nbsp;&nbsp;&nbsp;{{ item.mileageAfter }}
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
import { getMileageHistory, type Mileage } from '@/api/mileage';
import { localAxios } from '../../api/axios'
import { ref, onMounted } from 'vue';
const mileageHistory = ref<Mileage[]>([]);
onMounted(() => { 
        console.log('start onMounted');
        localAxios().get('/mileage/history?listNum=10&pageNum=1')
            .then(response => {
                console.log(response.data['history'])
                return mileageHistory.value = response.data['history']
            })
            .catch(error => console.error(error))
    }
)

</script>

<style scoped>
</style>
