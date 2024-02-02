<template>
    <div>
        <div>
            <CProfile />
        </div>
    <div class="sticky">
        <div id="tab">
        <div class="tabButton">만든 도안</div>
        <div class="tabButton">구매 도안</div>
        <div class="tabButton">북마크 도안</div>
        <div class="tabButton" @click="getHeartList">찜한 글</div>
    </div>

    </div>
        <template v-for="(board, i) in heartList" :key="i">
            <CCard :board="board"/>
        </template>
    </div>
</template>

<script setup lang="ts">
import CProfile from "../components/Diary/CProfile.vue"
import CCard from "../components/Diary/CCard.vue"
import axios from "axios";
import { ref, onMounted } from "vue";
// import InfiniteLoading from "infinite-loading-vue3-ts";

interface HeartListRes{
    boards: {
        boardId: number,
        boardTitle: string,
        createdAt: string,
        likeNumber: number,
        thumbnail: string,
        views: number
    };
    pageNum: number;
    length: number;
    totalPage: number;
}
interface HeartList {
    boardId: number,
    boardTitle: string,
    createdAt: string,
    likeNumber: number,
    thumbnail: string,
    views: number
}
const listNum = ref(3);
const pageNum = ref(1);
const heartList = ref([]);
const getHeartList = async (): Promise<HeartListRes> => {
    console.log("getHeartList called");
    try{
        // const heartListGet = await axios.get<HeartListRes>(`http://localhost:8080/v1/api/mypage/heart/list?listNum=${listNum.value}&pageNum=${pageNum.value}`)
        const heartListGet = await axios({
            method:"get",
            url: `http://localhost:8080/v1/api/mypage/heart/list?listNum=${listNum.value}&pageNum=${pageNum.value}`,
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                // Authorization: localStorage.getItem("token")
            },
        });
        heartList.value = heartListGet.data.boards;
        return heartListGet.data.boards;
    } catch(err){
        console.log("api 호출 중 오류 발생",err);
        return Promise.reject(err);
    }
};



</script>

<style scoped>

.sticky{
    position:sticky;
    top:0px;
    line-height: 4rem;
    background-color: #ffffff;
}
#tab{
display: flex;
justify-content: space-around;
align-items: center;
}

</style>