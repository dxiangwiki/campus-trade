import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  { path: '/login', component: () => import('@/views/Login.vue') },
  { path: '/register', component: () => import('@/views/Register.vue') },
  {
    path: '/',
    component: () => import('@/views/Layout.vue'),
    redirect: '/products',
    children: [
      { path: 'products', component: () => import('@/views/ProductList.vue') },
      { path: 'publish', component: () => import('@/views/ProductPublish.vue') },
      { path: 'orders', component: () => import('@/views/OrderManage.vue') },
      { path: 'user', component: () => import('@/views/UserCenter.vue') }
    ]
  }
]

const router = new VueRouter({
  mode: 'hash',           // 用 hash 模式，不需要服务器配置路由重写
  base: process.env.BASE_URL,
  routes
})

// 路由守卫：未登录跳登录
router.beforeEach((to, from, next) => {
  const user = localStorage.getItem('user')
  const publicPaths = ['/login', '/register']
  if (!publicPaths.includes(to.path) && !user) {
    next('/login')
  } else {
    next()
  }
})

export default router