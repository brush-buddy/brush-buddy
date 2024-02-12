import CommunityDetailView from '../views/CommunityDetailView.vue'
import CommunityListView from '../views/CommunityBoardListView.vue'
import DiaryView from '../views/DiaryView.vue'
import DraftView from '../views/DraftView.vue'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import LoginView from '../views/LoginView.vue'
import VMadeDraft from '../components/Diary/VMadeDraft.vue'
import VPurchaseDraft from '../components/Diary/VPurchaseDraft.vue'
import VBookmarkDraft from '../components/Diary/VBookmarkDraft.vue'
import VHeartList from '../components/Diary/VHeartList.vue'
import DraftCreateAIView from '../views/DraftCreateAIView.vue'
import VWrite from '../components/Community/VWrite.vue'
import { createRouter, createWebHistory } from 'vue-router'
import SDraftListComponentVue from '../components/Search/SDraftListComponent.vue'
import SCommunityListComponentVue from '../components/Search/SCommunityListComponent.vue'
import DraftPipoMakeViewVue from '../views/DraftPipoMakeView.vue'
import DraftDetailView from '../views/DraftDetailView.vue'

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
        component: SearchView,
        children: [
          {
            path: '/search',
            component: SCommunityListComponentVue,
          },
          {
            path: '/search/community',
            component: SCommunityListComponentVue,
          },
          
          {
            path: '/search/draft',
            component: SDraftListComponentVue,
          },
          
          
        ]
      },
      {
        name: 'draft',
        path: '/draft',
        component: DraftView
        // children : [{
        //   name: 'ai-create',
        //   path: 'aiCreate',
        //   component: () => import('../views/DraftCreateAIView.vue')
        // }]
      },{
        name: 'ai-create',
        path: '/aiCreate',
        component: () => import('../views/DraftCreateAIView.vue')
      }
      ,
      {
        name: 'boardDetail',
        path: '/community/:id',
        component:CommunityDetailView,

    },
    {
      name: 'draftDetail',
      path: '/draft/:id',
      component: DraftDetailView,

    },
      {
        name: 'boardAIView',
        path : "/draft/aiprompt",
        component : DraftCreateAIView,
    },
    {
      name: 'boardWriteView',
      path : "/community/write",
      component : VWrite,
    },
    {
      name: 'draftWriteView',
      path: "/draft/write",
      component: DraftPipoMakeViewVue,
    }

  ]
})

export default router;
