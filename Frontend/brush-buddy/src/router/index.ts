import CommunityDetailView from '../views/CommunityDetailView.vue'
import CommunityListView from '../views/CommunityListView.vue'
import DiaryView from '../views/DiaryView.vue'
import DraftView from '../views/DraftView.vue'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
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
      },
      {
        name: 'boardDetail',
        path: '/community/:id',
        component:CommunityDetailView,

      }

  ]
})

export default router;
