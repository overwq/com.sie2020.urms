<template  slot-scope='user'>
  <div id="User">
<!--    // 获取查询用户对象queryUser 新增、查询、批量删除Button-->
    <div id="UserHead">
      <el-link :underline="false">用户名:</el-link>
      <el-input v-model="queryUser.loginName" class="input" size="mini"></el-input>
      <el-link :underline="false">姓名:</el-link>
      <el-input v-model="queryUser.userName" class="input" size="mini"></el-input>
      <el-link :underline="false">状态</el-link>
      <el-select v-model="queryUser.status" placeholder="全部" @change="getAjaxQueryUsers" style="min-width:100px;max-width:200px;width:8%" size="mini" >
        <el-option v-for="item in queryUserOption" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-link :underline="false">创建时间:</el-link>
      <div id="dateInputDate">
        <el-date-picker v-model="c_dateInputValue" type="datetimerange" :picker-options="pickerOptions"
          range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
        </el-date-picker>
      </div>
      <el-button type="info" icon="el-icon-search" size="large" class="btn-wth" @click="getAjaxQueryUsers">查询</el-button>
      <el-button type="primary" icon="el-icon-edit" size="large" class="btn-wth" @click="addUserClickEvent('addUser')">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" size="large" class="btn-wth" @click="checkBoxStateChange()">删除</el-button>
    </div>
<!--    // dialog 添加或修改用户 dialogState dialogShow-->
    <div id="AddAndUpdateUser">
      <template v-if="userState.dialogShow">
          <el-dialog :title="c_dialogTitle" :visible.sync="userState.dialogShow" center width="700px">
            <el-form :model="addAndUpdateUser" ref="addAndUpdateUser" label-width="200px">
              <el-form-item label="真实姓名" prop="userName">
                <el-input v-model="addAndUpdateUser.userName"></el-input>
              </el-form-item>
              <el-form-item label="用户名" prop="userLoginName">
                <el-input v-model="addAndUpdateUser.userLoginName"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="userPassword">
                <el-input type="password" v-model="addAndUpdateUser.userPassword"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkUserPassword"  v-if="userState.dialogState==='addUser'">
                <el-input type="password" v-model="addAndUpdateUser.checkUserPassword"></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="userSex">
                <el-radio-group v-model="addAndUpdateUser.userSex">
                  <el-radio :label="1">男</el-radio>
                  <el-radio :label="0">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="电话" prop="userPhone">
                <el-input v-model="addAndUpdateUser.userPhone"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="userEmail">
                <el-input v-model="addAndUpdateUser.userEmail"></el-input>
              </el-form-item>
              <el-form-item label="状态" prop="userStatus">
                <el-radio-group v-model="addAndUpdateUser.userStatus">
                  <el-radio :label="1">有效</el-radio>
                  <el-radio :label="0">无效</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="备注" prop="userRemarks">
                <el-input type="textarea" v-model="addAndUpdateUser.userRemarks"></el-input>
              </el-form-item>
                <el-form-item>
                <el-button type="primary" @click="submitForm('addAndUpdateUser')">确认修改</el-button>
                <el-button @click="resetForm('addAndUpdateUser')">重置</el-button>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
            </span>
        </el-dialog>
      </template>
    </div>
<!-- 设置用户角色dialog -->
    <div id="UserRoleDialog" class="tableStyle" v-if="userState.userRoleShow">
      <el-dialog title="设置用户角色" :visible.sync="userState.userRoleShow"  center width="800px" >
        <el-table  @row-click="getCheckedMenuTrees" stripe  :data="showRoles"  height="480" ref="userRoleTable" @selection-change="selectionChange">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column type="expand" label-position="left" label="查看菜单" width="90px">
            <template>
              <el-tree
                :data="menuTree"
                default-expand-all
                node-key="menuId"
                ref="tree"
                :props="defaultProps">
              </el-tree>
            </template>
          </el-table-column>
          <el-table-column label="序号" min-width="50">
            <template slot-scope="role"><span>{{role.$index+1}}</span></template>
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
        </div>
        <div class="dialog_pageination">
          <pageination :noUsingVuex="true" @dialogPageSize="getDialogPageSize" @dialogPageNum="getDialogPageNum"></pageination>
        </div>
      </el-dialog>
    </div>
<!--     数据展示table-->
    <div class="tableStyle">
      <el-table stripe :data="c_tableUser" height="645">//数据显示
        <el-table-column label="全选" type="selection" min-width="50"></el-table-column>
        <el-table-column label="序号" min-width="50"><template slot-scope="scope"><span>{{scope.$index+1}}</span></template></el-table-column>
        <el-table-column prop="userLoginName" label="用户名" min-width="100px"></el-table-column>
        <el-table-column prop="userName" label="姓名" min-width="100"></el-table-column>
        <el-table-column prop="userSex" label="性别" min-width="50"></el-table-column>
        <el-table-column prop="userPhone" label="联系电话" min-width="100"></el-table-column>
        <el-table-column prop="userEmail" label="邮箱" min-width="100"></el-table-column>
        <el-table-column prop="userCreateTime" label="创建时间"></el-table-column>
        <el-table-column prop="userUpdateTime" label="修改时间"></el-table-column>
        <el-table-column prop="userStatus" label="状态" min-width="50"></el-table-column>
        <el-table-column label="操作" min-width="280" fixed="right">
          <template slot-scope="user" class="btn-wth">
            <el-button type="warning" round class="btn-wth" @click="userUpdateClickEvent(user.row.userId)">修改</el-button>
            <el-button type="danger" round class="btn-wth" @click="deleteUserInfo(user.row.userId)">删除</el-button>
            <el-button type="info" round class="btn-wth" @click="disableState(user.row)">无效</el-button>
            <el-button type="primary" round class="btn-wth" @click="setUserRole(user.row)">设置角色</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pageination></pageination>
    </div>
  </div>
</template>

<script>
import Pageination from '../public/Pageination'
import {
  api_addUser,
  api_updateUser,
  api_deleteUser,
  api_getUsers,
  api_userGetCount,
  api_getUserById
} from '../../api/user'
import { getRoleMenusByRoleId, getRoles, getCount } from '../../api/role'
import { getUserRoleByUserLoginName, addUserRole, delUserRole } from '../../api/userrole'
import { mapMutations } from 'vuex'
import { Message } from 'element-ui'
export default {
  name: 'User',
  data () {
    return {
      userState: {
        dialogShow: false, /*  添加 修改 dialog显示状态  */
        dialogState: '', /*  addUser updateUser */
        userRoleShow: false /*  设置用户角色dialog  */
      },
      users: [], /*  存放分页查询回调数据  */
      roles: [], /*  角色集合--设置用户角色 */
      checkedUser: {}, /*  设置用户角色点击事件的的用户对象--设置用户角色  */
      oldCheckedRoleIds: [{}], /*  用户已有角色--设置用户角色  */
      checkedRole: {}, /*  当前选中角色 可能为复数 --设置用户角色  */
      menuTree: [], /*  预览菜单树--设置用户角色  */
      defaultProps: {
        children: 'children',
        label: 'menuName'
      },
      queryUser: {
        loginName: '',
        userName: '',
        status: '',
        startTime: '',
        stopTime: '',
        pageSize: 20,
        pageNum: 1
      }, /*  分页查询用户对象  */
      queryRole: {
        roleId: '',
        roleName: '',
        roleStatus: '',
        roleCode: '',
        pageSize: 20,
        pageNum: 1
      }, /*  分页查询角色  */
      resetQueryUser: {
        loginName: '',
        userName: '',
        status: '',
        startTime: '',
        stopTime: '',
        pageSize: 20,
        pageNum: 1
      }, /*  重置-分页查询用户  */
      disableUser: {
        userId: '',
        userStatus: ''
      }, /*  设置无效  */
      addAndUpdateUser: {
        userId: '',
        userLoginName: '',
        userPassword: '',
        userName: '',
        userPhone: '',
        userEmail: '',
        userSex: 1,
        userStatus: 0,
        userRemarks: '',
        checkUserPassword: ''
      }, /*  添加或修改用户对象  */
      resetAddAndUpdateUser: {
        userId: '',
        userLoginName: '',
        userPassword: '',
        userName: '',
        userPhone: '',
        userEmail: '',
        userSex: 1,
        userStatus: 0,
        userRemarks: '',
        checkUserPassword: ''
      }, /*  重置-添加或修改用户对象  */
      queryUserOption: [
        {
          value: '',
          label: '全部'
        },
        {
          value: '1',
          label: '有效'
        },
        {
          value: '0',
          label: '无效'
        },
        {
          value: '2',
          label: '删除'
        }
      ], /*  下拉列表value label  */
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.seTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }, /*  日期插件  */
      pickerOptionsValue: '' /*  日期插件值  数组  */

    }
  },
  created: function () {
    document.onkeypress = (e) => {
      if (e.key === 'Enter') this.getAjaxQueryUsers()
    }
  },
  mounted: function () {
    this.getAjaxQueryUsers()
    this.$store.commit('setPageSize', 20)
    this.$store.commit('setPageNum', 1)
  },
  components: {
    Pageination
  },
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    /*  无效  */
    disableState (user) {
      this.disableUser.userId = user.userId
      user.userStatus === '有效' ? this.disableUser.userStatus = 0 : this.disableUser.userStatus = 1
      api_updateUser(JSON.stringify(this.disableUser)).then(res => {
        if (res.data.state !== 402) {
          this.getAjaxQueryUsers()
        }
      })
    },
    /*  根据id删除用户信息  */
    deleteUserInfo (id) {
      this.$confirm('确认要删除该用户吗？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        api_deleteUser(id).then(res => {
          if (res.data.state !== 402) {
            this.getAjaxQueryUsers()
          }
        })
      })
    },
    /*  add添加用户信息  */
    addUserClickEvent () {
      this.userState.dialogShow = true
      this.userState.dialogState = 'updateUser'
      this.addAndUpdateUser = JSON.parse(JSON.stringify(this.resetAddAndUpdateUser))
    },
    /*  update修改用户信息  */
    userUpdateClickEvent (userId) {
      this.userState.dialogShow = true
      this.userState.dialogState = 'updateUser'
      api_getUserById(userId).then(res => {
        this.addAndUpdateUser = res.data
      })
    },
    /*  设置用户角色点击事件 获取角色 添加默认选中角色  */
    setUserRole (user) {
      this.setRoleTotalCount()
      this.checkedUser = JSON.parse(JSON.stringify(user))
      this.userState.userRoleShow = true
      getRoles(this.queryRole).then(res => {
        this.roles = res.data.data
      })
      if (this.$refs.userRoleTable) {
        this.$refs.userRoleTable.clearSelection()
      }
      this.getCheckedUserRole()
    },
    /*  表单提交事件 三种状态：addUser updateUser ''  */
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.userState.dialogState === 'userAdd') {
            api_addUser(this.c_addAndUpdateUser).then(res => {
              if (res.data.state !== 402) {
                this.getAjaxQueryUsers()
              }
            })
          }
          if (this.userState.dialogState === 'userUpdate') {
            api_updateUser(this.c_addAndUpdateUser).then(res => {
              if (res.data.state !== 402) {
                this.getAjaxQueryUsers()
              }
            })
          }
          this.userState.dialogState = ''
          this.userState.dialogShow = false
        } else {
          this.userState.dialogShow = false
          this.userState.dialogState = ''
          console.log('error submit!!')
          return false
        }
      })
    },
    /*  表单重置  */
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    /*  分页查询  */
    getAjaxQueryUsers () {
      api_getUsers(this.queryUser).then(res => {
        this.users = res.data.data
        if (res.data.state !== 402) {
          this.getTotalCount()
        }
      })
    },
    /*  获取分页总数  */
    getTotalCount () {
      const countUser = JSON.parse(JSON.stringify(this.queryUser))
      countUser.userHidden = 0
      if (parseInt(countUser.status) === 2) countUser.hidden = 1
      api_userGetCount(JSON.stringify(countUser)).then(res => {
        this.$store.commit('setTotalCount', res.data)
      })
    },
    getDialogPageSize (val) {
      this.dialogPageSize = val
    },
    getDialogPageNum (val) {
      this.dialogPageNum = val
    },
    selectionChange (val) {
      console.log(val)
      this.checkedRole = val
    },
    getCheckedUserRole () {
      getUserRoleByUserLoginName(this.checkedUser.userLoginName).then(res => {
        this.oldCheckedRoleIds = res.data.data
        if (this.oldCheckedRoleIds && this.oldCheckedRoleIds.length > 0) {
          this.oldCheckedRoleIds.forEach(or => {
            const checkedSelections = this.roles.find(r => r.roleId === or.roleId)
            if (this.$refs.userRoleTable) {
              this.$refs.userRoleTable.toggleRowSelection(checkedSelections, true)
            }
          })
        }
      })
    },
    updateUserRoleUR () {
      try {
        const addUserRoles = this.checkedRole.filter(cr => !this.oldCheckedRoleIds.find(or => or.roleId === cr.roleId))
        const deleteUserRoles = this.oldCheckedRoleIds.filter(or => !this.checkedRole.find(cr => cr.roleId === or.roleId))
        if (addUserRoles.length > 0) {
          this.addCheckedUserRole(addUserRoles)
        }
        if (deleteUserRoles.length > 0) {
          this.deleteUserRole(deleteUserRoles)
        }
      } catch (error) {
        Message.error('你没有设置用户角色的权限')
      }

      this.dialogShow = false
    },
    deleteUserRole (deleteUserRoles) {
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
      this.countRole = JSON.parse(JSON.stringify(this.queryRole))
      getCount(this.countRole).then(res => {
        this.$store.commit('setTotalCount', res.data)
      })
    },
    getCheckedMenuTrees (role) {
      const roleId = role.roleId
      getRoleMenusByRoleId(roleId).then(res => {
        this.menuTree = res.data.data
      })
    }
  },
  computed: {
    /*  转换性别和状态 0 : 1 -> 女 : 男 , 无效 : 有效  */
    c_tableUser () {
      const users = JSON.parse(JSON.stringify(this.users))
      if (users && users.length > 0) {
        users.forEach(user => {
          user.userSex === 1 ? user.userSex = '男' : user.userSex = '女'
          parseInt(user.userStatus) === 1 ? user.userStatus = '有效' : user.userStatus = '无效'
          if (parseInt(user.userHidden) === 1) {
            user.userStatus = '删除'
          }
        })
      }
      return users
    },
    /*  转换dialog title 新增用户信息 修改用户信息  */
    c_dialogTitle () {
      let dialogTitle = ''
      if (this.userState.dialogState === 'addUser') {
        dialogTitle = '新增用户信息'
      } else {
        dialogTitle = '修改用户信息'
      }
      return dialogTitle
    },
    /*  分页插件 每页显示多少  */
    c_pageSize () {
      const pageSize = this.$store.getters.getPageSize
      this.queryUser.pageSize = pageSize
      this.getAjaxQueryUsers()
      return pageSize
    },
    /*  分页插件 第几页  */
    c_pageNum () {
      const pageNum = this.$store.getters.getPageNum
      this.queryUser.pageNum = pageNum
      this.getAjaxQueryUsers()
      return pageNum
    },
    /* 日期插件 */
    c_dateInputValue: {
      get () {
        return this.pickerOptionsValue
      },
      set (val) {
        val == null ? this.pickerOptionsValue = [] : this.pickerOptionsValue = val
        this.pickerOptionsValue[0] == null ? this.queryUser.startTime = '' : this.queryUser.startTime = this.pickerOptionsValue[0]
        this.pickerOptionsValue[1] == null ? this.queryUser.stopTime = '' : this.queryUser.stopTime = this.pickerOptionsValue[1]
        this.getAjaxQueryUsers()
      }
    },
    resetState () {
      return this.$store.getters.getResetState
    },
    showRoles () {
      const tempRoles = this.roles
      if (tempRoles && tempRoles.length > 0) {
        tempRoles.forEach(role => {
          role.roleStatus === 1 ? role.roleStatus = '有效' : role.roleStatus = '无效'
        })
      }
      return tempRoles
    },
    dialogPageSize: {
      get () {
        return this.queryRole.pageSize
      },
      set (val) {
        this.queryRole.pageSize = val
        this.setUserRole(this.checkedUser)
      }
    },
    dialogPageNum: {
      get () {
        return this.queryRole.pageNum
      },
      set (val) {
        this.queryRole.pageNum = val
        this.setUserRole(this.checkedUser)
      }
    }
  },
  watch: {
    resetState (val) {
      this.queryUser = JSON.parse(JSON.stringify(this.resetQueryUser))
      this.pickerOptionsValue = []
      this.getAjaxQueryUsers()
    },
    c_pageSize () {},
    c_pageNum () {}
  }

}
</script>
<style lang='less'>
@import url('../../style/TableStyle.less');
#UserHead {
  width: 100%;
  height: 60px;
  line-height: 60px;
}
.input {
  min-width: 60px;
  max-width: 200px;
  width: 10%;
  padding-left: 10px;
  padding-right: 10px;
}

#dateInputDate{
  display: inline-block;
  min-width: 200px;
input {
  width: 72px;
}
div {
  width: 100%;
}
}

</style>
