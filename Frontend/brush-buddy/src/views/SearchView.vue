<template>
  <div>
    <SearchComponent style="margin-bottom: 1rem" />
    <router-view />
  </div>
</template>

<script setup lang="ts">
import SearchComponent from '../components/common/SearchInputComponent.vue'
</script>

<style scoped></style>
