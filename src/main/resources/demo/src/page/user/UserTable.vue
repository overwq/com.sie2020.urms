<template>
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
      <el-radio label="男"></el-radio>
      <el-radio label="女"></el-radio>
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
      <el-radio label="有效"></el-radio>
      <el-radio label="无效"></el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="备注" prop="userRemarks">
    <el-input type="textarea" v-model="user.userRemarks"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('user')">立即创建</el-button>
    <el-button @click="resetForm('user')">重置</el-button>
  </el-form-item>
</el-form>
</template>

<script>
export default {
  data () {
    var checkUserName = (rule, value, callbak) => {
      if (!value) {
        return callbak(new Error('用户名不能为空'))
      }
      if (value.length > 20 || value.length < 6) {
        return callbak(new Error('6-20位字母或数字'))
      }
      callbak()
    }
    return {
      user: {
        userLoginName: '',
        userPassword: '',
        userName: '',
        userPhone: '',
        userEmail: '',
        userSex: '男',
        userStatus: '有效',
        userRemarks: '',
        checkUserPassword: ''
      },
      rules: {
        userLoginName: [
          { validate: checkUserName, trigger: 'blur', required: true, message: '用户名不能为空' }
        ],
        userPassword: [
          { required: true, message: '请输入密码', trigger: 'change' },
          { min: 6, max: 20, message: '长度为 6 到 20 个字符', trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名', trigger: 'change' }
        ],
        checkUserPassword: [
          { required: true, message: '请确认密码', trigger: 'change' }
        ],
        userRemarks: [
          { max: 500, trigger: 'blur' }
        ],
        userSex: [
          { required: true }
        ],
        userStatus: [
          { required: true }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.user.userSex === '男') {
            this.user.userSex = 1
          } else {
            this.user.userSex = 0
          }
          if (this.user.userStatus === 0) {
            this.user.userStatus = 1
          } else {
            this.user.userStatus = 0
          }
          this.$store.commit('setTempUser', this.user)
          // alert('submit!')
          console.log('come from UserTable : 添加用户请求已经提交')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
