<template>
    <div class="fixed_width">
      <div class=palette>
        <SinglePaletteComponent :color=color1 />
      </div>
      <div class=palette>
        <SinglePaletteComponent :color=color2 />
      </div>
      <div class=palette>
        <SinglePaletteComponent :color=color3 />
      </div>
      <div class=palette>
        <SinglePaletteComponent :color=color4 />
      </div>
      <div class=palette>
        <SinglePaletteComponent :color=color5 />
      </div>
    </div>
</template>

<script setup lang="ts">

import SinglePaletteComponent from "../common/SinglePaletteComponent.vue";
import { ref } from 'vue'

const color1 = ref('#46C2C8')
const color2 = ref('#E1C461')
const color3 = ref('#B7771F')
const color4 = ref('#2454A5')
const color5 = ref('#EEECDE')


</script>

<style scoped>
.fixed_width {
  display: inline-flex;
  align-items: flex-end;
}

.palette{
  margin-right: -6px;
}
</style>