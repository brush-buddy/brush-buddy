<template>
    <div>
        <svg width="50" height="20" viewBox="0 0 50 20" fill="none" xmlns="http://www.w3.org/2000/svg">
        <rect width="50" height="20" rx="8" :fill="props.color"/>
        <rect x="2.5" y="2.5" width="45" height="15" rx="5" fill="white"/> <!-- 빈 박스 추가 -->
        <text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" fill="black" font-size="9">{{ props.hashtag }}</text>
        </svg>
    </div>
</template>

<script setup lang="ts">
import {  defineProps } from "vue";

const props = defineProps(["hashtag","color"]);


</script>

<style scoped>
</style>