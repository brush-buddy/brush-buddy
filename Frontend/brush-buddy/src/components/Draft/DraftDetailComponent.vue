<template>
    <div class = "rect">
        <div id = "category">
            <div id = "category1">
            <DraftCategoryButton :title=title1 :color=color1 />
            </div>
            <div id = "category2">
            <DraftCategoryButton :title=title2 :color=color2 />
            </div>
        </div>
        <div id = "draft">
            <BookmarkedDraftImgComponent/>
        </div>
    
        
    </div>
</template>

<script setup lang="ts">
import BookmarkedDraftImgComponent from "./DraftBookmarked.vue";
import DraftCategoryButton from "../DraftDetail/DraftCategoryButton.vue";
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

    .rect {
        position: relative;
        background: #FFFFFF;    
        width: 18rem;
        /* height: 18rem; */
        border-radius: 2rem;
        margin: 0 auto;
    }
</style>