import axios from 'axios'
import { Message } from 'element-ui'
import vuex from '../store/index'
const intstance = axios.create()

intstance.interceptors.request.use(config => {
  config.baseURL = 'http://localhost:8080'
  config.headers.post['Content-Type'] = 'application/json'

  const url = config.url.trim().split('/')
  if (url[1] === 'login') return config

  const permissions = JSON.parse(localStorage.getItem('permissions'))
  if (!permissions && permissions.length > 0) {
    vuex.commit('setToken', permissions)
  }
  const headUrl = url[1]
  const endUrl = url[2]
  let pass = false
  if (permissions && permissions.length > 0) {
    permissions.some(p => {
      const permission = p.split('/')
      const uHeadUrl = permission[0]
      const uEndUrl = permission[1]
      if ((uEndUrl === '*' || uEndUrl === endUrl) && (uHeadUrl === '' || uHeadUrl === headUrl)) {
        pass = true
        return true
      }
    })
  }

  if (pass) {
    config.headers.token = permissions
    return config
  } else {
    config.url = '/user/no/permission'
    return config
  }
})

intstance.interceptors.response.use(res => {
  if (res.data.message) {
    Message.closeAll()
    if (res.data.state === 200) {
      Message.success(res.data.message)
    } else {
      Message.error(res.data.message)
    }
  }
  return res
})

export default intstance
