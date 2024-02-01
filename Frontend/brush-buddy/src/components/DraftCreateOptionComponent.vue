<script setup lang="ts">
import DraftCreateOptionButtonComponent from '@/components/DraftCreateOptionButtonComponent.vue';


</script>

<template>
    <div class="page">
        <div class="logo">
            <div class="icons-with-boxes">
                <img src="@/assets/images/glass_boxes.svg"/>
                <img class="logo-img" src="@/assets/logo.png"/>
            </div>
        </div>
        <div class="button-box">
            <DraftCreateOptionButtonComponent class="button" text="대화로 이미지 만들기"/>
            <DraftCreateOptionButtonComponent class="button" text="내 갤러리에서 고르기" />
        </div>
    </div>
    
</template>

<style scoped>
    .page{
        height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 5rem;
    }
    .logo{
        justify-self: center;
    }
    .logo-img{
        display: flex;
        width: 7.125rem;
        height: 7.125rem;
        justify-content: center;
        align-items: center;
        flex-shrink: 0;

        position: absolute;
        bottom: 1.7rem ;
        left: 1.7rem;
    }

    .icons-with-boxes{
        width: 12.125rem;
        height: 12.3125rem;
        flex-shrink: 0;
        position: relative;
    }

    .button-box{
        display: inline-flex;
        padding: var(--app-homescreen-row-app-margin, 2.1875rem) 5.125rem;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 1.875rem;
    }

    .button{
        display: flex;
        width: 13.0625rem;
        /* height: 1rem; */
        /* flex-direction: column; */
        align-items: flex-start;
    }
    
</style>