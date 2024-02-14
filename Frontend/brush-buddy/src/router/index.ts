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
import VPaletteList from '../components/Diary/VPaletteList.vue'
import PaletteDetailView from '../views/PaletteDetailView.vue'
import VMyBoard from '../components/Diary/VMyBoard.vue'
import HomeListView from '../views/HomeListView.vue'
import payViewVue from '../views/payView.vue'
import payChargeVue from '../views/payCharge.vue'
import successComponentVue from '../components/Pay/successComponent.vue'
import cancelComponentVue from '../components/Pay/cancelComponent.vue'
import failCompoenentVue from '../components/Pay/failCompoenent.vue'


const router = createRouter({
  history: createWebHistory(""),
  routes: [

      {
        name: 'home',
        path: '/',
        component: HomeView
      },
      {
        name: 'homeList',
        path: '/home',
        component: HomeListView
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
          {
            path: '/diary/myboard',
            component: VMyBoard
          },
          {
            path: '/diary/palette',
            component: VPaletteList
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
      name: 'paletteDetail',
      path: '/palette/:id',
      component:PaletteDetailView

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
    },
    {
      name: 'pay',
      path: '/pay',
      children: [
        {
          path: '/pay',
          component: DiaryView
        },
        {
          path: '/pay/success',
          component: successComponentVue
        },
        {
          path: '/pay/cancel',
          component: cancelComponentVue
        },
        {
          path: '/pay/fail',
          component: failCompoenentVue
        }
      ]
    },
    {
      name: 'charge',
      path: '/charge',
      component: payChargeVue,
    },

  ]
})

export default router;
