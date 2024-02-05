
<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import NavigationComponent from "./components/NavigationComponent.vue";
</script>
,
<template>
  <div style="height: 10px; width: 100vw;"></div>
        <RouterView/>
        <NavigationComponent />
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
