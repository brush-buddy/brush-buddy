<template>
  <div id="wrapper">
    <div style="height: 2rem"></div>
    <v-text-field v-model="savedTitle" label="Title" variant="underlined"></v-text-field>
    <div v-if="selectedFiles.length > 0">
      <p>선택된 파일들:</p>
      <div id="imgs">
        <div
          v-for="(file, idx) in selectedFiles"
          :key="idx"
          style="
            border-radius: 1rem;
            border: 3px dotted #adadad55;
            padding: 1rem;
            margin: 1rem;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 1rem;
            position: relative;
          "
        >
          <img :src="file.preview" alt="미리보기" style="max-width: 8rem; max-height: 8rem" />
          <span
            class="mdi mdi-alpha-x-box-outline"
            @click="removeimg(idx)"
            style="
              position: absolute;
              bottom: 0.5rem;
              right: 0.5rem;
              font-size: 1rem;
              cursor: pointer;
              color: red;
            "
          ></span>
        </div>
      </div>
    </div>
    <div style="display: flex; justify-content: center">
      <label for="input-file" class="writeBtn" color="primary" style="width: 12rem"
        ><v-icon icon="mdi-camera"></v-icon>사진 업로드</label
      >
      <input
        type="file"
        multiple
        id="input-file"
        style="display: none"
        @change="handleFileChange"
      />
    </div>
    <TextAreaComponent
      style="height: 10rem; margin-top: 1rem"
      @update-content="handleUpdateContent"
    />
    <v-combobox v-model="chips" chips clearable label="set category" multiple variant="solo">
    </v-combobox>
    <div style="display: flex; justify-content: center">
      <label class="writeBtn" @click="makeLink"
        ><v-icon icon="mdi-image-album" />도안 지정
        <v-dialog v-model="dialog" activator="parent" style="width: 80vw; height: 60vh">
          <v-card style="padding: 2rem">
            <div v-if="madeList.length > 0">
              <div style="margin-bottom: 1rem"><h3>만든 도안</h3></div>
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
              <div style="margin-bottom: 1rem; margin-top: 1rem"><h3>구매한 도안</h3></div>
              <div class="thumbnailWrapper">
                <div v-for="(draft, idx) in purchaseList" :key="idx">
                  <img
                    :src="draft.draftThumbnail"
                    alt="thumbnail"
                    class="thumbnail"
                    :class="{ selectedThumbnail: selectedIndex === idx + 99 }"
                    @click="check(idx + 99, 'purchase')"
                  />
                </div>
              </div>
            </div>
            <v-card-actions
              style="position: sticky; bottom: -1rem; background-color: rgba(251, 253, 255, 0.832)"
            >
              <v-btn color="primary" block @click="dialog = false">선택 완료</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </label>
    </div>
    <div style="display: flex; justify-content: center">
      <label class="writeBtn" @click="submitForm"
        ><v-icon icon="mdi-tray-arrow-down" />저장하기</label
      >
    </div>
  </div>

  <div id="navarea"></div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import DraftDetailComponent from '../DraftDetail/DraftDetailComponent.vue'
import TextAreaComponent from '../common/TextAreaComponent.vue'
import { writeBoard } from '../../api/board'
import { getMadeList, getPurchaseList } from '../../api/draft'
import { useRouter } from 'vue-router'
import type { BoardImage, BoardDetail } from '../../api/board'
import type { DraftPurchase, DraftMade } from '../../api/draft'
import type DraftDetailComponentVue from '../DraftDetail/DraftDetailComponent.vue'
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
    savedDraftId.value = purchaseList.value[idx - 99].draftId
  } else {
    savedDraftId.value = madeList.value[idx].draftId
  }
  selectedIndex.value = idx
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
      const convertedImages = await Promise.all(
        selectedFiles.value.map((selectedfile, index) => {
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
        })
      )
      console.log('converted ', convertedImages)
      const boardData: BoardDetail = {
        title: savedTitle.value,
        contents: savedContent.value,
        hashtags: chips.value,
        photo: convertedFiles.value,
        draftId: savedDraftId.value
      }

      await writeBoard(boardData)
        .then((res) => {
          alert('저장되었습니다')
          router.push('/community')
        })
        .catch((err) => console.log(err))
    } catch (error) {
      console.error(error)
    }
  } else {
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
  flex-wrap: nowrap;
  overflow-x: auto;
  .item {
    flex: 0 0 auto;
  }
  > div > img {
    margin: 1rem;
  }
}
.writeBtn {
  display: flex;
  justify-content: center;
  padding: 0.875rem 1.25rem;
  border-radius: 0.475rem;
  border: 1px solid #e0e0e0;

  /* background: linear-gradient(180deg, #f6b4bf 0%, #dcc4ee 100%); */
  gap: 0.625rem;
  margin-bottom: 1rem;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);
  text-decoration: none;
  /* color: white; */
  cursor: pointer;
  v-icon {
    font-size: 1.25rem;
    margin-right: 1rem;
  }
}
.v-card {
  padding: 1rem;
  width: 70vw;
}
.thumbnailWrapper {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.thumbnail {
  width: 5rem;
  height: 5rem;
  margin: 0.1rem;
}
.selectedThumbnail {
  border: 2px solid purple;
}
#navarea {
  height: 10vh;
  width: 100%;
}
#imgs {
  -ms-overflow-style: none;
}
#imgs::-webkit-scrollbar {
  display: none;
}
</style>
