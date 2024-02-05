
<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import NavigationComponent from "./components/NavigationComponent.vue";
</script>

<template>
  
    
        <RouterView/>
        <NavigationComponent />
  
  
</template>

<style scoped>
.Wrapper {
display: flex;
  flex-direction: column;
  height: 100vh;
}
.contentWrapper {
  flex : 1;

}

</style>
