<template>
    <div class = "rect">
        <div id = "category">
            <div id = "category1">
            <CategoryBtnComponent/>
            </div>
            <div id = "category2">
            <CategoryBtnComponent/>
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
import PaletteComponent from "../components/PaletteComponent.vue";
import CategoryBtnComponent from "../components/CategoryBtnComponent.vue";



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