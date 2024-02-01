<template>
    <div class=out>
        <div class="reply">
            <div class="replyImg">
                <img src="/src/assets/images/image1.jpg" alt="reply" />
            </div>
            <div class="replyContent">
                <div class="replyName">
                    <p>이름</p>
                </div>
                <div class="replyText">
                    <p>내용</p>
                </div>
            </div>
        </div>    
        <div id="btn" >
            <MiniBtnComponent :title=title :color=color />
        </div>
        
    </div>
</template>

<script setup lang="ts">
import MiniBtnComponent from "@/components/MiniBtnComponent.vue";
import { ref } from 'vue'

const title = ref('send')
const color = ref('#C5C6EF')

</script>

<style scoped>
    .reply {
    box-sizing: border-box;
    padding: 18px;
    border-bottom: 1px solid #ebebeb;
    display: flex;
    align-items: center;
    position: relative;
    }

    .replyImg {
    border-radius: 50%;
    background-color: #ffffff;
    width: 4rem;
    height: 4rem;
    margin-right: 1rem;
    padding: 1rem;
    overflow: hidden;
    }

    .replyImg img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    }

    .replyContent {
    flex: 1;
    display: flex;
    flex-direction: column;
    }

    .replyName {
    font-size: 1.2rem; /* 작게 만듦 */
    }

    .replyText {
    overflow: hidden;
    text-overflow: ellipsis;
    margin-top: 1px;
    font-size: 1.2rem;
    line-height: 20px;
    }

    .btn {
    position: absolute;
    right: 0;
    bottom: 0;
    }
</style>