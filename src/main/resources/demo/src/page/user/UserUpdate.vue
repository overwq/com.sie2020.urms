<template>
  <div id="UserUpdate">
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
        <!-- <el-form-item label="确认密码" prop="checkUserPassword">
          <el-input type="password" v-model="user.checkUserPassword"></el-input>
        </el-form-item> -->
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
            <el-radio :label="1">有效</el-radio>
            <el-radio :label="0">无效</el-radio>
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

// import { mapState } from 'vuex'
import { mapMutations } from 'vuex'
import UserAddUpdateMixin from './UserAddUpdateMixin'
import { updateUser, getUserById } from '../../api/user'
export default {
  mixins: [UserAddUpdateMixin],
  props: {
    userId: {
      type: Number,
      default: 0
    }
  },
  methods: {
    ...mapMutations(['setRefreshTag']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$store.commit('setCrudState', '')
          updateUser(JSON.stringify(this.user)).then(res => {
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
        if (this.$store.getters.getCrudState === 'updateUser') {
          return true
        }
        return false
      },
      set () {
        this.$store.commit('setCrudState', '')
      }
    }
  },
  mounted: function () {

  },

  watch: {
    userId (val) {
      getUserById(val).then(res => {
        this.user = res.data
      })
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
