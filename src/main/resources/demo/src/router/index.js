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
<<<<<<< HEAD
    component: () =>
      import('../page/Login.vue')
<<<<<<< Branch_wqh_test
=======
  },
  {
    path: '/',
    component: () =>
      import('../page/Login.vue')
=======
    component: () => import('../page/Login.vue')
  },
  {
    path: '/',
    component: () => import('../page/Login.vue')
>>>>>>> zhenghe
>>>>>>> local
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
<<<<<<< Branch_wqh_test
  const user = sessionStorage.getItem('user')
=======
<<<<<<< HEAD
  const user = localStorage.getItem('user')
>>>>>>> local
  if (to.path === '/login' || (user && user.length > 0)) {
    next()
  } else {
    Message.closeAll()
    Message.warning('请先登陆')
    next('/login')
  }
=======
  next()
  // const user = localStorage.getItem('user')
  // if (to.path === '/login' || (user && user.length > 0)) {
  //   next()
  // } else {
  //   Message.closeAll()
  //   Message.warning('请先登陆')
  //   next('/login')
  // }
>>>>>>> zhenghe
})

export default router
