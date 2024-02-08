<template>
  <div>
    <SearchComponent />
    <router-view />
  </div>
</template>

<script setup lang="ts">
import SearchComponent from '../components/common/SearchInputComponent.vue'
</script>

<style scoped></style>
