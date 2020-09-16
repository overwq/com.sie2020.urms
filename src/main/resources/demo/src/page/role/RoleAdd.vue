<template>
   <div id="RoleAdd">
    <el-dialog :visible.sync="dialogShow2" center width="700px">
      <div class="UserAddSlot"><slot></slot></div>
      <el-form :model="addRole" :rules="rules" ref="addRole" label-width="200px">
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="addRole.roleCode"></el-input>
        </el-form-item>
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="addRole.roleName"></el-input>
        </el-form-item>
        <el-form-item label="生效时间" prop="roleCreateTime">
          <el-date-picker v-model="addRole.roleCreateTime" type="date" placeholder="生效时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" prop="roleInvalidTime">
          <el-date-picker v-model="addRole.roleInvalidTime" type="date" placeholder="生效时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="roleStatus">
          <el-radio-group v-model="addRole.roleStatus">
            <el-radio :label="1">有效</el-radio>
            <el-radio :label="0">无效</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="roleRemarks">
          <el-input type="textarea" v-model="addRole.userRemarks"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="submitForm('addRole')">确认添加</el-button>
          <el-button @click="resetForm('addRole')">重置</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      </span>
   </el-dialog>
  </div>
</template>

<script>
import RoleAddUpdateMixin from './RoleMixin'
import { mapMutations, mapGetters } from 'vuex'
import { addRole } from '../../api/role'
export default {
  mixins: [RoleAddUpdateMixin],
  data () {
    return {
      addRole: {
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
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addRole(this.addRole).then(res => {
            this.setCrudState('')
            if (res.data.state !== 402) {
              this.setRefreshTag(true)
            }
          })
          // this.$store.commit('setCrudState', '')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  },
  computed: {
    ...mapGetters(['getCrudState']),
    dialogShow2: {
      get () {
        if (this.getCrudState === 'addRole') {
          // console.log('xxxxxxxxxx')
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

<style lang="less">
.UserAddSlot {
  width: 100%;
  text-align: center;
  height: 60px;
  line-height: 60px;
  margin-top: -55px;
  font-size: 1.5em;
}
</style>
