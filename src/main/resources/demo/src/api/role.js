import http from './http'

const getRoles = (role) => http.post('/role/select/condition', role)

const addRole = (role) => http.post('/role/insert', role)

const updateRole = (role) => http.post('/role/update', role)

const deleteRole = (roleId) => http.post('/role/delete', roleId)

const getRoleById = (roleId) => http.post('/role/select/id', roleId)

const getRoleMenusByRoleId = (roleId) => http.post('/role/select/menu', roleId)

const getRoleByName = (roleName) => http.post('/role/select/name', roleName)

const getCount = (role) => http.post('/role/select/count', role)

export {
  getRoles, addRole, updateRole, deleteRole, getRoleById, getRoleByName, getCount, getRoleMenusByRoleId
}
