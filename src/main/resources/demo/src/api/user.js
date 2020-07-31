import http from './http'

const login = (user) => http.post('/login', user)

const getUsers = (user) => http.post('/user/select/condition', user)

const addUser = (user) => http.post('/user/insert', user)

const deleteUser = (userId) => http.post('/user/delete', userId)

const updateUser = (user) => http.post('/user/update', user)

const getUserById = (userId) => http.post('/user/select/id', userId)

const getUserByName = (userName) => http.post('/user/select/name', userName)

const getUserMenu = (userLoginName) => http.post('/user/menu', userLoginName)

const getCount = (user) => http.post('/user/select/count', user)

export {
  login, getUsers, addUser, deleteUser, updateUser, getUserById, getUserByName, getUserMenu, getCount
}
