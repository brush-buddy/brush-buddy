<template>
  <!-- <div style="display: flex; justify-content: space-around;">
    <div>
      1번디브
    <div v-for="(card, i) in boardThumbnailDataFirst" :key="i">
        <CommunityComponent :boardThumbnail="card"/>
    </div>
    </div>
    <div>
      2번디브
    <div v-for="(card, i) in boardThumbnailDataSecond" :key="i">
        <CommunityComponent :boardThumbnail="card"/>
    </div>
  </div>
  </div> -->
  <v-infinite-scroll :height="300" :items="items" :onLoad="load">
    <div v-for="(item, index) in items" :key="item">
        <CommunityComponent :boardThumbnail="item"/>
    </div>
    <!-- <template v-for="(item, index) in items" :key="item">
        <CCard :board="item"/>
    </template> -->
</v-infinite-scroll>
</template>

<script setup lang="ts">
import { ref, onMounted} from 'vue';
import axios from "axios";
import CommunityComponent from '../components/CommunityComponent.vue';
import type{ BoardListRes } from '../api/type.ts';

// const boardThumbnailDataFirst = ref<BoardThumbnail[]>([{
//   boardId: '1',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/100/200',
//   likeNumber: 100,
//   views: 500
// },{
//   boardId: '2',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/200/300',
//   likeNumber: 100,
//   views: 300
// },{
//   boardId: '1',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/400/200',
//   likeNumber: 100,
//   views: 500
// },{
//   boardId: '2',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/200/300',
//   likeNumber: 100,
//   views: 300
// }]);

// const boardThumbnailDataSecond = ref<BoardThumbnail[]>([{
//   boardId: '1',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/300/100',
//   likeNumber: 100,
//   views: 500
// },{
//   boardId: '2',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/300/300',
//   likeNumber: 100,
//   views: 300
// },{
//   boardId: '1',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/200/100',
//   likeNumber: 300,
//   views: 500
// },{
//   boardId: '2',
//   boardTitle: 'Example Board',
//   thumbnail: 'https://picsum.photos/200/400',
//   likeNumber: 100,
//   views: 300
// }]);




// onMounted(() => {
//   axios({
//     baseURL: '',
//     method: 'get',
//     url: 'http://localhost:8080/api/v1/board/list', // URL에 한글이 포함될 경우 인코딩
//     headers: {
//       'Content-Type': 'application/json; charset=utf-8'
//     }
//   }).then(function (response : any) {
//     console.log(response.data)
    
//     // boardThumbnailData.value = response.data;
//   })
// })

// community board list 불러오기
const listNum = ref(10);
const pageNum = ref(0);
const heartList = ref([]);
const totalPage = ref(0);
const getBoardList = async (page: number): Promise<BoardListRes> => {
  console.log("getBoardList called");
  try {
    
    const boardListGet = await axios({
      method: "get",
      url: `http://localhost:8080/api/v1/board/list?listNum=${listNum.value}&pageNum=${page}`,
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        // Authorization: localStorage.getItem("token")
      },
    });
    heartList.value = boardListGet.data.boards;
    totalPage.value = boardListGet.data.totalPage;
    return boardListGet.data.boards;
  } catch (err) {
    console.log("api 호출 중 오류 발생", err);
    return Promise.reject(err);
  }
};

// 무한 스크롤 구현 
const items = ref<number[]>([])

const api = async () => {
    pageNum.value = pageNum.value + 1;
    console.log("pageNum ", pageNum.value);
    console.log("listNum ", listNum.value);
  return new Promise<number>(resolve => {
    setTimeout(() => {
      resolve(pageNum.value);
    }, 1000)
  })
}

const load = async ({ done }: { done: (status: string) => void }) => {
    try{
            // Perform API call => pageNum update
            const res = await api()
            const resList = await getBoardList(res)
            items.value.push(...resList)
            console.log("items: ", items.value);

        if(totalPage.value > pageNum.value){
            done('ok')
        }else{
            done('empty')
        }
    } catch(error){
        console.error("Error during load:", error);
        done('error')
    }

}


</script>
