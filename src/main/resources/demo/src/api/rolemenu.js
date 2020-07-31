import http from './http'

const getRoleMenuById = (roleMenuId) => http.post('/rolemenu/select/id', roleMenuId)

const getRoleMenuByRoleId = (roleId) => http.post('/rolemenu/select/roleid', roleId)

const addRoleMenu = (roleMenu) => http.post('/rolemenu/insert', roleMenu)

const deleteRoleMenu = (roleMenuId) => http.post('/rolemenu/delete', roleMenuId)

export {
  getRoleMenuById, getRoleMenuByRoleId, addRoleMenu, deleteRoleMenu
}
