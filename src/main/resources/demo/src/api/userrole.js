import http from './http'

const getUserRoleByUserLoginName = (name) => http.post('/userrole/select/userloginname', name)

const getUserRoleByCondition = (condition) => http.post('/userrole/select/condition', condition)

const addUserRole = (userRole) => http.post('/userrole/insert', userRole)

const delUserRole = (userRoleId) => http.post('/userrole/delete', userRoleId)

const updateUserRole = (userRole) => http.post('/userrole/update', userRole)

const getUserRoleById = (userRoleId) => http.post('/userrole/select/id', userRoleId)

export {
  getUserRoleByUserLoginName, getUserRoleByCondition, addUserRole, delUserRole, updateUserRole, getUserRoleById
}
