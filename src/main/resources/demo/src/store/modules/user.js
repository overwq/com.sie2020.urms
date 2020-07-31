export default {
  namespaced: true,
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

  }

}
