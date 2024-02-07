<template>
    <div id="cardWrapper">
        <div id="container">
            <div id="title">{{ props.board.boardTitle }}</div>
            <div id="thumbnailBox">
                <div id="thumbnail">
                    <img :src="props.board.thumbnail" alt="Thumbnail">
                </div>
            </div>
            <div id="rel">
                <img src="../../assets/icon/view.png" alt="view" width="20rem" height="20rem">
                <div>&nbsp{{ props.board.likeNumber }}&nbsp&nbsp</div>
                <img src="../../assets/icon/heart_filled.png" alt="view" width="20rem" height="20rem">
                <div>&nbsp{{ props.board.views }}</div>
            </div>
        </div>

    </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue';
const props = defineProps({board:Object});
console.log("incard",props);
</script>
<style scoped>
#cardWrapper{
    border-style: solid;
    border-width: 0 0 1px;
    border-color: gray;
    padding: 0.5rem 0 1rem;
}
#thumbnailBox{
    display: flex;
    justify-content: center;
    align-items: center;
}
#thumbnail{
  background-color: #ffffff;
  width: 90vw;
  padding: 1rem;
  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    }
}
#title{
    margin-left: 5vw;
    font-size: 1.5rem;
    font-weight: 600;
}
#rel{
    display: flex;
    justify-content: flex-end;
    margin-right: 5vw;
}

</style>