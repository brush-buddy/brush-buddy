<template>
    <svg width="17" height="13" viewBox="0 0 17 13" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M8.5 0.5C4.71988 0.5 1.95216 2.43228 0 6.5C1.95216 10.5677 4.71988 12.5 8.5 12.5C12.2825 12.5 15.0502 10.5677 17 6.5C15.0478 2.43228 12.2801 0.5 8.5 0.5ZM8.40626 10.6575C6.12834 10.6575 4.28164 8.79606 4.28164 6.5C4.28164 4.20394 6.12834 2.34252 8.40626 2.34252C10.6842 2.34252 12.5309 4.20394 12.5309 6.5C12.5309 8.79606 10.6842 10.6575 8.40626 10.6575Z" :fill="props.color"/>
    </svg>
</template>

<script setup lang="ts">
import {  defineProps } from "vue";

const props = defineProps(["color"]);

</script>

<style scoped>

</style>