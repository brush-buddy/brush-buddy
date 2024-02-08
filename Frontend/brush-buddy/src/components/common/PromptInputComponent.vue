<script setup lang="ts">
import { ref } from "vue";
import { localAxios } from "../../api/axios";
const prompt = ref<string>("");
const dialog = ref(false);
const loadingState = ref(true);
const imageSrc = ref("../../assets/icon/loading.gif");
const makeImage = () => {
  console.log(prompt.value);
  // localAxios()
  //   .post("http://localhost:8000/api/v1/prompt/", { propmt: prompt.value })
  //   .then((res) => {
  //     console.log(res);
  //   });

  setTimeout(function () {
    loadingState.value = false;
    imageSrc.value = "https://picsum.photos/200/300?random=1";
  }, 10000);
};

//-그려줘라고 입력하면 그림을 만들어드려요
</script>

<template>
  <div class="input-box-container">
    <!-- <div class="input-container"> -->
    <input
      type="text"
      v-model="prompt"
      class="search-input-box"
      placeholder="-그려줘라고 입력하면 그림을 만들어드려요"
      style="width: 18rem"
      v-on:keyup.enter="makeImage()"
    />

    <v-row justify="center">
      <v-dialog v-model="dialog" persistent width="auto">
        <template v-slot:activator="{ props }">
          <v-btn
            @click="makeImage()"
            icon="mdi-arrow-up"
            size="small"
            color="success"
            v-bind="props"
          ></v-btn>
        </template>
        <v-card>
          <div style="display: flex; justify-content: center">
            <img
              src="../../assets/icon/loading.gif"
              alt=""
              style="margin: 2rem; width: 10rem"
              v-show="loadingState"
            />
            <img
              :src="imageSrc"
              alt=""
              v-show="!loadingState"
              style="margin: 2rem"
            />
          </div>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="green-darken-1"
              variant="text"
              @click="dialog = false"
            >
              Disagree
            </v-btn>
            <v-btn
              color="green-darken-1"
              variant="text"
              @click="dialog = false"
            >
              Agree
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- </div> -->
  </div>
</template>

<style scoped>
.input-box-container {
  display: flex;
  width: 21.5rem;
  height: 2.125rem;
  flex-direction: row;
  align-items: center;
  flex-shrink: 0;
  justify-content: space-around;
}

.input-box-sub-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  align-self: stretch;
}

.search-input-box {
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  align-self: stretch;
  border-radius: 0.625rem;
  opacity: 0.5;
  background: var(--system-grey4, #d2d2d7);
  color: var(--label-primary, #000);
  text-align: center;
  font-family: ABeeZee;
  font-size: 1.0625rem;
  font-style: normal;
  font-weight: 400;
}
input[type="text"]::placeholder {
  font-size: 0.8rem;
}
</style>
