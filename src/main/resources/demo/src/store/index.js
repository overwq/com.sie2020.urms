import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import UserRole from './modules/UserRole'
import { getTree } from '../api/menu'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: { user ,UserRole},
  state: {
    resetState: false, // 重置查询条件状态
    queryState: false,
    crudState: '', // 当前点击事件为crud
    pageSize: 20, // 分页的页码
    pageNum: 1, // 分页的页号
    refreshTag: false, // 页面刷新状态
    tempUser: {}, // 临时对象，保存新增修改对象
    checkBoxShowState: false, // 点击批量删除后的checkbox显示状态
    totalCount: 10,
    MenuTree:[],
    Token:[],
  },
  getters: {
    getResetState (state) {
      return state.resetState
    },
    getQueryState (state) {
      return state.queryState
    },
    getCrudState (state) {
      return state.crudState
    },
    getPageSize (state) {
      return state.pageSize
    },
    getPageNum (state) {
      return state.pageNum
    },
    getRefreshTag (state) {
      return state.refreshTag
    },
    getTempUser (state) {
      return state.tempUser
    },
    getCheckBoxShowState (state) {
      return state.checkBoxShowState
    },
    getTotalCount (state) {
      return state.totalCount
    },
    getMenuTree(state){
      return state.MenuTree
    },
    getToken(state){
      return state.Token
    }
   
  },
  mutations: {
    setResetState (state) {
      state.resetState = !state.resetState
      this.commit('setRefreshTag', true)
    },
    setQueryState (state) {
      state.query = !state.queryState
    },
    setCrudState (state, toState) {
      state.crudState = toState
    },
    setPageSize (state, val) {
      if (typeof (val) === 'number')
      state.pageSize = val
    },
    setPageNum (state, val) {
      if (typeof (val) === 'number')
      state.pageNum = val
    },
    setRefreshTag (state, val) {
      if (typeof (val) === 'boolean') { state.refreshTag = val }
    },
    setTempUser (state, val) {
      state.tempUser = val
    },
    setTotalCount (state, val) {
      if (typeof (val) === 'number')
      state.totalCount = val
    },
    setCheckBoxShowState (state) {
      state.checkBoxShowState = !state.checkBoxShowState
    },
    setMenuTree(state,val){
      state.MenuTree = val
    },
    setToken(state,val){
      state.Token = val
    }
  

  },
  actions: {
    setMenuTree(state){
      getTree().then(res => {
        this.commit('setMenuTree',res.data)
      })
    },
   

  }
})
