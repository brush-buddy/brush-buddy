<template>
  <div id="wrapper">
    <v-text-field v-model="savedTitle" label="Title" variant="underlined"></v-text-field>
    <div v-if="selectedFiles.length > 0">
      <p>선택된 파일들:</p>
      <div id="imgs">
        <div v-for="(file, idx) in selectedFiles" :key="idx">
          <img :src="file.preview" alt="미리보기" style="max-width: 8rem; max-height: 8rem" />
          <span class="mdi mdi-alpha-x-box-outline" @click="removeimg(idx)"></span>
        </div>
      </div>
    </div>
    <div>
      <label for="input-file" id="Btn" color="primary">사진 업로드</label>
      <input
        type="file"
        multiple
        id="input-file"
        style="display: none"
        @change="handleFileChange"
      />
    </div>
    <TextAreaComponent @update-content="handleUpdateContent" />
    <v-combobox v-model="chips" chips clearable label="set category" multiple variant="solo">
    </v-combobox>
    <label id="Btn" @click="makeLink"
      >도안 지정
      <v-dialog v-model="dialog" activator="parent" width="auto">
        <v-card>
          <div v-if="madeList.length > 0">
            <div>만든 도안</div>
            <div class="thumbnailWrapper">
              <div v-for="(draft, idx) in madeList" :key="idx">
                <img
                  :src="draft.draftThumbnail"
                  alt="thumbnail"
                  class="thumbnail"
                  :class="{ selectedThumbnail: selectedIndex === idx }"
                  @click="check(idx, 'made')"
                />
              </div>
            </div>
          </div>
          <div v-if="purchaseList.length > 0">
            <div>구매한 도안</div>
            <div class="thumbnailWrapper">
              <div v-for="(draft, idx) in purchaseList" :key="idx">
                <img
                  :src="draft.draftThumbnail"
                  alt="thumbnail"
                  class="thumbnail"
                  :class="{ selectedThumbnail: selectedIndex === idx+99 }"
                  @click="check(idx+99, 'purchase')"
                />
              </div>
            </div>
          </div>
          <v-card-actions>
            <v-btn color="primary" block @click="dialog = false">선택 완료</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </label>
    <label id="Btn" @click="submitForm">저장하기</label>
  </div>
  <div id="navarea"></div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import TextAreaComponent from '../common/TextAreaComponent.vue'
import { writeBoard } from '../../api/board'
import { getMadeList, getPurchaseList } from '../../api/draft'
import { useRouter } from 'vue-router'
import type { BoardImage, BoardDetail } from '../../api/board'
import type { DraftPurchase, DraftMade } from '../../api/draft'
interface ExtendedFile extends File {
  preview: string
}
// title 영역 값 저장
const savedTitle = ref('')
// content 영역 값 저장
const savedContent = ref('')
const handleUpdateContent = (newContent: string) => {
  savedContent.value = newContent
}
// 선택된 이미지 삭제
const removeimg = (idx: number) => {
  if (idx >= 0 && idx < selectedFiles.value.length) {
    selectedFiles.value.splice(idx, 1)
  } else {
    console.error('Invalid index.')
  }
}
// category 영역 값 저장
const chips = ref<string[]>([])
//도안 지정
const dialog = ref(false)
const purchaseList = ref<DraftPurchase[]>([])
const madeList = ref<DraftMade[]>([])
const makeLink = async () => {
  await getPurchaseList({
    listNum: 100000,
    pageNum: 1
  }).then((res) => (purchaseList.value = res.data.drafts))
  console.log(purchaseList.value)
  await getMadeList({
    listNum: 100000,
    pageNum: 1
  }).then((res) => (madeList.value = res.data.drafts))
  console.log(madeList.value)
}
// 도안 선택 저장
const savedDraftId = ref<number | undefined>(undefined)
const selectedIndex = ref<number | null>(null)
const check = (idx: number, category: string) => {
  if (category == 'purchase') {
    savedDraftId.value = purchaseList.value[idx-99].draftId
  } else {
    savedDraftId.value = madeList.value[idx].draftId
  }
  selectedIndex.value = idx;
}

// 이미지 미리보기
const selectedFiles = ref<ExtendedFile[]>([])
const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement
  if (target.files) {
    if (target.files.length > 0) {
      // FileList를 배열로 변환
      const filesArray: File[] = Array.from(target.files)

      // 배열에 대해 forEach 사용
      filesArray.forEach((one: File) => {
        // 이미지 파일만 업로드 가능
        if (/^image\//.test(one.type)) {
          // 이미지 미리보기 URL 생성
          const previewURL = URL.createObjectURL(one)

          // File을 확장한 ExtendedFile 객체를 생성
          const extendedFile: ExtendedFile = Object.assign(one, { preview: previewURL })
          selectedFiles.value.push(extendedFile)
        } else {
          alert('이미지만 업로드 가능합니다.')
        }
      })
    }
  }
}
const convertedFiles = ref<BoardImage[]>([])
const router = useRouter()
const submitForm = async () => {
  if (selectedFiles.value.length > 0) {
    try {
      const convertedImages = await Promise.all(selectedFiles.value.map((selectedfile, index) => {
        return new Promise((resolve, reject) => {
          const reader = new FileReader()
          // 파일을 base64로 읽기
          reader.readAsDataURL(selectedfile)
          reader.onload = (e) => {
            const base64Data = e?.target?.result as string
            if (base64Data) {
              const boardImage: BoardImage = {
                order: index + 1,
                img: base64Data
              }
              convertedFiles.value.push(boardImage)
              resolve(boardImage)
            } else {
              reject(new Error('이미지 변환 실패'))
            }
          }
        })
      }))
      console.log("converted ", convertedImages);
      const boardData: BoardDetail = {
        title: savedTitle.value,
        contents: savedContent.value,
        hashtags: chips.value,
        photo: convertedFiles.value,
        draftId: savedDraftId.value
      };
      
      await writeBoard(boardData)
      .then((res)=>alert('저장되었습니다'))
      .catch((err)=>console.log(err));
      
    } catch (error) {
      console.error(error)
    }
  }
 else{
   alert('이미지를 선택해주세요.')
 }
}
</script>

<style scoped>
#wrapper {
  padding: 1rem;
}
#imgs {
  display: flex;
  flex-wrap: wrap;
  > div > img {
    margin: 1rem;
  }
}
#Btn {
  display: flex;
  justify-content: center;
  padding: 0.875rem 1.25rem;
  border-radius: 0.475rem;
  background: linear-gradient(180deg, #f6b4bf 0%, #dcc4ee 100%);
  gap: 0.625rem;
  text-decoration: none;
  color: white;
}
.v-card {
  padding: 1rem;
  width: 70vw;
}
.thumbnailWrapper {
  display: flex;
  flex-wrap: wrap;
}
.thumbnail {
  width: 5rem;
  height: 5rem;
  margin: 0.1rem;
}
.selectedThumbnail {
  border: 2px solid purple
}
#navarea {
  height: 10vh;
  width: 100%;
}
</style>