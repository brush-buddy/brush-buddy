<script setup lang="ts">
import { ref } from 'vue';
import axios from "axios";
const prompt = ref<string>("");
const makeImage = () => {
    console.log(prompt.value);
    axios.post("http://localhost:8000/", {"propmt": prompt.value})
    .then((res) => {
        console.log(res);
    })

}

    //-그려줘라고 입력하면 그림을 만들어드려요
</script>

<template>
   
    <div class="input-box-container">
        <!-- <div class="input-container"> -->
            <input type="text" v-model = "prompt" class="search-input-box" placeholder="-그려줘라고 입력하면 그림을 만들어드려요" style="width: 18rem;" v-on:keyup.enter="makeImage()"/>
            <v-btn @click = "makeImage()" icon="mdi-arrow-up" size="small" color = "success"></v-btn>
        <!-- </div> -->
    </div>
   
</template>

    <style scoped>
    .input-box-container{
        display: flex;
        width: 21.5rem;
        height: 2.125rem;
        flex-direction: row;
        align-items: center;
        flex-shrink: 0;
        justify-content: space-around;

    }

    .input-box-sub-container{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        flex: 1 0 0;
        align-self: stretch;
        
    }

    /* .input-container{
        display: flex;
        height: var(--app-safari-address-bar-margin, 2.25rem);
        padding-left: 0.375rem;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        align-self: stretch;
        border-radius: 0.625rem;
        background: var(--system-grey4, #D2D2D7);
    } */

    .search-input-box{
        height: var(--app-safari-address-bar-margin, 2.25rem);
        padding-left: 0.375rem;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        align-self: stretch;
        border-radius: 0.625rem;
        opacity: 0.5;
        background: var(--system-grey4, #D2D2D7);

        color: var(--label-primary, #000);
        text-align: center;
        font-family: ABeeZee;
        font-size: 1.0625rem;
        font-style: normal;
        font-weight: 400;
        line-height: 1.375rem; /* 129.412% */
        letter-spacing: -0.02688rem;
    }
</style>