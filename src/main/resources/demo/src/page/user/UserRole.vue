<template>
  <div class="tableStyle">
    <el-dialog :visible.sync="dialogShow" center width="800px" title="设置用户所属角色" v-on:open="setUserRole()" >
    <div class="UserAddSlot" style="margin:-40px 0 0 0px;text-align:left;">用户名 :<slot></slot></div>
     <el-table stripe  :data="showRoles"  height="480" ref="userRoleTable" @selection-change="selectionChange">
       <el-table-column type="selection" width="55"></el-table-column>
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
import { getRoles } from '../../api/role'
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
      checkedRole: {}
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
            this.$refs.userRoleTable.toggleRowSelection(checkedSelections)
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
      this.$refs.userRoleTable.clearSelection()
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
    }

  },
  watch: {
  },
  filter: {
  }

}
</script>
<style lang="less">
@import url('../../style/TableStyle.less');
@import '../../style/CheckBoxHidden.less';

</style>
