<script setup lang="ts">
<<<<<<< HEAD
import { defineComponent } from "vue";
import communitycomponent from "@/components/CommunityComponent.vue";
const communityList = [];
=======
import CommunityComponent from "@/components/CommunityComponent.vue";

>>>>>>> 5639bc58f74ffe143c962ddfd0efe76a1eb4173c
</script>

<template>
  <div style = "width: 10rem; height: 13rem;">
  <CommunityComponent src="https://picsum.photos/300/400" :heartFilled = "true"></CommunityComponent>
</div>
<div style = "width: 10rem; height: 13rem;">
  <CommunityComponent src="https://avatars.githubusercontent.com/u/74080194?v=4"  :heartFilled = "false"></CommunityComponent>
</div>
  
</template>

<style scoped></style>
