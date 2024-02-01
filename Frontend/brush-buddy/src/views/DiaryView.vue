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
        <!-- <div v-for="(item, index) for heartList.boards" :key="index">
            <CCard :board = "item"/> 
        </div> -->
    </div>
</template>

<script setup lang="ts">
import CProfile from "../components/Diary/CProfile.vue"
import CCard from "../components/Diary/CCard.vue"
import axios from "axios";
import { ref, toRaw } from "vue";

interface HeartList{
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
const listNum = ref(3);
const pageNum = ref(1);
// const heartList = ref<HeartList>({
//   boards: 
//   {
//         boardId: 0,
//         boardTitle: "",
//         createdAt: "",
//         likeNumber: 0,
//         thumbnail: "",
//         views: 0

//     }
// ,
//   pageNum: 0,
//   length: 0,
//   totalPage: 0
// });
const heartList = ref([]);

const getHeartList = async (): Promise<HeartList> => {
    console.log("getHeartList called");
    const heartListGet = await axios.get<HeartList>(`http://localhost:8080/v1/api/mypage/heart/list?listNum=${listNum.value}&pageNum=${pageNum.value}`)
    console.log(heartListGet.data);
    heartList.value = heartListGet.data;
    console.log("dkdkdkdk");
    console.log(heartList.value);
    return heartListGet;
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