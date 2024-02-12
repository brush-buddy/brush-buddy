<template>
        <div class="sticky">
      <div id="tab">
        <router-link to="/diary">
            <div class="tabButton">만든 도안</div>
        </router-link>
        <router-link to="/diary/purchaseDraft">
            <div class="tabButton">구매 도안</div>
        </router-link>
        <router-link to="/diary/bookmarkDraft">
            <div class="tabButton">북마크 도안</div>
        </router-link>
        <router-link to="/diary/heartList">
            <div class="tabButton">찜한 글</div>
        </router-link>
      </div>
    </div>
</template>
<script setup lang="ts">
import {RouterLink} from 'vue-router';
</script>
<style scoped>
.sticky {
  position: sticky;
  top: 0px;
  line-height: 4rem;
  background-color: #ffffff;
}
#tab {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
</style>