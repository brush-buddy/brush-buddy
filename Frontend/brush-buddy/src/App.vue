
<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import NavigationComponent from "./components/NavigationComponent.vue";
import DraftPipoMakeView from "./views/DraftPipoMakeView.vue";
import {ref} from 'vue';
const palette = ref({"1" : "#FFFFF", "2":"#882abd", "3":"#3e4029", "4":"#26a31f", "5":"#fbbdb8", "6":"#aa1280", "7":"#f2a991", "8":"#8c0a44", "9":"#7da717"})
</script>

<template>
  
    
        <!-- <RouterView/> -->
        <DraftPipoMakeView pipo-url="https://picsum.photos/200/300?random=1" :pipo-palette="palette"/>
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
