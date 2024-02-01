<template>
    <svg width="50" height="20" viewBox="0 0 50 20" fill="none" xmlns="http://www.w3.org/2000/svg">
    <rect width="50" height="20" rx="8" :fill="props.color"/>
    <text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" fill="white" font-size="10">{{ props.title }}</text>
    </svg>

</template>
    
<script setup lang="ts">

const props = defineProps(["title","color"]);

</script>

<style scoped>

</style>