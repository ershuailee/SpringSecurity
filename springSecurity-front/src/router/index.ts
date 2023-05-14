import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/blog/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      children: [
        {
          path: 'child',
          name: 'Child',
          component:  () => import('../views/blog/test.vue'),
        },
      ],
    },
    {
      path: '/admin',
      name: 'admin',
      component: HomeView,
      children: [
        {
          path: 'child',
          name: 'Child',
          component:  () => import('../views/admin/HomeView.vue'),
        },
      ],
    },
  ]
})

export default router
