<template>
  <div><!--v-on:open="setCheckedKeys"-->
    <el-dialog :visible.sync="dialogShow" center width="700px"  v-on:open="getCheckedMenuTrees" v-on:close="resetChecked">
      <div class="UserAddSlot"><slot></slot></div>
      <el-tree
        :data="menuTree"
        show-checkbox
        default-expand-all
        node-key="menuId"
        ref="tree"
        highlight-current
        :props="defaultProps">
      </el-tree>

      <div class="buttons">
        <el-button @click="updateRoleMenu">设置角色菜单</el-button>
<!--        <el-button @click="getCheckedNodes">通过 node 获取</el-button>-->
        <el-button @click="getCheckedKeys">通过 key 获取</el-button>
<!--        <el-button @click="setCheckedNodes">通过 node 设置</el-button>-->
<!--        <el-button @click="setCheckedKeys">通过 key 设置</el-button>-->
        <el-button @click="resetChecked">清空</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import { getRoleMenuByRoleId, addRoleMenu, deleteRoleMenu } from '../../api/rolemenu'
import { Message } from 'element-ui'
export default {
  props: {
    roleId: ''
  },
  data () {
    return {
      checkedBeforeChange: [],
      roleMenus: [],
      defaultProps: {
        children: 'children',
        label: 'menuName' // menuName
      }

    }
  },
  mounted: function () {
    this.$store.dispatch('setMenuTree')
    this.getCheckedMenuTrees()
  },
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    getCheckedNodes () {
      console.log(this.$refs.tree.getCheckedNodes())
      console.log(this.$refs.tree)
      console.log(this.$refs.tree.getNodeKey)
    },
    getCheckedKeys () {
      console.log(this.$refs.tree.getCheckedKeys())
    },
    setCheckedNodes () {
      this.$refs.tree.setCheckedNodes([{
        id: 5,
        label: '二级 2-1'
      }, {
        id: 9,
        label: '三级 1-1-1'
      }])
    },
    setCheckedKeys () {
      this.$refs.tree.setCheckedKeys(this.selectedRoleId)
    },
    resetChecked () {
      this.$refs.tree.setCheckedKeys([])
    },
    getCheckedMenuTrees () {
      getRoleMenuByRoleId(this.roleId).then(res => {
        const roleMenus = res.data.data
        this.roleMenus = roleMenus
        let tempMenuIds = []
        if (roleMenus && roleMenus.length > 0) {
          roleMenus.forEach(sid => {
            tempMenuIds = [...tempMenuIds, sid.menuId]
          })
        }
        this.checkedBeforeChange = tempMenuIds
        this.$refs.tree.setCheckedKeys(tempMenuIds)
      })
    },
    addRoleMenuRM (addRoleMenus) {
      let roleMenus = []
      if (addRoleMenus && addRoleMenus.length > 0) {
        addRoleMenus.forEach(ck => {
          const roleMenu = {
            roleId: this.roleId,
            menuId: ck
          }
          roleMenus = [...roleMenus, roleMenu]
        })
      }
      addRoleMenu(roleMenus)
    },
    deleteRoleMenuRM (deleteRoleMenus) {
      // deleteRoleMenus.forEach( drm => {
      //   deleteRoleMenu(drm.roleMenuId)
      // })
      let roleMenuIds = []
      if (deleteRoleMenus && deleteRoleMenus.length > 0) {
        deleteRoleMenus.forEach(drm => {
          roleMenuIds = [...roleMenuIds, drm.roleMenuId]
        })
      }
      deleteRoleMenu(roleMenuIds)
    },
    updateRoleMenu () {
      try {
        const checkedBefore = this.roleMenus
        const checkedNow = this.$refs.tree.getCheckedKeys()
        const addRoleMenus = checkedNow.filter(cn => !checkedBefore.find(cb => cb.menuId === cn))
        const deleteRoleMenus = checkedBefore.filter(cb => !checkedNow.find(cn => cn === cb.menuId))
        if (addRoleMenus.length > 0) {
          this.addRoleMenuRM(addRoleMenus)
        }
        if (deleteRoleMenus.length > 0) {
          this.deleteRoleMenuRM(deleteRoleMenus)
        }
      } catch (error) {
        Message.error('你没有设置角色菜单的权限')
      }

      this.dialogShow = false
    }

  },
  computed: {
    ...mapGetters(['getCrudState']),
    menuTree () {
      return this.$store.getters.getMenuTree
    },
    dialogShow: {
      get () {
        if (this.getCrudState === 'roleMenu') {
          return true
        }
        return false
      },
      set () {
        this.setCrudState('')
      }
    }
  }
}
</script>
