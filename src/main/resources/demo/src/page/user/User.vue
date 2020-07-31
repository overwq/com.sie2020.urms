<template  slot-scope='user'>
  <div class="tableStyle">
    <userAdd @add-user="addUserInfo">添加用户信息</userAdd>
    <userUpdate :user-id="updateUserId">修改用户信息</userUpdate>
    <template v-if="checkedUser != ''">
      <userRole :checkedUser="checkedUser">{{checkedUser.userLoginName}}</userRole>
    </template>
    <userHead @query-users-comeing="setUsers"></userHead>
    <el-table stripe :data="computedUsers" height="645">
      <el-table-column label="全选" min-width="50">
        <template slot-scope="scope">
          <el-checkbox  v-show="checkBoxShowState"  v-if="scope.$index === 0"  v-model="checkAll"
            :key="scope.$index" @change="handleCheckAllChange" :indeterminate="isIndeterminate">
          </el-checkbox>
          <el-checkbox v-show="checkBoxShowState" v-else :key="scope.$index"
            v-model="checkedBoxs" @change="handleCheckedBoxChange">
          </el-checkbox>
        </template>
      </el-table-column>
      <el-table-column label="序号" min-width="50">
        <template slot-scope="scope">
          <span>{{scope.$index+1}}</span>
          <!-- <span>{{scope.row}}</span> -->
        </template>
      </el-table-column>
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
          <el-button type="warning" round class="btn-wth" @click="setUpdateUserId(user.row.userId),setCrudState('updateUser')">修改</el-button>
          <el-button type="danger" round class="btn-wth" @click="deleteUserInfo(user.row.userId)">删除</el-button>
          <el-button type="info" round class="btn-wth" @click="disableState(user.row.userId)">无效</el-button>
          <el-button type="primary" round class="btn-wth" @click="setCrudState('setUserRole'),checkedUser = user.row">设置角色</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pageination :totalCount="totalCount"></pageination>
  </div>
</template>

<script>
import UserHead from './UserHead'
import UserAdd from './UserAdd'
import UserUpdate from './UserUpdate'
import Pageination from '../public/Pageination'
import { addUser, deleteUser, updateUser } from '../../api/user'
import { mapState, mapMutations } from 'vuex'
import UserMixin from './UserMixin'
import UserRole from './UserRole'
export default {
  name: 'User',
  mixins: [UserMixin],
  data () {
    return {
      users: [],
      checkAll: false,
      isIndeterminate: true,
      checkedBoxs: [],
      deleteAll: false,
      totalCount: 200,
      updateUserId: 0,
      checkedUser: ''
    }
  },
  components: {
    UserHead, UserAdd, Pageination, UserUpdate, UserRole
  },
  methods: {
    ...mapMutations(['setTempUser', 'setRefreshTag', 'setCrudState']),
    setUsers (users) {
      this.users = users
    },
    disableState (userId) {
      this.disableUser.userId = userId
      this.disableUser.userStatus = 0
      updateUser(JSON.stringify(this.disableUser)).then(res => {
        this.setRefreshTag(true)
      })
    },
    deleteUserInfo (id) {
      this.$confirm('确认要删除该用户吗？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser(id).then(res => {
          this.setRefreshTag(true)
        })
      })
    },
    setUpdateUserId (val) {
      this.updateUserId = val
    },
    handleCheckAllChange (val) {
      this.isIndeterminate = false
    },
    handleCheckedBoxChange (val) {
      const checkedBoxNum = val.length
      this.checkAll = checkedBoxNum === this.checkedBoxs.length
      this.isIndeterminate =
        checkedBoxNum > 0 && checkedBoxNum < this.checkedBoxs.length
    },
    transformUserSex (user) {
      user.userSex === 1 ? user.userSex = '男' : user.userSex = '女'
    },
    transformUserStatus (user) {
      const state = parseInt(user.userStatus)
      const hidden = parseInt(user.userHidden)
      state === 1 ? user.userStatus = '有效' : user.userStatus = '无效'
      if (hidden === 1) {
        user.userStatus = '删除'
      }
    },
    transformUserForPage (user) {
      this.transformUserSex(user)
      this.transformUserStatus(user)
    },
    addUserInfo (user) {
      addUser(user)
        .then(res => {
          this.setRefreshTag(true)
        })
    }
    // getTotalCount () {
    //   console.log(JSON.stringify(this.user))
    //   getCount(JSON.stringify(this.user)).then(res => {
    //     this.totalCount = res.data
    //     console.log(1111111111111111)
    //     console.log(this.totalCount)
    //   })
    // }
  },
  computed: {
    ...mapState(['checkBoxShowState']),
    computedUsers () {
      this.users.forEach(user => {
        this.transformUserForPage(user)
      })
      return this.users
    },
    computedDeleteAll () {
      if (this.deleteAll === false) {
        return true
      } else {
        return false
      }
    },
    ccheckedUser: {
      get () {
        return this.checkedUser
      },
      set (val) {
        this.checkedUser = val
      }
    }

  },
  mounted: function () {
    // this.getTotalCount()
  }

}
</script>
<style lang='less'>
@import url('../../style/TableStyle.less');
.userShowBtn {
  display: inline-flex;
  width: 80px;
  height: 100%;
}

.userShowTable {
  text-align: center;
}

</style>
