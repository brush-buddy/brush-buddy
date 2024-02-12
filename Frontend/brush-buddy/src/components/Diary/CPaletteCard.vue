<template>
  <RouterLink :to="{ name: 'paletteDetail', params: { id: palette.paletteId } }">
    <div id="paletteCard">
      <div id="head">
        <span id="title">{{ palette.paletteName }}</span>
        <span id="timestamp">
          {{ palette.paletteModifiedTime.substring(0, 10) }}
          {{ palette.paletteModifiedTime.substring(11, 19) }}
        </span>
      </div>

        <div id="paletteColors">
          <div id="colors" v-for="(item, index) in colors" :key="index">
            <SinglePaletteComponent :color="item" />
          </div>
        </div>
        <div id="thumbnailBox">
          <div id="thumbnail">
            <v-img
              class="mx-auto w-100 elevation-2"
              lazy-src="../assets/logo.png"
              cover
              rounded="lg"
              :src="palette.draftImage"
            >
              <template v-slot:placeholder>
                <div class="d-flex align-center justify-center fill-height">
                  <v-progress-circular color="grey-lighten-4" indeterminate></v-progress-circular>
                </div>
              </template>
            </v-img>
          </div>
        </div>
      </div>
  </RouterLink>
</template>
<script setup lang="ts">
import { RouterLink } from 'vue-router'
import SinglePaletteComponent from '../common/SinglePaletteComponent.vue'
const props = defineProps(['palette'])
const colors = JSON.parse(props.palette.paletteColorCode)
// console.log(JSON.parse(props.palette.paletteColorCode))
</script>
<style scoped>

#paletteCard {
  border-radius: 10px;
  margin: 0 3vw 1vh;
  box-shadow: 3px 3px 10px 3px rgb(206, 206, 206);
  padding: 0.5vh 0 1vh;
}
#head {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  margin: 1vh 5vw 1vh;
}
#title {
  font-size: 1.5rem;
  font-weight: 600;
}
#timestamp {
  font-size: small;
  color: gray;
}
#paletteColors {
  display: flex;
  flex-direction: row;
  padding-right: 1.15rem;
  margin: 1vh 5vw 1vh;
}
#thumbnailBox {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 5vw 0;
}
#thumbnail {
  background-color: #ffffff;
  width: 90vw;
  overflow: hidden;
}
a {
  text-decoration: none;
  color: black;
}
</style>
