import CommunityDetailView from '../views/CommunityDetailView.vue'
import CommunityListView from '../views/CommunityBoardListView.vue'
import DiaryView from '../views/DiaryView.vue'
import DraftView from '../views/DraftView.vue'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
<<<<<<< HEAD
import DraftCreateAIView from '../views/DraftCreateAIView.vue'
=======
import LoginView from '../views/LoginView.vue'
>>>>>>> fd330c6f24f457fdb9b341788e7dea64f03c3035
import VMadeDraft from '../components/Diary/VMadeDraft.vue'
import VPurchaseDraft from '../components/Diary/VPurchaseDraft.vue'
import VBookmarkDraft from '../components/Diary/VBookmarkDraft.vue'
import VHeartList from '../components/Diary/VHeartList.vue'
<<<<<<< HEAD
import WriteComponentVue from '@/components/Community/WriteComponent.vue'
=======
import DraftCreateAIView from '../views/DraftCreateAIView.vue'
import DraftWriteView from '../views/DraftWrite.vue'
>>>>>>> fd330c6f24f457fdb9b341788e7dea64f03c3035
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
        name: 'login',
        path: '/login',
        component: LoginView
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

<<<<<<< HEAD
      },
=======
    },
>>>>>>> fd330c6f24f457fdb9b341788e7dea64f03c3035
      {
        name: 'boardAIView',
        path : "/draft/aiprompt",
        component : DraftCreateAIView,
    },
    {
      name: 'boardWriteView',
      path : "/community/write",
<<<<<<< HEAD
      component : WriteComponentVue,
=======
      component : DraftWriteView,
>>>>>>> fd330c6f24f457fdb9b341788e7dea64f03c3035
    }

  ]
})

export default router;
