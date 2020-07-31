import axios from 'axios'
import { Message } from 'element-ui'
const intstance = axios.create()
import vuex from '../store/index'

intstance.interceptors.request.use(config => {
  config.baseURL = 'http://localhost:8080'
  config.headers.post['Content-Type'] = 'application/json'

  let url = config.url.trim().split('/')
  if(url[1] === 'login') return config
  let permissions = JSON.parse(sessionStorage.getItem('permissions'))
  if(!permissions && permissions.length > 0){
    vuex.commit('setToken',permissions)
  }
  let headUrl = url[1]
  let endUrl = url[2]
  let pass = false
  if(permissions && permissions.length > 0){
    permissions.some(p => {
      let permission = p.split('/')
      let uHeadUrl = permission[0]
      let uEndUrl  = permission[1]
      if ((uEndUrl === '*' || uEndUrl === endUrl) && (uHeadUrl === '' || uHeadUrl === headUrl)){
        return pass = true
      }
    })
  }

  if (pass){
    config.headers.token = permissions
    return config
  }else {
    let menus = JSON.parse(sessionStorage.getItem('menu'))
    let menu =  menus.filter( m => m.menuUrl === config.url.substr(1,config.url.length-1))
    config.url = '/user/no/permission'
    return config
  }
})

intstance.interceptors.response.use(res => {

  if (res.data.message) {
    Message.closeAll()
    if (res.data.state === 400){
      Message.error(res.data.message)
    }else {
      Message.success(res.data.message)
    }

  }

  return res
})

export default intstance

