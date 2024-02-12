<template>
    <div id = "fulldraft">
        <div id = "bookmark">
         <svg xmlns="http://www.w3.org/2000/svg" width="23" height="30" viewBox="0 0 23 30" fill="none">
          <g filter="url(#filter0_d_492_1838)">
          <path d="M4 0V21.9688L11.6298 15.5612L18.7333 21.9688V0H4Z" fill="#E74343"/>
          </g>
          <defs>
          <filter id="filter0_d_492_1838" x="0" y="0" width="22.7333" height="29.9688" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
          <feFlood flood-opacity="0" result="BackgroundImageFix"/>
          <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"/>
          <feOffset dy="4"/>
          <feGaussianBlur stdDeviation="2"/>
          <feComposite in2="hardAlpha" operator="out"/>
          <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.6 0"/>
          <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_492_1838"/>
          <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_492_1838" result="shape"/>
          </filter>
          </defs>
          </svg>
        </div>
        <div class="draft">
            <div v-if="imageUrl" class="image-container">
                <img :src="imageUrl" alt="이미지">
            </div>
            <div v-else>
                <p>이미지가 없습니다.</p>
            </div>
            <!-- <img src="../src/assets/images/draft_img.png" alt="draft" /> -->  
        </div> 
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import {localAxios} from "../../api/axios";

interface Image {
  draft_file_link: string;
}

const imageUrl = ref<string | null>(null);

const imageUrlFromAPI = async (page: number): Promise<Image> => {
  console.log("imageUrlFromAPI called");
  try {
    const image = await localAxios().get(`/draft/draftId=${draftId.value}`);
    return image.data.draftfilelink;
  } catch (err: any) {
    console.log("api 호출 중 오류 발생", err);
    return Promise.reject(err);
  }
};
   
</script>

<style scoped>
    #bookmark {
        position: absolute;
        top: 0;
        right: 1rem;
    }

    #draft {
        position: absolute;
        background: #FFFFFF;
        box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
        border-radius: 20px;

    }
    img {
        width : 100%;
    }
</style>