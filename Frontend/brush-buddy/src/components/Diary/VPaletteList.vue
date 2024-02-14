<template>
  <div class= "machine-button">     

        <v-btn
          v-if="userStore.isMachineConnected()"
          class="buttonUnder"
          color="red-darken-1"
          size="small"
          variant="tonal"
          prepend-icon="mdi-location-exit"
          @click="disconnectBtn"
        >
        기기 연결해제
        </v-btn>
        <v-dialog
          v-model="cameraOn"
          persistent
          width="auto"
          :scrim="false"
          transition="dialog-bottom-transition"
          class="camera-dialog"
        >
          <template v-slot:activator="{ props }">
            <v-btn
              v-if="!userStore.isMachineConnected()"
              class="buttonUnder"
              color="green-darken-1"
              size="small"
              variant="tonal"
              prepend-icon="mdi-connection"
              @click="cameraOn = true"
            >
            기기 연결하기
            </v-btn>
          </template>
          <v-card class="camera-card">
            <v-card-title class="text-h5">
              기기 연결 하기
            </v-card-title>
            <v-card-text>QR코드를 카메라에 인식시켜주세요</v-card-text>
            <v-card-actions>
              <v-spacer class="camera-component">
                <CameraQRCodeComponent class="camera-component" @machineConnect="cameraOn=false"></CameraQRCodeComponent>
                <v-btn
                  class="buttonUnder"
                  color="red-darken-1"
                  size="small"
                  variant="tonal"
                  prepend-icon="mdi-exit-to-app"
                  @click="cameraOn=false"
                  >
                  그만두기
               </v-btn>
              </v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
  </div>
  
    <div>
      <v-infinite-scroll :items="items" :onLoad="load">
        <div v-for="(item, index) in items" :key="index">
          <CPaletteCard :palette="item" />
        </div>
      </v-infinite-scroll>
    </div>
    <div id="navarea"></div>
</template>
<script setup lang="ts">
  import CPaletteCard from "./CPaletteCard.vue";
  import { ref, type Ref } from "vue";
  import {localAxios} from "../../api/axios";
  import { useUserStore } from "@/stores/user";
  import  CameraQRCodeComponent  from "@/components/Palette/CameraQRCodeComponent.vue"
import { disconnectMachine } from "@/api/machine";
  // madeDraft 불러오기
  interface HeartListRes {
    palettes: {
        draftImage: string;
        paletteColorCode: string;
        paletteCreatedAt: string;
        paletteId: number;
        paletteModifiedTime: string;
        paletteName: string;
    }[];
    pageNum: number;
    length: number;
    totalPage: number;
  }
  interface HeartList {
    draftImage: string;
    paletteColorCode: string;
    paletteCreatedAt: string;
    paletteId: number;
    paletteModifiedTime: string;
    paletteName: string;
  }
  
  const listNum = ref(3);
  const pageNum = ref(1);
  const firstCall = ref([
      localAxios().get('/palette?listNum=3&pageNum=1')
      .then(function (response: any) {
        console.log(response.data);
        items.value = response.data.palettes;
        console.log("아이템 ",items.value);
      })
      .catch(function (error: any) {
        console.log(error.message);
      }),
  ]);
  const totalPage = ref(0);
  const getHeartList = async (page: number): Promise<HeartListRes> => {
    console.log("getPaletteList called");
    try {
      const heartListGet = await localAxios().get(`/palette?listNum=${listNum.value}&pageNum=${page}`);
      return heartListGet.data.palettes;
    } catch (err: any) {
      console.log("api 호출 중 오류 발생", err);
      return Promise.reject(err);
    }
  };
  
  // 무한 스크롤 구현
  const items = ref<HeartList[]>([]);
  
  const api = async () => {
    pageNum.value = pageNum.value + 1;
    return new Promise<number>((resolve) => {
      setTimeout(() => {
        resolve(pageNum.value);
      }, 1000);
    });
  };
  
  type InfiniteScrollSide = any;
  type InfiniteScrollStatus = any;
  const load = async (options: {
    side: InfiniteScrollSide;
    done: (status: InfiniteScrollStatus) => void;
  }): Promise<void> => {
    try {
      // Perform API call
      const res = await api();
      const resList = await getHeartList(res);
      if (resList && Array.isArray(resList) && resList.length > 0) {
        resList.forEach((res: HeartList) => items.value.push(res));
      }
      if (totalPage.value > pageNum.value) {
        options.done("ok");
      } else {
        options.done("empty");
      }
    } catch (error) {
      console.error("Error during load:", error);
      options.done("error");
    }
  };

  //기기 연결 로직
  const userStore = useUserStore();
  const cameraOn : Ref<boolean> = ref(false);

  const disconnectBtn = async () => {
    disconnectMachine()
    .then(() => {
      userStore.setConnectedMachine(-1);
      alert("기기 연결 해제 성공")
    })
    .catch(() => {
      alert("기기 연결 해제 실패")
    })
  }
  </script>
  
  <style scoped>
  #navarea {
    height: 10vh;
    width: 100%;
  }
  .machine-button{
    display: flex;
    justify-content: end;
    padding: 1vh 5vw
  }
  .camera-component{
    height: 40vh;
    width: 80%;
  }

  .camera-card{
    padding-top: 2vh;
    display: flex;
    align-items: center;
  }
  .camera-component{
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1rem;
    margin-bottom: 1rem;
  }
  </style>
  