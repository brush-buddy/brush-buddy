<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, inject, ref } from 'vue'
import DraftDetailComponent from '../components/DraftDetail/DraftDetailComponent.vue'
import DraftDetailCommunityThumbnailListComponent from '../components/DraftDetail/DraftDetailCommunityThumbnailListComponent.vue'
import PaletteDetailComponent from '../components/Palette/PaletteDetailComponent.vue'
import { localAxios } from '../api/axios'

import { storeToRefs } from 'pinia'
import { useBookmarksStore } from '../stores/bookmark'
const { setBookmarkState } = useBookmarksStore()
const BookmarkStore = useBookmarksStore()
const { isBookmarked } = storeToRefs(BookmarkStore)

const route = useRoute()
const draftId = route.params.id
const draft = ref<any>({
  draftId: 1,
  userId: 1,
  draftPrice: 100,
  draftColorCode: '{"color1": "#FF5733", "color2": "#C70039"}',
  draftThumbnail: 'thumbnail_1.jpg',
  draftIsAI: true,
  draftIsPublic: true,
  draftIsDefault: false,
  draftIsDeleted: false,
  draftDownload: 5,
  draftBookmark: 10,
  draftPrompt: 'Sample prompt 1',
  draftTimestamp: '2024-01-29T03:00:00.000+00:00',
  isAuthor: false,
  isBookmark: false,
  isBuy: false,
  categoryContents: []
})

const im = ref<any>({ order: '-1', imageUrl: '' })
const charge = ref(false)
const dialog = ref(false)
console.log(route.params)
onMounted(() => {
  localAxios()
    .get(`/draft/${draftId}/is-bookmarked`)
    .then(function (response: any) {
      console.log(response.data)
      setBookmarkState(response.data)
    })

  localAxios()
    .get('draft/' + draftId)
    .then((res) => {
      draft.value = res.data
      console.log(draft.value)
    })
})

const purchase = () => {
  localAxios()
    .post('draft/' + draftId + '/purchase')
    .then((res) => {
      draft.value.isBuy = true
    })
    .catch((err) => {
      console.log(err)
      charge.value = true
    })
}
</script>

<template>
  <div
    style="
      display: flex;
      justify-content: center;
      align-items: center;
      bottom: 2rem;
      flex-direction: column;
    "
  >
    <DraftDetailComponent
      :draft-id="Number(draftId)"
      :image-thumbnail="draft.draftThumbnail"
      :draft-color-code="draft.draftColorCode"
    />
    <div style="display: flex; justify-content: space-between; width: 100vw; padding: 0 1rem">
      <div style="height: 4rem; display: flex; align-items: center">
        <div v-if="draft.isAuthor | draft.isBuy">
          <v-btn prepend-icon="mdi-download" size="small">다운로드하기</v-btn>
        </div>
      </div>
      <div style="height: 4rem; display: flex; align-items: center">
        <div v-if="draft.isAuthor">
          <v-btn prepend-icon="mdi-pencil" size="small">수정하기</v-btn>
        </div>
        <div v-if="!draft.isAuthor">
          <v-btn
            size="small"
            prepend-icon="mdi-bucket"
            v-bind:disabled="draft.isBuy"
            v-bind:text="!draft.isBuy ? `${draft.draftPrice}$ 구매하기` : '구매한도안'"
            @click="dialog = true"
          >
          </v-btn>
          <v-dialog v-model="dialog" width="auto">
            <v-card style="width: 15rem">
              <div
                style="
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                  padding: 1rem 2rem 0rem 2rem;
                "
              >
                <v-card-text> 구매하시겠습니까? </v-card-text>
                <v-card-text style="font-size: small"
                  >{{ draft.draftPrice }}마일리지 차감</v-card-text
                >
              </div>
              <v-card-actions style="display: flex; justify-content: flex-end">
                <v-btn color="primary" @click="(dialog = false), purchase()">구매하기</v-btn>
                <v-btn color="primary" @click="dialog = false">취소</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="charge" width="auto">
            <v-card style="width: 15rem">
              <div
                style="
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                  padding: 1rem 2rem 0rem 2rem;
                "
              >
                <v-card-text> 마일리지가 부족합니다! </v-card-text>
                <v-card-text style="font-size: small"
                  >마일리지 충전페이지로 이동하시겠습니까?</v-card-text
                >
              </div>
              <v-card-actions style="display: flex; justify-content: flex-end">
                <v-btn color="primary" @click="charge = false">이동하기</v-btn>
                <v-btn color="primary" @click="charge = false">취소</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </div>
    </div>
    <DraftDetailCommunityThumbnailListComponent :draft-id="Number(draftId)" />
  </div>
</template>

<style scoped></style>
