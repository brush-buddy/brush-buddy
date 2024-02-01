import CommunityListviewVue from '@/views/CommunityListview.vue'
import DiaryView from '@/views/DiaryView.vue'
import DraftView from '@/views/DraftView.vue'
import HomeView from '@/views/HomeView.vue'
import SearchView from '@/views/SearchView.vue'
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
        component: CommunityListviewVue
      },
      {
        name: 'diary',
        path: '/diary',
        component: DiaryView
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
      }

  ]
})

export default router
