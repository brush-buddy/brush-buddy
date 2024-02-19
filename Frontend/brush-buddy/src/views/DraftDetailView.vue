<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, ref } from 'vue'
import DraftDetailComponent from '../components/DraftDetail/DraftDetailComponent.vue'
import DraftDetailCommunityThumbnailListComponent from '../components/DraftDetail/DraftDetailCommunityThumbnailListComponent.vue'
import items from '../stores/menutypes'

import { localAxios } from '../api/axios'

import { storeToRefs } from 'pinia'
import { useBookmarksStore } from '../stores/bookmark'
import router from '@/router'
const { setBookmarkState } = useBookmarksStore()
const BookmarkStore = useBookmarksStore()
const { totalBookmarkNum } = storeToRefs(BookmarkStore)

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

const downloadNum = ref(0)

const charge = ref(false)
const dialog = ref(false)
const modify = ref(false)
const categories = ref([])

console.log(route.params)
onMounted(() => {
  localAxios()
    .get('draft/' + draftId)
    .then((res) => {
      draft.value = res.data
      categories.value = res.data.categoryContents
      console.log(draft.value)
      setBookmarkState(res.data.isBookmark, res.data.draftBookmark)
      downloadNum.value = draft.value.draftDownload
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

const downloadImage = async (imageUrl: string) => {
  try {
    const response = await fetch(imageUrl)
    const imageBlob = await response.blob()
    const url = window.URL.createObjectURL(imageBlob)

    const link = document.createElement('a')
    link.href = url
    link.download = 'downloaded_image.jpeg' // 다운로드될 파일명
    document.body.appendChild(link)
    link.click()

    // 클릭 후에는 링크를 제거
    document.body.removeChild(link)
    window.URL.revokeObjectURL(url)
    downloadNum.value += 1
  } catch (error) {
    console.error('이미지 다운로드 중 오류 발생:', error)
  }
}

const download = () => {
  localAxios()
    .get('draft/' + draftId + '/download')
    .then((res) => {
      downloadImage(res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

const modifyCategories = () => {
  localAxios()
    .put('draft/' + draftId, {
      categoryList: categories.value
    })
    .then((res) => {
      console.log(res)
    })
    .catch((err) => {
      console.log(err)
    })
}

const deleteDraft = () => {
  localAxios()
    .delete('/draft/' + draftId)
    .then((res) => {
      alert('도안이 삭제 처리 되었습니다.')
      router.push('/search')
    })
    .catch((error) => {
      alert('오류로 인해 삭제에 실패했습니다.')
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
    <div
      style="
        display: flex;
        justify-content: space-between;
        margin-top: 3rem;
        margin-bottom: 0.5rem;
        align-items: flex-start;
        width: 100vw;
        padding: 0 1rem;
      "
    >
      <div>
        <template v-for="(category, i) in categories" :key="i">
          <v-chip color="pink" dark size="small" style="margin-right: 0.2rem">{{
            category
          }}</v-chip>
        </template>
      </div>
      <div style="display: flex; align-items: center">
        <v-icon icon="mdi-download-outline"></v-icon>
        <p class="draftInfo">{{ downloadNum }}</p>
        <v-icon icon="mdi-bookmark-outline"></v-icon>
        <p class="draftInfo">{{ totalBookmarkNum }}</p>
      </div>
    </div>
    <div style="display: flex; justify-content: space-between; width: 100vw; padding: 0 1rem">
      <div style="height: 4rem; display: flex; align-items: center">
        <div v-if="draft.isAuthor | draft.isBuy">
          <v-btn
            prepend-icon="mdi-download"
            size="small"
            @click="download()"
            variant="tonal"
            color="purple-darken-2"
            >다운로드하기</v-btn
          >
        </div>
      </div>
      <div style="height: 4rem; display: flex; align-items: center">
        <div class="author-buttons" v-if="draft.isAuthor">
          <v-btn
            variant="tonal"
            color="pink-darken-2"
            prepend-icon="mdi-pencil"
            size="small"
            @click="modify = true"
            >수정하기</v-btn
          >
          <v-btn
            variant="tonal"
            color="white-darken-2"
            prepend-icon="mdi-delete"
            size="small"
            @click="deleteDraft"
            >삭제하기</v-btn
          >
          <v-dialog v-model="modify" width="auto">
            <v-card style="width: 20rem">
              <div
                style="
                  display: flex;
                  flex-wrap: wrap;
                  flex-direction: column;
                  align-items: center;
                  padding: 2rem 2rem 0rem 2rem;
                "
              >
                <!-- {{ items }} -->
                <v-autocomplete
                  v-model="categories"
                  :items="items['items'].value"
                  chips
                  closable-chips
                  color="blue-grey-lighten-2"
                  item-title="text"
                  item-value="text"
                  label="카테고리"
                  multiple
                  style="width: 15rem"
                  bg-color="white"
                >
                  <template v-slot:chip="{ props, item }">
                    <v-chip
                      v-bind="props"
                      :prepend-icon="item.raw.icon"
                      :text="item.raw.text"
                    ></v-chip>
                  </template>

                  <template v-slot:item="{ props, item }">
                    <v-list-item
                      v-bind="props"
                      :prepend-icon="item.raw.icon"
                      :title="item.raw.text"
                    ></v-list-item>
                  </template>
                </v-autocomplete>
              </div>
              <v-card-actions style="display: flex; justify-content: flex-end">
                <v-btn color="primary" @click="modify = false">취소</v-btn>
                <v-btn color="success" @click="(modify = false), modifyCategories()"
                  >수정하기</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
        <div v-if="!draft.isAuthor">
          <v-btn
            size="small"
            prepend-icon="mdi-bucket"
            v-bind:disabled="draft.isBuy"
            v-bind:text="!draft.isBuy ? `${draft.draftPrice}M 구매하기` : '구매한도안'"
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
                <v-btn variant="tonal" color="pink-darken-2" @click="(dialog = false), purchase()"
                  >구매하기</v-btn
                >
                <v-btn variant="tonal" color="pink-darken-2" @click="dialog = false">취소</v-btn>
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
                <v-btn color="primary" @click="(charge = false), router.push('/charge')"
                  >이동하기</v-btn
                >
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

<style scoped>
.draftInfo {
  margin: 0 0.5rem;
  font-size: 1.5rem;
}
.author-buttons {
  display: flex;
  justify-content: center;
  gap: 1vw;
}
</style>
