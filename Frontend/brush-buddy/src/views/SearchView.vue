<template>
  <div>
    <div class="searchBar">
      <div class="searchInputBar">
        <v-icon color="purple" icon="mdi-magnify"></v-icon>
        <input
          type="text"
          placeholder="search"
          id="searchInput"
          v-model="searchValue"
          v-on:keyup.enter="makeSearch()"
        />
        <v-icon @click="makeSearch()">
          <v-icon color="purple" icon="mdi-arrow-left-bottom"></v-icon>
        </v-icon>
      </div>

      <v-tabs v-model="tab" color="purple-darken-2" grow style="margin-bottom: 1rem; width: 100vw">
        <v-tab :value="1" style="width: 50%">Draft <v-icon icon="mdi-image-album" /></v-tab>
        <v-tab :value="2" style="width: 50%">Community<v-icon icon="mdi-account-multiple" /></v-tab>
      </v-tabs>
      <v-window v-model="tab">
        <v-window-item :value="1">
          <SDraftListComponent :searchValue="searchValue" ref="draftList" />
        </v-window-item>
        <v-window-item :value="2">
          <SCommunityListComponent :searchValue="searchValue" ref="communityList" />
        </v-window-item>
      </v-window>
    </div>
  </div>
</template>

<script setup lang="ts">
import SCommunityListComponent from '../components/Search/SCommunityListComponent.vue'
import SDraftListComponent from '../components/Search/SDraftListComponent.vue'
import { ref } from 'vue'
const draftList = ref()
const communityList = ref()

const searchValue = ref('')
const tab = ref(1)

const makeSearch = () => {
  console.log('search')

  draftList.value?.searchList()
  communityList.value?.searchList()
}
</script>

<style scoped>
.searchInputBar {
  display: flex;

  align-items: center;
  justify-content: space-between;
  margin: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 3rem;
  /* width: ; */
  padding-left: 1rem;
  padding-right: 1rem;

  box-shadow: 1px 1px 5px #ccc;
}
#searchInput {
  padding: 0.5rem;
  margin-right: 1rem;
  /* border: 1px solid #ccc; */
  /* margin : 0.5rem; */
  width: 13rem;
}
.searchBar {
  display: flex;
  align-items: center;
  flex-direction: column;
  /* align-items: center; */
  border-bottom: 1px solid #ccc;
  box-shadow: 5px #ccc;
  margin-bottom: 1rem;
}
input[type='text']:focus {
  outline: 1px #ffffff; /* oranges! yey */
}

.conditionBar {
  display: flex;
  justify-content: space-evenly;

  margin-top: 0.5rem;

  margin-bottom: 0.5rem;
}
.searchOption {
  width: 9rem;
  /* background-color: bisque; */
}
p {
  margin: 0;
  padding: 0;
  margin-left: 0.5rem;
}
</style>
