import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import qs from 'qs'
// axios.defaults.baseURL = 'http://localhost:8080'
// axios.defaults.headers.post['Content-Type'] = 'application/json'

Vue.config.productionTip = false
Vue.prototype.$qs = qs
Vue.use(VueAxios, axios)
Vue.use(Vuex)
Vue.use(Element, { size: 'middle' })
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')
