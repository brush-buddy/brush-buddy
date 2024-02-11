<template>
     <v-textarea label="Content" variant="underlined" rows="1"
     v-model="content"
        @keydown="resize"
        @keyup="resize"></v-textarea>
  </template>
  
  <script setup lang="ts">
  import { ref, defineEmits } from 'vue';
  const content = ref('');
  const emit = defineEmits(['updateContent']);
  
  const resize = (event: KeyboardEvent) => {
    const obj = event.target as HTMLTextAreaElement;
    obj.style.height = '1px';
    obj.style.height = `${12 + obj.scrollHeight}px`;
    obj.style.fontSize = '16px';
    emit('updateContent', content.value);
  };
  

  </script>
  
  <style>

  </style>
  