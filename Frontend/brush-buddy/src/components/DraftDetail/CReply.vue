<template>
  <v-card style="width: 90vw; display: flex">
    <div class="profile-wrap">
      <div id="profile-avartar">
        <img src="@/assets/logo.png" style="width: 1.5rem; height: 1.5rem" alt="프로필사진" />
      </div>
    </div>
    <div class="reply">
      <div id="naeyoung">
        <v-container fluid>
          <v-textarea
            label="reply"
            auto-grow
            variant="outlined"
            rows="4"
            row-height="10"
            v-model="content"
          ></v-textarea>
        </v-container>
      </div>
      <div style="display: flex; justify-content: flex-end">
        <v-btn :disabled="content === ''" @click="sendreply()" size="small">저장하기</v-btn>
      </div>
    </div>
  </v-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { localAxios } from '@/api/axios'

const text = ref('send')
const props = defineProps({
  boardId: Number
})

const emits = defineEmits(['replyReload'])
const content = ref('')
const sendreply = () => {
  if (text.value === 'send') {
    localAxios()
      .post(`/board/${props.boardId}/replies`, {
        contents: content.value
      })
      .then(function (response: any) {
        content.value = ''
        console.log(response)
        emits('replyReload')
      })
      .catch(function (error: any) {
        console.log(error)
      })
  }
}
</script>

<style scoped>
#naeyoung {
  margin: 1rem 0 0 0;
}

.v-container {
  width: 100%;
  padding: 0;
  margin-right: auto;
  margin-left: auto;
}

.fixed-size-textarea {
  width: 100%;
  height: 100px;
  margin-right: 2rem;
  resize: none;
}

.wrap {
  box-shadow: 0.5pc 0.5pc 9px 0 #f1f0f0;
  border-color: rgb(207, 205, 205);
  display: flex;
  justify-content: space-evenly;
  flex-direction: row;
  /* align-items: center; */
  border-radius: 10%;
  /* margin: 1rem 1rem 1rem 1rem; */
}

.profile-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 3rem;
  margin-left: 1rem;
}

.reply {
  /* background-color: blue; */
  display: flex;
  flex-direction: column;
  margin: 1rem;
  width: 90%;
  /* margin-bottom: 1rem !important; */
  /* margin-right: 3rem; */
}

#profile-avartar {
  border-radius: 50%;
  background-color: #fbf4c1;
  width: 2.5rem;
  height: 2.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  /* padding: 1rem; */
  /* margin-left: 1rem; */
  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
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
  margin: 0 0 0.5rem 0;
}
</style>
