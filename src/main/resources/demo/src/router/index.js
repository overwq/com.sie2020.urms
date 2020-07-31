import Vue from 'vue'
import VueRouter from 'vue-router'
import { Message } from 'element-ui'
Vue.use(VueRouter)

const routes = [
  {
    path: '/container',
    component: () =>
      import('../components/Container.vue'),
    children: [{
      path: 'user',
      component: () =>
        import('../page/user/User.vue')
    },
    {
      path: 'role',
      component: () =>
        import('../page/role/Role.vue')
    },
    {
      path: 'menu',
      component: () =>
        import('../page/menu/Menu.vue')
    },
    {
      path: 'home',
      component: () =>
        import('../page/Home.vue')
    }
    ]
  },

  {
    path: '/login',
    name: 'Login',
    component: () =>
      import('../page/Login.vue')
  }

]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  const user = sessionStorage.getItem('user')
  if (to.path === '/login' || (user && user.length > 0)) {
    next()
  } else {
    Message.closeAll()
    Message.warning('请先登陆')
    next('/login')
  }
})

export default router
