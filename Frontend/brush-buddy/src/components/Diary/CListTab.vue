<template>
  <div class="sticky">
    <div id="tab">
      <router-link to="/diary" @click="setActiveTab('made')">
        <div :class="{ activeTab: activeTab === 'made' }">만든 도안</div>
      </router-link>
      <router-link to="/diary/purchaseDraft" @click="setActiveTab('purchase')">
        <div :class="{ activeTab: activeTab === 'purchase' }">구매 도안</div>
      </router-link>
      <router-link to="/diary/bookmarkDraft" @click="setActiveTab('bookmark')">
        <div :class="{ activeTab: activeTab === 'bookmark' }">북마크 도안</div>
      </router-link>
      <!-- <router-link to="/diary/heartList" @click="setActiveTab('heart')">
        <div :class="{ activeTab: activeTab === 'heart' }">좋아요글</div>
      </router-link> -->
      <router-link to="/diary/myboard" @click="setActiveTab('board')">
        <div :class="{ activeTab: activeTab === 'board' }">작성글</div>
      </router-link>
      <router-link to="/diary/palette" @click="setActiveTab('palette')">
        <div :class="{ activeTab: activeTab === 'palette' }">내 팔레트</div>
      </router-link>
    </div>
  </div>
</template>
<script setup lang="ts">
import { RouterLink } from 'vue-router'
import { ref } from 'vue'
const activeTab = ref<string | null>('made')
const setActiveTab = (path: string) => {
  activeTab.value = path
}
import { watch, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

const updateTabFromUrl = () => {
      const path = router.currentRoute.value.path;
      console.log(path)
      switch (path) {
        case '/diary':
        activeTab.value = 'made';
          break;
        case '/diary/purchaseDraft':
        activeTab.value = 'purchase';
          break;
        case '/diary/bookmarkDraft':
        activeTab.value = 'bookmark';
          break;
        case '/diary/myboard':
          activeTab.value = 'board';
           break;
        case '/diary/palette':
          activeTab.value = 'palette';
          break;
      }
    };

watch(route, updateTabFromUrl);
onMounted(
  () => {
    updateTabFromUrl()
  }
)
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
  overflow-x: auto;
  white-space: nowrap;
  width: 100vw;
}
#tab::-webkit-scrollbar {
  display: none;
}
a {
  text-decoration: none;
  color: black;
}
.activeTab {
  font-size: large;
  font-weight: bold;
  color: #ae67e4;
}
</style>
