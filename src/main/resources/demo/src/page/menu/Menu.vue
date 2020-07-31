<template>
  <div class="tableStyle">
    <menuHead @query-menus-coming="setMenus"></menuHead>
    <menuAdd></menuAdd>
    <menuUpdate :menuId="updateMenuId"></menuUpdate>
     <el-table stripe  :data="setShowMenus"  height="645">
        <el-table-column label="全选"></el-table-column>
        <el-table-column label="序号">
          <template slot-scope="menu">
            <span>{{menu.$index+1}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="menuName" label="菜单名称" min-width="85"></el-table-column>
        <el-table-column prop="menuParentName" label="上级菜单"></el-table-column>
        <el-table-column prop="menuType" label="菜单类型"></el-table-column>
        <el-table-column prop="menuOpcode" label="菜单标识"></el-table-column>
        <el-table-column prop="menuUrl" label="菜单地址"></el-table-column>
        <el-table-column prop="menuQueueNumber" label="排序号"></el-table-column>
        <el-table-column prop="menuStatus" label="状态"></el-table-column>
        <el-table-column label="操作" min-width="330" fixed="right">
          <template slot-scope="menu" class="btn-wth">
            <el-button type="warning" round class="btn-wth" @click="setUpdateMenuId(menu.row.menuId),setCrudState('updateMenu')">修改</el-button>
            <el-button type="danger" round class="btn-wth" @click="deleteMenuInfo(menu.row.menuId)">删除</el-button>
            <el-button type="info" round class="btn-wth" @click="disableState(menu.row.menuId)">无效</el-button>
          </template>
        </el-table-column>

      </el-table>
    <pageination></pageination>
  </div>
</template>

<script>
import MenuHead from './MenuHead'
import Pageination from '../public/Pageination'
import { mapMutations } from 'vuex'
import { deleteMenu, updateMenu } from '../../api/menu'
import MenuAdd from './MenuAdd'
import MenuUpdate from './MenuUpdate'
export default {
  name: 'Menu',
  components: {
    MenuHead, Pageination, MenuAdd, MenuUpdate
  },
  data () {
    return {
      menus: [],
      updateMenuId: 0,
      disableMenu: {
        menuId: '',
        menuStatus: ''
      }
    }
  },
  methods: {
    ...mapMutations(['setTempUser', 'setRefreshTag', 'setCrudState']),
    setMenus (menus) {
      this.menus = menus
    },
    deleteMenuInfo (id) {
      this.$confirm('确认要删除该用户吗？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteMenu(id).then(res => {
          this.setRefreshTag(true)
        })
      })
    },
    setUpdateMenuId (val) {
      // console.log(val)
      this.updateMenuId = val
    },
    disableState (menuId) {
      this.disableMenu.menuId = menuId
      this.disableMenu.menuStatus = 0
      updateMenu(JSON.stringify(this.disableMenu)).then(res => {
        this.setRefreshTag(true)
      })
    }

  },
  computed: {
    setShowMenus () {
      if (this.menus && this.menus.length > 0){
        this.menus.forEach(menu => {
          if (menu.menuStatus === 1) {
            menu.menuStatus = '有效'
          } else if (menu.menuStatus === 0) {
            menu.menuStatus = '无效'
          } else if (menu.menuHidden === 1) {
            menu.menuStatus = '删除'
          }
        })
      }
      return this.menus
    }
  }

}
</script>
<style lang="less">
@import url('../../style/TableStyle.less');
</style>
