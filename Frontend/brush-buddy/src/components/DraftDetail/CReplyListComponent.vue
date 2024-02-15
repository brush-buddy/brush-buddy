<script setup lang="ts">
import { ref } from 'vue'
import type { ReplyListElement } from '../../api/board'
import { deleteReply } from '../../api/board'
const props = defineProps<{
  replyList: ReplyListElement
  boardId: number
}>()

const emits = defineEmits(['replyReload'])

const dialog = ref(false)

const removeReply = async () => {
  await deleteReply(props.boardId, props.replyList.replyId).then((res) => {
    emits('replyReload')
  })
}
const generateRandomColorWithSameTone = () => {
  // 채도와 명도를 랜덤하게 설정 (예: 30% ~ 70%)
  var hue = parseInt(`${Math.random() * 24}`, 10) * 15
  return `hsl(${hue}, 16%, 80%)`
}
</script>

<template>
  <v-card style="margin: 1rem; width: 90vw" class="elevation-2">
    <div style="padding: 1rem; display: flex; justify-content: space-between">
      <div style="display: flex">
        <div
          id="profile-avartar"
          v-bind:style="{ backgroundColor: generateRandomColorWithSameTone() }"
        >
          <img src="@/assets/logo.png" style="width: 1.5rem; height: 1.5rem" alt="프로필사진" />
        </div>
        <div>
          <p style="font-size: small; margin-left: 1rem; color: grey">{{ replyList.nickname }}</p>
          <div style="flex-wrap:">
            <p class="replyContent">{{ replyList.contents }}</p>
          </div>
        </div>
      </div>
      <div>
        <v-icon
          v-if="replyList.isMine"
          icon="mdi-trash-can"
          @click="(dialog = true), console.log('click')"
          color="red"
        >
        </v-icon>
        <v-dialog v-model="dialog" width="auto">
          <v-card>
            <v-card-text> 삭제 하시겠습니까? </v-card-text>
            <v-card-actions style="display: flex; justify-content: flex-end">
              <v-btn color="primary" @click="dialog = false">취소</v-btn>
              <v-btn color="primary" @click="(dialog = false), removeReply()">삭제</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
    </div>
  </v-card>
</template>

<style scoped>
#profile-avartar {
  border-radius: 50%;
  background-color: #fbf4c1;
  width: 2.5rem;
  height: 2.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  /* padding: 1rem; */

  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}

.replyContent {
  margin-left: 1rem;
}
</style>
