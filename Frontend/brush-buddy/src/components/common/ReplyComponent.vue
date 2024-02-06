<template>
  <div class="wrap">
    <div class="replyImg">
      <img src="../src/assets/images/image1.jpg" alt="reply" />
    </div>
    <div class="reply">
      <div class="replyName">
        <p>ssafy@naver.com</p>
      </div>
      <v-textarea class="fixed-size-textarea" variant="outlined"></v-textarea>
      <!-- <textarea
          v-model="textAreaValue"
          rows="1"
          type="text"
          class="replyText"
        /> -->
      <div class="btnout">
        <div id="btn">
          <ButtonComponent :text="text" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import ButtonComponent from '../common/ButtonComponent.vue'
import { ref } from 'vue'

const text = ref('send')
</script>

<style scoped>
.fixed-size-textarea {
  width: 100%;
  height: 100px;
  margin-right: 2rem;
  resize: none;
}

.wrap {
  box-shadow: 0 0 1px 1px;
  border-color: black;
  display: flex;
  justify-content: space-between;
  /* flex-direction: row; */
  /* align-items: center; */
  border-radius: 2rem;
  margin: 0 1rem 0 1rem;
}

.reply {
  /* background-color: blue; */
  width: 70%;
  display: flex;
  flex-direction: column;
  margin-right: 3rem;
}

.replyImg {
  border-radius: 50%;
  background-color: #fffff3;
  width: 4rem;
  height: 4rem;
  padding: 1rem;
  overflow: hidden;
  margin: 1rem;

  > img {
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
}

.replyName {
  font-size: small;
}

#btn {
  float: right;
}

textarea {
  border: none;
  outline: none;
  resize: none;
}

.btnout {
  display: flex;
  justify-content: flex-end;
  margin: 1rem 0 1rem 0;
}
</style>
