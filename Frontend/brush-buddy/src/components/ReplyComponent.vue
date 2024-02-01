<template>
  <div class="out">
    <div class="wrap">
      <div class="replyImg">
        <img src="/src/assets/images/image1.jpg" alt="reply" />
      </div>
      <div class="reply">
        <div class="replyName">
          <p>이름</p>
        </div>
        <textarea
          v-model="textAreaValue"
          rows="1"
          type="text"
          class="replyText"
          @input="resizeTextArea"
        />
        <div class="btnout">
          <div id="btn">
            <MiniBtnComponent :title="title" :color="color" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MiniBtnComponent from "@/components/MiniBtnComponent.vue";
import { ref } from "vue";

const title = ref("send");
const color = ref("#C5C6EF");

const textAreaValue = ref("");
const textareaRef = ref(null);

const resizeTextArea = () => {
  const targetTextarea = textareaRef.value;
  if (targetTextarea.scrollHeight > targetTextarea.clientHeight) {
    // textarea height 확장
    targetTextarea.style.height = targetTextarea.scrollHeight + "px";
  } else {
    // textarea height 축소
    targetTextarea.style.height = targetTextarea.scrollHeight - 18 + "px";
  }
};
</script>

<style scoped>
.out {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-self: center;
  /* justify-content: in; */
  /* margin: 1rem; */
  /* background-color: aquamarine; */
  border-style: solid;
  border-width: thin;
  border-radius: 1rem;
}

.wrap {
  /* background-color: aqua; */
  display: flex;
  justify-content: space-between;
  /* flex-direction: row; */
  /* align-items: center; */
  margin: auto;
}

.reply {
  /* background-color: blue; */
  width: 70%;
  display: flex;
  flex-direction: column;
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
    object-fit: cover;
  }
}
.replyContent {
}

.replyName {
  font-size: small;
}

.replyText {
}

#btn {
  float: right;
}

textarea {
  border: none;
  border-bottom-style: solid;
  border-width: thin;
  border-bottom-color: black;
  outline: none;
  resize: none;
}
.btnout {
  display: flex;
  justify-content: flex-end;
  margin: 1rem;
}
</style>
