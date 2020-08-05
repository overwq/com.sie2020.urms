<template>
  <div class="tableStyle">
    <el-dialog :visible.sync="dialogShow" center width="800px" title="设置用户所属角色" v-on:open="setUserRole()" >
    <div class="UserAddSlot" style="margin:-40px 0 0 0px;text-align:left;">用户名 :<slot></slot>查看菜单前请先点击角色</div>
     <el-table  @row-click="getCheckedMenuTrees" stripe  :data="showRoles"  height="480" ref="userRoleTable" @selection-change="selectionChange">
       <el-table-column type="selection" width="55"></el-table-column>
       <el-table-column type="expand" label-position="left" label="查看菜单" width="90px">
         <template>
            <el-tree
            :data="cmenuTree"
            default-expand-all
            node-key="menuId"
            ref="tree"
            :props="defaultProps">
          </el-tree>
         </template>
       </el-table-column>
        <el-table-column label="序号" min-width="50">
          <template slot-scope="role">
            <span>{{role.$index+1}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="roleCode" label="角色编码"></el-table-column>
        <el-table-column prop="roleName" label="角色名称"></el-table-column>
        <el-table-column prop="roleCreateTime" label="生效日期" min-width="100"></el-table-column>
        <el-table-column prop="roleInvalidTime" label="失效日期" min-width="100"></el-table-column>
        <el-table-column prop="roleStatus" label="状态" min-width="50"></el-table-column>

      </el-table>
      <div class="dialog-footer" slot="footer">
        <el-button @click="updateUserRoleUR" type="primary" class="biggerBtn">设置角色</el-button>
        <el-button @click="dialogShow = false" type="info" class="biggerBtn">取消</el-button>
      </div><!--dialogShow = false-->
      <div class="dialog_pageination">
        <pageination :noUsingVuex="true" @dialogPageSize="getDialogPageSize" @dialogPageNum="getDialogPageNum"></pageination>
      </div>
      <div class="el-checkboxHidden">选择角色</div>
    </el-dialog>

  </div>
</template>

<script>
import Pageination from '../public/Pageination'
import { mapMutations, mapGetters } from 'vuex'
import { getRoles, getCount, getRoleMenusByRoleId } from '../../api/role'
// import { getRoleMenuByRoleId } from '../../api/rolemenu'
import { addUserRole, delUserRole, getUserRoleByUserLoginName } from '../../api/userrole'
export default {
  name: 'Role',
  data () {
    return {
      role: {
        roleId: '',
        roleName: '',
        roleStatus: '',
        roleCode: '',
        pageSize: 20,
        pageNum: 1
      },
      oldCheckedRoleIds: [{}],
      roles: [],
      defaultProps: {
        children: 'children',
        label: 'menuName' // menuName
      },
      checkedRole: {},
      menuTree: []
    }
  },
  components: {
    Pageination
  },
  props: {
    checkedUser: Object
  },
  mounted: function () {
    this.getRolesUR()
    this.$store.dispatch('setMenuTree')
  },
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    getDialogPageSize (val) {
      this.dialogPageSize = val
    },
    getDialogPageNum (val) {
      this.dialogPageNum = val
    },
    getRolesUR () {
      getRoles(this.role).then(res => {
        this.roles = res.data.data
        this.setUserRole()
        this.setRoleTotalCount()
      })
    },
    setRoles (roles) {
      this.roles = roles
    },
    selectionChange (val) {
      // console.log(this.$refs.userRoleTable.)
      this.checkedRole = val
    },
    getCheckedUserRole () {
      getUserRoleByUserLoginName(this.checkedUser.userLoginName).then(res => {
        this.oldCheckedRoleIds = res.data.data
        if (this.oldCheckedRoleIds && this.oldCheckedRoleIds.length > 0) {
          this.oldCheckedRoleIds.forEach(or => {
            const checkedSelections = this.roles.find(r => r.roleId === or.roleId)
            if (this.$refs.userRoleTable) {
              this.$refs.userRoleTable.toggleRowSelection(checkedSelections)
            }
          })
        }
      })
    },
    updateUserRoleUR () {
      const addUserRoles = this.checkedRole.filter(cr => !this.oldCheckedRoleIds.find(or => or.roleId === cr.roleId))
      const deleteUserRoles = this.oldCheckedRoleIds.filter(or => !this.checkedRole.find(cr => cr.roleId === or.roleId))
      if (addUserRoles.length > 0) {
        this.addCheckedUserRole(addUserRoles)
      }
      if (deleteUserRoles.length > 0) {
        this.deleteUserRole(deleteUserRoles)
      }
      this.dialogShow = false
    },
    setUserRole () {
      if (this.$refs.userRoleTable) {
        this.$refs.userRoleTable.clearSelection()
      }
      this.getCheckedUserRole()
    },
    deleteUserRole (deleteUserRoles) {
      //  deleteUserRoles.forEach(userRole => {
      //   delUserRole(userRole.userRoleId)
      // })
      let userRoleIds = []
      if (deleteUserRoles && deleteUserRoles.length > 0) {
        deleteUserRoles.forEach(ur => {
          userRoleIds = [...userRoleIds, ur.userRoleId]
        })
        delUserRole(userRoleIds)
      }
    },
    addCheckedUserRole (addUserRoles) {
      let userRoles = []
      if (addUserRoles && addUserRoles.length > 0) {
        addUserRoles.forEach(r => {
          const userRole = {
            userLoginName: this.checkedUser.userLoginName,
            userId: this.checkedUser.userId,
            roleName: r.roleName,
            roleId: r.roleId
          }
          userRoles = [...userRoles, userRole]
          addUserRole(userRoles)
        })
      }
    },
    setRoleTotalCount () {
      this.countRole = JSON.parse(JSON.stringify(this.role))
      getCount(this.countRole).then(res => {
        this.$store.commit('setTotalCount', res.data)
      })
    },
    getCheckedMenuTrees (role) {
      const roleId = role.roleId
      getRoleMenusByRoleId(roleId).then(res => {
        this.cmenuTree = res.data.data
      })
    }

  },
  computed: {
    ...mapGetters(['getCrudState']),
    dialogPageSize: {
      get () {
        return this.role.pageSize
      },
      set (val) {
        this.role.pageSize = val
        this.getRolesUR()
      }
    },
    dialogPageNum: {
      get () {
        return this.role.pageNum
      },
      set (val) {
        this.role.pageNum = val
        this.getRolesUR()
      }
    },
    showRoles () {
      if (this.roles && this.roles.length > 0) {
        this.roles.forEach(role => {
          if (role.roleStatus === 0) {
            role.roleStatus = '无效'
          } else if (role.roleStatus === 1) {
            role.roleStatus = '有效'
          } else {
            role.roleStatus = '删除'
          }
        })
      }
      return this.roles
    },
    dialogShow: {
      get () {
        if (this.getCrudState === 'setUserRole') {
          return true
        }
        return false
      },
      set () {
        this.setCrudState('')
      }
    },
    cmenuTree: {
      get () {
        return this.menuTree
      },
      set (val) {
        this.menuTree = val
      }

    }

  },
  watch: {
  },
  filter: {
  }

}
</script>
<style lang="less" scope >
@import url('../../style/TableStyle.less');
@import '../../style/CheckBoxHidden.less';
#expandMenu {
  background-color: red;
}
</style>
