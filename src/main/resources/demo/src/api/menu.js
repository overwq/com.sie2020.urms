import http from './http'

const getMenus = (menu) => http.post('/menu/select/condition', menu)

const addMenu = (menu) => http.post('/menu/insert', menu)

const deleteMenu = (menuId) => http.post('/menu/delete', menuId)

const updateMenu = (menu) => http.post('/menu/update', menu)

const getMenuById = (menuId) => http.post('/menu/select/id', menuId)

const getOptionValues = () => http.post('/menu/select/options')

const getTree = () => http.post('/menu/select/tree')

const getCount = (menu) => http.post('/menu/select/count', menu)

// const getMenuByName = (menuName) => http.post('/menu/select/name', menuName)

export {
  getMenus, addMenu, deleteMenu, updateMenu, getMenuById, getOptionValues, getCount, getTree
}
