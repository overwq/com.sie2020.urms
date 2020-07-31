<template>
  <div id="UserHead">
    <el-link :underline="false">用户名:</el-link>
    <el-input v-model="user.loginName" class="input" size="mini"></el-input>
    <el-link :underline="false">姓名:</el-link>
    <el-input v-model="user.userName" class="input" size="mini"></el-input>
    <el-link :underline="false">状态</el-link>
    <el-select v-model="user.status" placeholder="全部"  style="min-width:100px;max-width:200;width:8%" size="mini" >
      <el-option v-for="item in options1" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-link :underline="false">创建时间:</el-link>
    <div id="dateInputDate">
      <el-date-picker v-model="dateInputValue" type="datetimerange" :picker-options="pickerOptions"
        range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
      </el-date-picker>
    </div>
    <el-button type="info" icon="el-icon-search" size="large" class="btn-wth" @click="getAjaxQueryUsers">查询</el-button>
    <el-button type="primary" icon="el-icon-edit" size="large" class="btn-wth" @click="uploadCrudState('addUser')">新增</el-button>
    <el-button type="danger" icon="el-icon-delete" size="large" class="btn-wth" @click="checkBoxStateChange()">删除</el-button>
  </div>
</template>

<script>
import UserMixin from './UserMixin'
import { getUsers, getCount } from '../../api/user'
import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'UserHead',
  mixins: [UserMixin],
  data () {
    return {
      user: {},
      users: {},
      resetUser: {},
      pickerOptions: {},
      value2: []
    }
  },
  created: function(){
    document.onkeypress = ( e ) => {
      if (e.key === 'Enter') this.getAjaxQueryUsers()
     }
  },
  mounted: function () {
    this.getAjaxQueryUsers()
  },
  destroyed: function () {

  },
  methods: {
    // ...mapActions(['getUsersInfo']),
    ...mapMutations(['setResetState', 'setRefreshTag', 'setCrudState', 'setCheckBoxShowState', 'setTotalCount']),
    getAjaxQueryUsers () {
      getUsers(this.user).then(res => {
        this.setRefreshTag(false)
        this.$emit('query-users-comeing', res.data.data)
        this.getTotalCount()
      })
    },
    checkBoxStateChange () {
      this.setCheckBoxShowState()
    },
    setUserDate (date) {
      this.user.startTime = date[0]
      this.user.stopTime = date[1]
    },
    setUserStatue (val) {
      this.user.status = val
    },
    uploadCrudState (toState) {
      this.setCrudState(toState)
    },
    getTotalCount () {
      this.countUser = JSON.parse(JSON.stringify(this.user))
      if (parseInt(this.countUser.status) === 2) this.countUser.hidden = 1
      getCount(JSON.stringify(this.countUser)).then(res => {
        this.setTotalCount(res.data)
      })
    }
  },
  computed: {
    ...mapGetters(['getResetState', 'getCrudState', 'getPageNum', 'getPageSize', 'getRefreshTag']),
    resetState () { return this.getResetState },
    crudState () { return this.getCrudState },
    pageNum () { return this.getPageNum },
    pageSize () { return this.getPageSize },
    refreshTag () { return this.getRefreshTag },
    dateInputValue: {
      get () { return this.value2 },
      set (val) {
        val == null ? this.value2 = [] : this.value2 = val
      }
    },
    userStateSelectOptionValue () { return this.user.status }
  },
  watch: {
    resetState (val) {
      this.user = JSON.parse(JSON.stringify(this.resetUser))
      this.value2 = []
    },
    pageNum (val) {
      this.user.pageNum = val
      this.setRefreshTag(true)
    },
    pageSize (val) {
      this.user.pageSize = val
      this.setRefreshTag(true)
    },
    refreshTag (val) {
      if (val === true) {
        this.getAjaxQueryUsers()
      }
    },
    dateInputValue (val) {
      val[0] == null ? this.user.startTime = '' : this.user.startTime = val[0]
      val[1] == null ? this.user.stopTime = '' : this.user.stopTime = val[1]
      this.setRefreshTag(true)
    },
    userStateSelectOptionValue (val) {
      this.setRefreshTag(true)
    }
  }

}
</script>

<style lang="less">
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

.inline-block {
  display: inline-block;
  width: 100px;
}
.date-input{
  width: 114px!important;
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
