<script setup lang="ts">
import CommunityComponent from "@/components/CommunityComponent.vue";

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
