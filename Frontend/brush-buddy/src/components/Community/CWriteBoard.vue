<template>
  <form @submit.prevent="submitForm">
    <label for="input-file"> 업로드 </label>
    <input
      type="file"
      multiple
      id="input-file"
      style="display: none"
      @change="handleFileChange"
    />
  </form>
  <!-- <div v-if="selectedFile.length > 0">
            <p>선택된 파일들:</p>
            <ul>
                <li v-for="(file, idx) in selectedFile.value" :key="idx">{{ file.name }}</li>
            </ul>
        </div> -->
</template>
<script setup lang="ts">
import { ref } from "vue";
const selectedFile = ref<File | null>(null);
const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files) {
    console.log(target.files.length)
    selectedFile.value.push(...target.files);
  }
  console.log(selectedFile);
};
const submitForm = () => {
  if (selectedFile.value) {
    const formData = new FormData();
    formData.append("file", selectedFile.value);

    // 서버로 formData 전송
    // axios 또는 다른 HTTP 라이브러리를 사용할 수 있습니다.
    // axios.post('/upload', formData)
    //   .then(response => {
    //     // 성공적으로 업로드된 경우 처리
    //   })
    //   .catch(error => {
    //     // 업로드 중 에러 발생 시 처리
    //   });
  }
};
</script>
<style></style>
