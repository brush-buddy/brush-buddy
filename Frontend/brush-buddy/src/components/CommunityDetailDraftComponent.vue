<template>
    <div class = "rect">
        <div id = "category">
            <div id = "category1">
            <CategoryBtnComponent :title=title1 :color=color1 />
            </div>
            <div id = "category2">
            <CategoryBtnComponent :title=title2 :color=color2 />
            </div>
        </div>
        <div id = "draft">
            <BookmarkedDraftImgComponent/>
        </div>
        <div id = "palette">
            <PaletteComponent/>
        </div>
        
    </div>
</template>

<script setup lang="ts">
import BookmarkedDraftImgComponent from "../components/BookmarkedDraftImgComponent.vue";
import PaletteComponent from "./EclipsePaletteComponent.vue";
import CategoryBtnComponent from "../components/CategoryBtnComponent.vue";
import { ref } from 'vue'

const title1 = ref('물고기')
const color1 = ref('#FFB6C1')

const title2 = ref('바다')
const color2 = ref('#FF7575')

</script>

<style scoped>
    #category {
        display: flex;
        flex-direction: row;
        justify-content:align-start;
        position : relative;
        left: 0.3rem;
        top: 0;
        z-index: 2;
        margin-bottom: 0.3rem;
    }
    #category1{
        margin-right: 0.5rem;
    }
    #draft {
        position: relative;
        top: 0;
        margin-bottom : -2rem;
        z-index: 1;
    }
    #palette {
        position: relative;
        margin-bottom : 0.5rem;
        z-index: 2;
    }
    .rect {
        position: relative;
        background: #FFFFFF;    
        width: 18rem;
        height: 18rem;
        border-radius: 2rem;
        margin: 0 auto;
    }
</style>