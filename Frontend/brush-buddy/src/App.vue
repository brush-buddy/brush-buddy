<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import NavigationComponent from "./components/NavigationComponent.vue";
import CommunityListview from "./views/CommunityListview.vue";
</script>

<template>
  <header>
    <div class="wrapper"></div>
  </header>

  <body>
    <CommunityListview></CommunityListview>
    <NavigationComponent></NavigationComponent>
  </body>
</template>

<style scoped>
NavigationComponent {
  width: 375px;
  height: 50px;
  flex-shrink: 0;
}
</style>
