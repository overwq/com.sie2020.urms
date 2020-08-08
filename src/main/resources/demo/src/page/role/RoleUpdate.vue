<template>
  <div id="RoleUpdate">
   <el-dialog :visible.sync="dialogShow" center width="700px">
      <div class="UserAddSlot"><slot></slot></div>
      <el-form :model="updateRole" :rules="rules" ref="updateRole" label-width="200px">
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="updateRole.roleCode"></el-input>
        </el-form-item>
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="updateRole.roleName"></el-input>
        </el-form-item>
        <el-form-item label="生效时间" prop="roleCreateTime">
          <el-date-picker v-model="updateRole.roleCreateTime" type="date" placeholder="生效时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" prop="roleInvalidTime">
          <el-date-picker v-model="updateRole.roleInvalidTime" type="date" placeholder="生效时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="roleStatus">
          <el-radio-group v-model="updateRole.roleStatus">
            <el-radio :label="1">有效</el-radio>
            <el-radio :label="0">无效</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="roleRemarks">
          <el-input type="textarea" v-model="updateRole.roleRemarks"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="submitForm('updateRole')">确认修改</el-button>
          <el-button @click="resetForm('updateRole')">重置</el-button>
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
  data () {
    return {
      updateRole: {
        roleId: '',
        roleName: '',
        roleRemarks: '',
        roleCode: '',
        roleCreateTime: '',
        roleInvalidTime: '',
        roleHidden: '',
        roleStatus: ''
      }
    }
  },
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
        this.updateRole = res.data
        if (res.data.state !== 402) {
          this.setRefreshTag(true)
        }
      })
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.setCrudState('')
          updateRole(JSON.stringify(this.updateRole)).then(res => {
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
