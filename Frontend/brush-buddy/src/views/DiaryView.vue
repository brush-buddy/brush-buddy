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

</script>

<style scoped>
/* .sticky {
  position: sticky;
  top: 0px;
  line-height: 4rem;
  background-color: #ffffff;
}
#tab {
  display: flex;
  justify-content: space-around;
  align-items: center;
} */
</style>
