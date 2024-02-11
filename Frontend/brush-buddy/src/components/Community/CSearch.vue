<template>
  <div class="searchBar">
    <div class="searchInputBar">
      <form action="">
        <v-icon color="purple" icon="mdi-magnify"></v-icon>
        <input type="text" placeholder="search" id="searchInput" />
        <v-button type="submit">
          <v-icon color="purple" icon="mdi-arrow-left-bottom"></v-icon>
        </v-button>
      </form>
    </div>

    <div class="conditionBar">
      <router-link
        to="/search/community"
        style="text-decoration: none; display: flex; align-items: center"
      >
        <div
          class="searchOption"
          style="display: flex; justify-content: center"
        >
          <v-icon
            v-bind:color="communityColor"
            icon="mdi-account-multiple"
          ></v-icon>
          <p>커뮤니티 검색</p>
        </div>
      </router-link>
      <router-link
        to="/search/draft"
        style="text-decoration: none; display: flex; align-items: center"
      >
        <div style="width: 1rem; height: 1rem"></div>
        <div
          class="searchOption"
          style="display: flex; justify-content: center"
        >
          <v-icon v-bind:color="draftColor" icon="mdi-image-album"></v-icon>
          <p>도안 검색</p>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script setup lang="ts">
// Optionally import default styling
import "vue-search-input/dist/styles.css";
import { ref } from "vue";
const searchVal = ref("");
// import { DefineComponent } from 'vue';

const communityColor = ref("purple");
const communitySelect = ref(false);
const draftSelect = ref(false);
const draftColor = ref("purple");
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
  margin-bottom: 0.7rem;
}
input[type="text"]:focus {
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
