<template>
  <form @submit.prevent="submitForm">
    <!-- <input> -->
    <label for="input-file"> 업로드 </label>
    <input type="file" multiple id="input-file" style="display: none" @change="handleFileChange" />
    <div v-if="selectedFiles.length > 0">
      <p>선택된 파일들:</p>
      <ul>
        <li v-for="(file, idx) in selectedFiles" :key="idx">
          <img :src="file.preview" alt="미리보기" style="max-width: 100px; max-height: 100px;"/>
        </li>
      </ul>
    </div>
    <button type="submit">제출</button>
  </form>
</template>

<script setup lang="ts">
import { ref } from 'vue'

interface ExtendedFile extends File {
  preview: string;
}

// 이미지 미리보기 URL을 담을 ref 객체 생성
const imagePreviews = ref<string[]>([]);
const selectedFiles = ref<ExtendedFile[]>([]);

const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement
  if (target.files) {
    console.log('선택된 파일들:', target.files)
    if (target && target.files.length > 0) {
      // FileList를 배열로 변환
      const filesArray: File[] = Array.from(target.files)

      // 배열에 대해 forEach 사용
      filesArray.forEach((one: File) => {
        if(/^image\//.test(one.type)){ 
          // 이미지 미리보기 URL 생성
          const previewURL = URL.createObjectURL(one);
          imagePreviews.value.push(previewURL);
          
          // File을 확장한 ExtendedFile 객체를 생성
          const extendedFile: ExtendedFile = Object.assign(one, { preview: previewURL });
          selectedFiles.value.push(extendedFile);
        } else {
          alert('이미지만 업로드 가능합니다.')
        }
      })
    }
  }
  console.log('선택된 파일들:', selectedFiles.value)
}

const submitForm = () => {
  if (selectedFiles.value.length > 0) {
    const formData = new FormData()
    selectedFiles.value.forEach(file => {
      formData.append('files[]', file)
    });

    // formData를 서버로 전송
    // axios 또는 다른 HTTP 라이브러리를 사용할 수 있습니다.
    // axios.post('/upload', formData)
    //   .then(response => {
    //     // 성공적으로 업로드된 경우 처리
    //   })
    //   .catch(error => {
    //     // 업로드 중 에러 발생 시 처리
    //   });
  }
}
</script>

<style scoped></style>
