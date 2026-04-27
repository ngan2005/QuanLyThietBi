import { createRouter, createWebHistory } from 'vue-router'
import Dashboard from '../views/Dashboard.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: Dashboard
    },
    {
      path: '/devices',
      name: 'devices',
      component: () => import('../views/DeviceList.vue')
    },
    {
      path: '/employees',
      name: 'employees',
      component: () => import('../views/EmployeeList.vue')
    }
  ]
})

export default router
