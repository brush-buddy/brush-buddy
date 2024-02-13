<script setup lang="ts">
const props = defineProps<{
  text?: string
  link?: string
  option?: string
}>()
</script>

<template>
  <div class="option-button">
    <img src="@/assets/icon/palette.svg" />
    <span class="inner-message"> {{ props.text }} </span>
  </div>
</template>

<style scoped>
.btn-container {
  justify-content: center;
}

.option-button {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 0.875rem 1.25rem;
  border-radius: 0.475rem;
  background: linear-gradient(180deg, #f6b4bf 0%, #dcc4ee 100%);
  gap: 0.625rem;
}
.inner-message {
  color: var(--system-white, #fff);
  font-family: 'Abril Fatface';
  font-size: 1.0625rem;
  font-style: normal;
  font-weight: 400;
  line-height: 1.375rem; /* 129.412% */
  letter-spacing: -0.02688rem;
  text-decoration: none;
}
</style>
