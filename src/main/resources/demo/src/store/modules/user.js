import { api_getUsers } from '../../api/user'

export default {
  // namespaced: true,
  state: {
    users: {}
  },
  getters: {
    getUsers (state) {
      return state.users
    }
  },
  mutations: {
    setUsers (state, val) {
      state.users == null ? state.users = {} : state.users = val
    }
  },
  actions: {
    getAjaxQueryUsers (state,queryUser) {
      api_getUsers(queryUser).then(res => {
        state.users = res.data.data
      })
    },

  }

}
