<template>
  <div class="wrap">
    <div class = "profile-wrap">
      <div id="profile-avartar">
        <img src="@/assets/logo.png" alt="프로필사진" />
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
          ></v-textarea>
        </v-container>
      </div>
      <div class="btnout">
        <v-btn
          color="#C5C6EF"
          variant="tonal"
          @click="(dialog = false), sendreply()"
        >
          send
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import CButton from '@/components/Community/CButton.vue'

const text = ref('send')

const sendreply = () => {
  if (text.value === 'send') {
    axios
      .post('http://localhost:8000/api/v1/draft/pipo-painting',)
      .then((res) => {
        console.log(res)
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
  border-radius: 4rem;
  margin: 1rem 1rem 1rem 1rem;
}

.profile-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 3rem;
}

.reply {
  /* background-color: blue; */
  width: 80%;
  display: flex;
  flex-direction: column;
  margin : 2rem 1rem 2rem 1rem;
  /* margin-right: 3rem; */
}

#profile-avartar {
  border-radius: 50%;
  background-color: #fbf4c1;
  width: 4rem;
  height: 4rem;
  padding: 1rem;
  margin-left:1rem;
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
