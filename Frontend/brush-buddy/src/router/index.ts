import CommunityViewVue from '@/views/CommunityView.vue'
import DiaryVIewVue from '@/views/DiaryView.vue'
import DraftViewVue from '@/views/DraftView.vue'
import HomeViewVue from '@/views/HomeView.vue'
import SearchViewVue from '@/views/SearchView.vue'
import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    
      {
        path: '/',
        name: 'HomeView',
        component: HomeViewVue
      },
      {
        path: '/community',
        name: 'CommunityView',
        component: CommunityViewVue
      },
      {
        path: '/diary',
        name: 'DiaryView',
        component: DiaryVIewVue
      },
      {
        path: '/search',
        name: 'SearchView',
        component: SearchViewVue
      },
      {
        path: '/draft',
        name: 'DraftView',
        component: DraftViewVue
      }

  ]
})

export default router
