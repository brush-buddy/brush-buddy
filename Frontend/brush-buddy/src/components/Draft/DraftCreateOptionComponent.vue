<script setup lang="ts">
import DraftCreateOptionButtonComponent from '@/components/Draft/DraftCreateOptionButtonComponent.vue';
import { onMounted, ref} from 'vue';
const fadein = ref(false);
const buttonFadein = ref(false);

const nextFadein = () => {
    buttonFadein.value = true;
}

onMounted(() => {
    fadein.value = true;
})
</script>

<template>
    <div class="page">
        <Transition @after-enter="nextFadein" name="fade">
        <div v-show="fadein" class="logo">
            <div class="icons-with-boxes">
                <img src="../assets/images/glass_boxes.svg"/>
                <img class="logo-img" src="@/assets/logo.png"/>
            </div>
        </div>
        </Transition>
        
        <div class="button-box">
            <Transition name="slide-fade">
            <div v-show="buttonFadein" class="button-group">
                <DraftCreateOptionButtonComponent link="/" class="button" text="대화로 이미지 만들기"/>
                <DraftCreateOptionButtonComponent link="/" class="button" text="내 갤러리에서 고르기" />
            </div>
        </Transition>
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
        padding: var(--app-homescreen-row-app-margin, 2.1875rem) 5.125rem;
    }

    .button-group{
        display: inline-flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 1.875rem;
    }

    .button{
        display: flex;
        width: 15.0625rem;
        align-items: flex-start;
    }

    .fade-enter-active , .fade-leave-active {
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

    .slide-fade-enter-from, .slide-fade-leave-to {
        transform: translateY(30px);
        opacity: 0;
}
    
</style>