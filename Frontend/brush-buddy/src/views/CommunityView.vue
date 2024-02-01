<template>
    <div>
        <p>Community</p>
        <HashTagComponent :hashtag=hashtag :color=color />
        <BigGoodComponent :color=goodcolor />
        <SmallGoodComponent :color=goodcolor />
        <EyeComponent :color=eyecolor />
    </div>
</template>

<script setup lang="ts">
import HashTagComponent from "../components/HashTagComponent.vue";
import BigGoodComponent from "../components/BigGoodComponent.vue";
import SmallGoodComponent from "../components/SmallGoodComponent.vue";
import EyeComponent from "../components/EyeComponent.vue";

import { ref } from 'vue'

const hashtag = ref('#fish')
const color = ref('#E1C461')
const goodcolor = ref('#E1C461')
const eyecolor = ref('#EE7575')
</script>

<style scoped>

</style>