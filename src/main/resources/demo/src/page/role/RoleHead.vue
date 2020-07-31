<template>
 <div id="RoleHead">
    <el-link :underline="false">角色名称:</el-link>
    <el-input v-model="role.roleName" class="input" size="mini"></el-input>
    <el-link :underline="false">角色编码:</el-link>
    <el-input v-model="role.roleCode" class="input" size="mini" ></el-input>
    <el-link :underline="false">状态</el-link>
    <el-select v-model="role.roleStatus" placeholder="全部"  style="min-width:100px;max-width:200;width:8%" size="mini" >
      <el-option v-for="item in options1" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-button type="info" icon="el-icon-search"  class="btn-wth" @click="getAllRoles">查询</el-button>
    <el-button type="primary" icon="el-icon-edit"  class="btn-wth" @click="uploadCrudState('addRole')">新增</el-button>
    <el-button type="danger" icon="el-icon-delete"  class="btn-wth" @click="checkBoxStateChange">删除</el-button>
 </div>
</template>

<script>
import RoleMixin from './RoleMixin'
import { mapMutations, mapGetters } from 'vuex'
import { getRoles, getCount } from '../../api/role'
export default {
  name: 'UserHead',
  mixins: [RoleMixin],
  data () {
    return {
      role: {}
    }
  },
  created: function () {
    document.onkeypress = (e) => {
      if (e.key === 'Enter') this.getAllRoles()
    }
  },
  mounted: function () {
    this.getAllRoles()
  },
  methods: {
    ...mapMutations(['setResetState', 'setRefreshTag', 'setCrudState', 'setCheckBoxShowState', 'setTotalCount']),
    uploadCrudState (toState) {
      this.setCrudState(toState)
    },
    getAllRoles () {
      getRoles(this.role).then(res => {
        this.$emit('query-role-comming', res.data.data)
        this.setRoleTotalCount()
        this.setRefreshTag(false)
      })
    },
    checkBoxStateChange () {
      this.setCheckBoxShowState()
    },
    setRoleTotalCount () {
      this.countRole = JSON.parse(JSON.stringify(this.role))
      getCount(this.countRole).then(res => {
        // console.log(res.data)
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
    getRoleState () { return this.role.roleStatus }
  },
  watch: {
    resetState () {
      this.role = JSON.parse(JSON.stringify(this.resetRole))
    },
    pageNum (val) {
      this.role.pageNum = val
      this.setRefreshTag(true)
    },
    pageSize (val) {
      this.role.pageSize = val
      this.setRefreshTag(true)
    },
    refreshTag (val) {
      if (val) {
        this.getAllRoles()
      }
    },
    getRoleState () {
      this.setRefreshTag(true)
    }
  }
}
</script>

<style lang="less">
@import url('../../style/TableStyle.less');
#RoleHead {
  width: 100%;
  height: 60px;
  line-height: 60px;
}

</style>
