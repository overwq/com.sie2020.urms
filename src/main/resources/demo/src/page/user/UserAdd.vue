<template>
   <div id="UserAdd">
    <el-dialog :visible.sync="dialogShow" center width="700px">
      <div class="UserAddSlot"><slot></slot></div>
      <el-form :model="user" :rules="rules" ref="user" label-width="200px">
        <el-form-item label="真实姓名" prop="userName">
          <el-input v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="userLoginName">
          <el-input v-model="user.userLoginName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPassword">
          <el-input type="password" v-model="user.userPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkUserPassword">
          <el-input type="password" v-model="user.checkUserPassword"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="userSex">
          <el-radio-group v-model="user.userSex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="userPhone">
          <el-input v-model="user.userPhone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="userEmail">
          <el-input v-model="user.userEmail"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="userStatus">
          <el-radio-group v-model="user.userStatus">
            <el-radio :label="0">有效</el-radio>
            <el-radio :label="1">无效</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="userRemarks">
          <el-input type="textarea" v-model="user.userRemarks"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="submitForm('user')">确认修改</el-button>
          <el-button @click="resetForm('user')">重置</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="userAddDialogVisiable = false">取消</el-button> -->
      </span>
   </el-dialog>
    <!-- <slot name="UserAddDialogSlot">{{userAddDialogVisiable}}</slot> -->
  </div>
</template>

<script>
import UserAddUpdateMixin from './UserAddUpdateMixin'
import { addUser } from '../../api/user'
import { mapMutations, mapGetters } from 'vuex'
export default {
  mixins: [UserAddUpdateMixin],
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // this.user.userSex === '男' ? this.user.userSex = 1 : this.user.userSex = 0
          // this.user.userStatus === '有效' ? this.user.userStatus = 0 : this.user.userStatus = 1
          // this.$emit('addUser', this.user)
          // this.$store.commit('setCrudState', '')
          this.setCrudState('')
          addUser(this.user)
            .then(res => {
              this.setRefreshTag(true)
            })

          // this.$store.commit('setTempUser', this.user)

          console.log('come from UserTable : 添加用户请求已经提交')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  },
  computed: {
    ...mapGetters(['getCrudState']),
    dialogShow: {
      get () {
        if (this.getCrudState === 'addUser') {
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
