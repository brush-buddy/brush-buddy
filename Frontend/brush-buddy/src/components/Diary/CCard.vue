<template>
  <div id="cardWrapper">
    <router-link :to="{ name: 'boardDetail', params: { id: board.boardId } }">
      <div id="title">{{ board.boardTitle }}</div>
      <div id="thumbnailBox">
        <div id="thumbnail">
          <img :src="board.thumbnail" alt="Thumbnail" />
        </div>
      </div>
      <div id="rel">
        <img src="../../assets/icon/view.png" alt="view" width="20rem" height="20rem" />
        <div>&nbsp;{{ board.views }}&nbsp;&nbsp;</div>
        <img src="../../assets/icon/heart_filled.png" alt="view" width="20rem" height="20rem" />
        <div>&nbsp;{{ board.likeNumber }}</div>
      </div>
    </router-link>
  </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue'

const prop = defineProps(['board'])
console.log("incard",prop.board);
</script>
<style scoped>
#cardWrapper {
  border-style: solid;
  border-width: 0 0 1px;
  border-color: gray;
}
#thumbnailBox {
  display: flex;
  justify-content: center;
  align-items: center;
}
#thumbnail {
  background-color: #ffffff;
  width: 90vw;
  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}
#title {
  margin-left: 5vw;
  margin-top: 1vh;
  margin-bottom: 1vh;
  font-size: 1.5rem;
  font-weight: 600;
}
#rel {
  margin-right: 5vw;
  margin-top: 1vh;
  margin-bottom: 1vh;
  display: flex;
  justify-content: flex-end;
}
a {
  text-decoration: none;
  color: black;
}
</style>
