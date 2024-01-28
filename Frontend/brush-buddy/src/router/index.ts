import CommunityViewVue from '@/views/CommunityView.vue'
import DiaryVIewVue from '@/views/DiaryVIew.vue'
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
        path: '/',
        name: 'CommunityView',
        component: CommunityViewVue
      },
      {
        path: '/',
        name: 'DiaryView',
        component: DiaryVIewVue
      },
      {
        path: '/',
        name: 'SearchView',
        component: SearchViewVue
      }

  ]
})

export default router
