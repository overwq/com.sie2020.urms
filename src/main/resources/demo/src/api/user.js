import http from './http'

const login = (user) => http.post('/login', user)

const api_getUsers = (user) => http.post('/user/select/condition', user)

const api_addUser = (user) => http.post('/user/insert', user)

const api_deleteUser = (userId) => http.post('/user/delete', userId)

const api_updateUser = (user) => http.post('/user/update', user)

const api_getUserById = (userId) => http.post('/user/select/id', userId)

const getUserByName = (userName) => http.post('/user/select/name', userName)

const getUserMenu = (userLoginName) => http.post('/user/menu', userLoginName)

const api_userGetCount = (user) => http.post('/user/select/count', user)

export {
  login, api_getUsers, api_addUser, api_deleteUser, api_updateUser, api_getUserById, getUserByName, getUserMenu, api_userGetCount
}
