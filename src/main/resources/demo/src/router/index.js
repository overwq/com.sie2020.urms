import Vue from 'vue'
import VueRouter from 'vue-router'
// import { Message } from 'element-ui'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../page/HelloWorld.vue')
  },
  {

    path: '/container',
    component: () => import('../components/Container.vue'),
    children: [
      {
        path: 'user',
        component: () => import('../page/user/User.vue')
      },
      {
        path: 'role',
        component: () => import('../page/role/Role.vue')
      },
      {
        path: 'menu',
        component: () => import('../page/menu/Menu.vue')
      },
      {
        path: 'home',
        component: () => import('../page/Home.vue')
      }
    ]
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import('../page/Login.vue')
  },
  {
    path: '/',
    component: () => import('../page/Login.vue')
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  next()
  // const user = localStorage.getItem('user')
  // if (to.path === '/login' || (user && user.length > 0)) {
  //   next()
  // } else {
  //   Message.closeAll()
  //   Message.warning('请先登陆')
  //   next('/login')
  // }
})

export default router
