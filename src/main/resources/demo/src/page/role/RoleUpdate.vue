<template>
  <div id="RoleUpdate">
   <el-dialog :visible.sync="dialogShow" center width="700px">
      <div class="UserAddSlot"><slot></slot></div>
      <el-form :model="role" :rules="rules" ref="role" label-width="200px">
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="role.roleCode"></el-input>
        </el-form-item>
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="role.roleName"></el-input>
        </el-form-item>
        <el-form-item label="生效时间" prop="roleCreateTime">
          <el-date-picker v-model="role.roleCreateTime" type="date" placeholder="生效时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" prop="roleInvalidTime">
          <el-date-picker v-model="role.roleInvalidTime" type="date" placeholder="生效时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="roleStatus">
          <el-radio-group v-model="role.roleStatus">
            <el-radio :label="1">有效</el-radio>
            <el-radio :label="0">无效</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="roleRemarks">
          <el-input type="textarea" v-model="role.userRemarks"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="submitForm('role')">确认修改</el-button>
          <el-button @click="resetForm('role')">重置</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      </span>
   </el-dialog>
    <!-- <slot name="UserAddDialogSlot">{{userAddDialogVisiable}}</slot> -->
  </div>
</template>

<script>

import { mapMutations, mapGetters } from 'vuex'
import RoleMixin from './RoleMixin'
import { updateRole, getRoleById } from '../../api/role'
export default {
  mixins: [RoleMixin],
  props: {
    roleId: {
      type: Number,
      default: 0
    }
  },
  mounted: function () {
    this.getUpdateRoleById()
  },
  methods: {
    ...mapGetters(['getCrudState']),
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    getUpdateRoleById () {
      getRoleById(this.roleId).then(res => {
        this.role = res.data
        this.setRefreshTag(true)
      })
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.setCrudState('')
          updateRole(JSON.stringify(this.role)).then(res => {
            this.setRefreshTag(true)
          })
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }

  },
  computed: {
    dialogShow: {
      get () {
        if (this.getCrudState() === 'updateRole') {
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
    roleId (val) {
      this.getUpdateRoleById()
    }
  }

}
</script>

<style lang="less">
#UserAddSlot {
  width: 100%;
  text-align: center;
  height: 60px;
  line-height: 60px;
  margin-top: -55px;
  font-size: 1.5em;
}
</style>
