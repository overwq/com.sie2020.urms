import { getUserRoleByUserLoginName } from '../../api/userrole'
import { getRoles } from '../../api/role'
export default {
  namespaced :true,
  state:{
    roles:{},
    checkedRole:{}
  },
  getters:{
    get_checkedRole(state){
      return state.checkedRole
    },
    get_roles(state){
      return state.roles
    }
  },
  mutations:{
    set_checkedRole(state,val){
      state.checkedRole = val
    },
    set_roles(state,val){
      state.roles = val
    }
  },
  action:{
    //获取角色信息 绑定checked事件
    async get_checkedRole({commit},userLoginName){
      getUserRoleByUserLoginName(userLoginName).then(res=>{
        commit('set_checkedRole',res.data)
        console.log('aaaaaaaabbbbbbbb')
      })
    }
  }
}
