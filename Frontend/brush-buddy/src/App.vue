
<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import NavigationComponent from "./components/NavigationComponent.vue";
import CommunityListview from "./views/CommunityListview.vue";
</script>

<template>
  <div class="Wrapper">
    <div class="contentWrapper">
        <RouterView/>
        <NavigationComponent />

    </div>
  </div>



</template>

<style scoped>
NavigationComponent {
  /* width: 375px;
  height: 50px;
  flex-shrink: 0;
  z-index: 999; */
}
.Wrapper {
display: flex;
  flex-direction: column;
  height: 100vh;
}
.contentWrapper {
  flex : 1;

}
</style>
