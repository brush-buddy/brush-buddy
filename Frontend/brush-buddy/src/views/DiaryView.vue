<template>
  <div>
    <div>
      <CProfile />
    </div>
      <CListTab/>
      <router-view/>
  </div>
</template>

<script setup lang="ts">
import CProfile from "../components/Diary/CProfile.vue";
import CListTab from "../components/Diary/CListTab.vue";
import { RouterView } from 'vue-router'
import { ref } from 'vue'
import axios from 'axios';
import { provide } from 'vue'
const heartList = ref([]);

const provideHeartList = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/mypage/heart/list?listNum=3&pageNum=1', {
      headers: {
        'Content-Type': 'application/json; charset=utf-8'
      }
    });

    console.log("최초호출");
    console.log(response.data);

    heartList.value = response.data.boards;
  } catch (error) {
    console.error('Error fetching heart list:', error);
  }
};

provide('heartList', heartList);

// Call the provideHeartList method when the component is mounted
provideHeartList();
</script>

<style scoped>

</style>
