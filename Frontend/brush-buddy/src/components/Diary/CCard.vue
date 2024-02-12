<template>
  <div id="cardWrapper">
    <div id="container">
      <div id="title">{{ board.boardTitle }}</div>
      <div id="thumbnailBox">
        <div id="thumbnail">
          <img :src="board.thumbnail" alt="Thumbnail" />
        </div>
      </div>
      <div id="rel">
        <img src="../../assets/icon/view.png" alt="view" width="20rem" height="20rem" />
        <div>&nbsp;{{ board.likeNumber }}&nbsp;&nbsp;</div>
        <img src="../../assets/icon/heart_filled.png" alt="view" width="20rem" height="20rem" />
        <div>&nbsp;{{ board.views }}</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { defineProps } from 'vue'

const prop = defineProps(['board'])
// console.log("incard",prop.board);
</script>
<style scoped>
#cardWrapper {
  border-style: solid;
  border-width: 0 0 1px;
  border-color: gray;
  padding: 1vh 0 1vh;
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
  margin-bottom: 1vh;
  font-size: 1.5rem;
  font-weight: 600;
}
#rel {
  display: flex;
  justify-content: flex-end;
  margin-top: 1vh;
  margin-right: 5vw;
}
a {
  text-decoration: none;
  color: black;
}
</style>
