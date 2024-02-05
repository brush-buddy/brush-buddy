<template>
    <div id="cardWrapper">
        {{ props }}
    </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue';
const props = defineProps({board:Object});
console.log("incard",props);
</script>
<style scoped>
#cardWrapper{
    border-style: solid;
    border-width: 0 0 1px;
    padding: 1rem;
}
</style>