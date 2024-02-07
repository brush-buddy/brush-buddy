import CommunityDetailView from '../views/CommunityDetailView.vue'
import CommunityListView from '../views/CommunityBoardListView.vue'
import DiaryView from '../views/DiaryView.vue'
import DraftView from '../views/DraftView.vue'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import DraftCreateAIView from '../views/DraftCreateAIView.vue'
import DraftWriteView from '../views/DraftWrite.vue'
import VMadeDraft from '../components/Diary/VMadeDraft.vue'
import VPurchaseDraft from '../components/Diary/VPurchaseDraft.vue'
import VBookmarkDraft from '../components/Diary/VBookmarkDraft.vue'
import VHeartList from '../components/Diary/VHeartList.vue'
import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(""),
  routes: [

      {
        name: 'home',
        path: '/',
        component: HomeView
      },
      {
        name: 'community',
        path: '/community',
        component: CommunityListView
      },
      {
        name: 'diary',
        path: '/diary',
        component: DiaryView,
        children: [
          {
            path: '/diary',
            component: VMadeDraft
          },
          {
            path: '/diary/purchaseDraft',
            component: VPurchaseDraft
          },
          {
            path: '/diary/bookmarkDraft',
            component: VBookmarkDraft
          },
          {
            path: '/diary/heartList',
            component: VHeartList
          },
        ]
      },
      {
        name: 'search',
        path: '/search',
        component: SearchView
      },
      {
        name: 'draft',
        path: '/draft',
        component: DraftView
      },
      {
        name: 'boardDetail',
        path: '/community/:id',
        component:CommunityDetailView,

      },
      {
        name: 'boardAIView',
        path : "/draft/aiprompt",
        component : DraftCreateAIView,
    },
    {
      name: 'boardWriteView',
      path : "/community/write",
      component : DraftWriteView,
    }

  ]
})

export default router;
