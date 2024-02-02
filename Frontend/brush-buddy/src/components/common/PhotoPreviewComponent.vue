<script setup lang="ts">
const props = defineProps(['photoSrc']);
</script>

<template>
    <div>
        <img class="photo-preview" :src="photoSrc">
    </div>
</template>

<style scoped>
.photo-preview{
    display: flex;
    width: 330px;
    height: fit-content;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 10px;
}
</style>