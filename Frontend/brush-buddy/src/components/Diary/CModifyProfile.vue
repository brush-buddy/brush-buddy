<template>
  <v-card-text>
    <div>
      <v-text-field :label="nickname" v-model="newNickname"></v-text-field>
    </div>
    <div id="buttons">
      <v-btn @click="modifyNickname" >apply</v-btn>
      <div @click="withdrawal">회원 탈퇴</div>
    </div>
  </v-card-text>

  <!-- 닉네임 수정 스낵바 -->
  <v-snackbar v-model="snackbar" :timeout="timeout">
    {{ text }}
    <template v-slot:actions>
      <v-btn color="blue" variant="text" @click="snackbar = false">
        Close
      </v-btn>
    </template>
  </v-snackbar>

</template>
<script setup lang="ts">
import { ref } from "vue";
import {withDraw} from "../../api/user"
import {localAxios} from "../../api/axios"
defineProps(["nickname"]);
const emits = defineEmits(["reloadInfo"])
const newNickname = ref('');
const modifyNickname = ()=>{
  localAxios().post(`/mypage/nickname`, {"newNickname":newNickname.value})
  .then((res)=>{
    snackbar.value = true;
    emits("reloadInfo")
  });
};
const withdrawal = () => {
  withDraw().then(() => {
    alert("회원탈퇴 완료");
  });

};
const snackbar = ref(false);
const text = "닉네임 수정 완료";
const timeout = 2000;
</script>
<style scoped>
#buttons {
  display: flex;
  justify-content: space-between;
}
</style>
