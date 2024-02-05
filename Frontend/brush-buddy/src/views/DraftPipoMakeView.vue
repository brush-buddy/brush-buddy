<script setup lang="ts">
import SinglePaletteComponent from '../components/SinglePaletteComponent.vue'
const props = defineProps({
  pipoUrl: String,
  pipoPalette: {}
})
import { ref } from 'vue'
const shared = ref(true)
</script>

<template>
  <div style="display: flex; justify-content: center; flex-direction: column; align-items: center">
    <img :src="pipoUrl" alt="" style="width: 15rem; margin-top: 2rem;" />
    <div style="height: 3rem; display: flex; align-items: center">
      <p>색 추출 결과</p>
    </div>
    <div class="paletteColors">
      <template v-for="(value, key) in pipoPalette" :key="key">
        <SinglePaletteComponent :color="value" />
      </template>
    </div>

    <div style="display: flex; flex-direction: row; align-items: center">
      <p style="margin-right: 1rem">도안 공유</p>
      <v-switch v-model="shared" hide-details inset color="pink-lighten-1"></v-switch>
    </div>

    <div style="display: flex; flex-direction: column; align-items: flex-start;">
        <p>도안 이름</p>     
        <v-text-field></v-text-field>
    </div>
    <!-- https://vuetifyjs.com/en/components/chips/#custom-list-->
    <div style="display: flex; flex-direction: column; justify-content: flex-start;">
        <template>
  <v-card
    class="mx-auto"
    max-width="500"
  >
    <v-toolbar
      color="transparent"
      flat
    >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>Photo Info</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn
        icon="mdi-magnify"
        @click="searchField.focus()"
      >
      </v-btn>
    </v-toolbar>

    <v-container>
      <v-row
        align="center"
        justify="start"
      >
        <v-col
          v-for="(selection, i) in selections"
          :key="selection.text"
          cols="auto"
          class="py-1 pe-0"
        >
          <v-chip
            :disabled="loading"
            closable
            @click:close="selected.splice(i, 1)"
          >
            <v-icon
              :icon="selection.icon"
              start
            ></v-icon>

            {{ selection.text }}
          </v-chip>
        </v-col>

        <v-col
          v-if="!allSelected"
          cols="12"
        >
          <v-text-field
            ref="searchField"
            v-model="search"
            hide-details
            label="Search"
            single-line
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>

    <v-divider v-if="!allSelected"></v-divider>

    <v-list>
      <template v-for="item in categories">
        <v-list-item
          v-if="!selected.includes(item)"
          :key="item.text"
          :disabled="loading"
          @click="selected.push(item)"
        >
          <template v-slot:prepend>
            <v-icon
              :disabled="loading"
              :icon="item.icon"
            ></v-icon>
          </template>

          <v-list-item-title v-text="item.text"></v-list-item-title>
        </v-list-item>
      </template>
    </v-list>

    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn
        :disabled="!selected.length"
        :loading="loading"
        color="purple"
        variant="text"
        @click="next"
      >
        Next
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
    </div>
    <div style="display: flex; justify-content: flex-end; width: 80vw;">
        
        <v-btn color="pink-accent-1" size="small" @click="discard()" style="margin-right: 10px; ">취소</v-btn>
        <v-btn color="purple-lighten-1" size = "small" @click="save()">만들기</v-btn>
    </div>
    <div style="height: 100px"></div>
  </div>
</template>
<style scoped>
.paletteColors {
  display: flex;
  flex-direction: row;
  padding-right: 1.15rem;
}
</style>
