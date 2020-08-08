<template>
  <div class="tableStyle">
    <roleAddDialog>添加角色</roleAddDialog>
    <template v-if="updateRoleShow">
      <roleUpload :role-id="uploadRoleId">修改角色</roleUpload>
    </template>
    <template v-if="roleMenuShow">
      <roleMenu :roleId="roleId">设置角色菜单</roleMenu>
    </template>
    <roleHead @query-role-comming="setRoles"></roleHead>
     <el-table stripe  :data="showRoles"  height="645">
        <el-table-column label="全选" min-width="50">
          <template slot-scope="role">
             <el-checkbox
            v-show="checkBoxShowState"
            v-if="role.$index === 0"
            v-model="checkAll"
            :key="role.$index"
            :indeterminate="isIndeterminate"
          ></el-checkbox>
          <el-checkbox
            v-show="checkBoxShowState"
            v-else
            :key="role.$index"
            v-model="checkAll"

          ></el-checkbox>
          </template>
        </el-table-column>
        <el-table-column label="序号" min-width="50">
          <template slot-scope="role">
            <span>{{role.$index+1}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="roleCode" label="角色编码"></el-table-column>
        <el-table-column prop="roleName" label="角色名称" min-width="100"></el-table-column>
        <el-table-column prop="roleCreateTime" label="生效日期" min-width="100"></el-table-column>
        <el-table-column prop="roleInvalidTime" label="失效日期" min-width="100"></el-table-column>
        <el-table-column prop="roleStatus" label="状态" min-width="50"></el-table-column>
        <el-table-column label="操作" min-width="280" fixed="right">
          <template slot-scope="role">
            <el-button type="warning" round class="btn-wth" @click="updateRoleShow=true,uploadRoleId = role.row.roleId,setCrudState('updateRole')">修改</el-button>
            <el-button type="danger" round class="btn-wth" @click="deleteRoleById(role.row.roleId)">删除</el-button>
            <el-button type="info" round class="btn-wth" @click="disableState(role.row)">无效</el-button>
            <el-button type="primary" round class="btn-wth" @click="roleId = role.row.roleId,roleMenuShow = true,setUserRoleCheckedRoleId(role.row.roleId),setCrudState('roleMenu')">设置菜单</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pageination></pageination>
  </div>
</template>

<script>
import RoleHead from './RoleHead'
import RoleUpload from './RoleUpdate'
import Pageination from '../public/Pageination'
import RoleAddDialog from './RoleAdd'
import { mapState, mapMutations } from 'vuex'
import { deleteRole, updateRole } from '../../api/role'
import RoleMenu from './RoleMenu'
export default {
  name: 'Role',
  data () {
    return {
      role: '',
      roles: [],
      checkAll: false,
      isIndeterminate: false,
      uploadRoleId: 0,
      disableRole: {
        roleId: '',
        roleStatus: ''
      },
      roleMenuShow: false,
      updateRoleShow: false,
      roleId: ''
    }
  },
  components: {
    RoleHead, Pageination, RoleAddDialog, RoleUpload, RoleMenu
  },
  methods: {
    ...mapMutations(['setTempUser', 'setRefreshTag', 'setCrudState']),
    setUserRoleCheckedRoleId (val) {
      // this.$store.dispatch('setRoleMenuTreeCheckedMenu',val)
    },
    deleteRoleById (id) {
      this.$confirm('确认要删除该角色吗？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteRole(id).then(res => {
          if (res.data.state !== 402) {
            this.setRefreshTag(true)
          }
        })
      })
    },
    setRoles (roles) {
      this.roles = roles
    },
    disableState (role) {
      this.disableRole.roleId = role.roleId
      role.roleStatus === '无效' ? this.disableRole.roleStatus = 1 : this.disableRole.roleStatus = 0
      updateRole(JSON.stringify(this.disableRole)).then(res => {
        if (res.data.state !== 402) {
          this.setRefreshTag(true)
        }
      })
    }
  },
  computed: {
    ...mapState(['checkBoxShowState']),
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
    }
  }

}
</script>
<style lang="less">
@import url('../../style/TableStyle.less');

</style>
