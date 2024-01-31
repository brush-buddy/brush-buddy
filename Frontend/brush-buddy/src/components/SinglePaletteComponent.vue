<template>
    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"   fill="none">
    <path d="M39.5 20C39.5 30.7696 30.7696 39.5 20 39.5C9.23045 39.5 0.5 30.7696 0.5 20C0.5 9.23045 9.23045 0.5 20 0.5C30.7696 0.5 39.5 9.23045 39.5 20Z" :fill="props.color" stroke="white"/>
    </svg>
</template>

<script setup lang="ts">

import { defineProps } from "vue";

const props = defineProps(["color"]);

</script>

<style scoped>

</style>