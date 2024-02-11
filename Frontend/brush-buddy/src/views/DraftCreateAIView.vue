<script setup lang="ts">
import { onMounted, ref } from 'vue'
import PromptInputComponent from '../components/common/PromptInputComponent.vue'
const fadein = ref(false)

onMounted(() => {
  fadein.value = true
})

const buttonFadein = ref(true)

const nextFadein = () => {
  buttonFadein.value = true
}
</script>

<template>
  <div class="page">
    <Transition @after-enter="nextFadein" name="fade">
      <div v-show="fadein" class="logo">
        <img src="@/assets/images/ai_prompt_logo.svg" />
      </div>
    </Transition>

    <div class="button-box">
      <Transition name="slide-fade">
        <div v-show="buttonFadein" class="button-group">
          <PromptInputComponent />
        </div>
      </Transition>
    </div>
  </div>
</template>

<style scoped>
.page {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 5rem;
}
.logo {
  justify-self: center;
}
.icons-with-boxes {
  width: 12.125rem;
  height: 12.3125rem;
  flex-shrink: 0;
  position: relative;
}

.button-group {
  display: inline-flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1.875rem;
}

.button {
  display: flex;
  width: 15.0625rem;
  align-items: flex-start;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.7s ease;
}

.fade-enter-from, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}

.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateY(30px);
  opacity: 0;
}
</style>
