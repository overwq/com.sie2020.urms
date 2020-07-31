<template>
  <div id="LoginPage">
    <el-form :model="user" ref="user" label-width="100px" class="demo-dynamic">
      <el-button type="info" plain class="info-btn">用户权限管理系统</el-button>
      <!-- <div></div> -->
      <el-form-item
        prop="userLoginName"
        label="用户名"
        :rules="[
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { type: '', message: '请输入正确的用户名', trigger: ['blur', 'change'] }
        ]">
        <el-input v-model="user.userLoginName"></el-input>
      </el-form-item>
      <el-form-item
        prop="userPassword"
        label="密码"
        :rules="[
          { required: true, message: '密码', trigger: 'blur' },
          { type: '', message: '密码不正确', trigger: ['blur', 'change'] }
        ]">
        <el-input type="password" v-model="user.userPassword"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('user')" >提交</el-button>
        <el-button @click="resetForm('user')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { login } from '../api/user'
import router from '../router/index'
export default {
  data () {
    return {
      user: {
        userPassword: '',
        userLoginName: ''
      }
    }
  },
  created: function () {
    document.onkeypress = (e) => {
      if (e.key === 'Enter') this.submitForm('user')
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.login()
        } else {
          alert('user name or password is null or not correct!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    async login () {
      const res = await login(this.user)
      let userMenus = res.data.box.menu
      let permissions = []
      if(userMenus && userMenus.length > 0){
        userMenus.forEach(t => {
          permissions = [...permissions,t.menuUrl]
        })
      }
      sessionStorage.setItem('user', JSON.stringify(res.data.box.user))
      // sessionStorage.setItem('role', JSON.stringify(res.data.box.role))
      sessionStorage.setItem('menu', JSON.stringify(res.data.box.menu))
      sessionStorage.setItem('permissions', JSON.stringify(permissions))
      setTimeout(()=>{
        sessionStorage.removeItem('user')
        sessionStorage.removeItem('menu')
        sessionStorage.removeItem('permissions')
        sessionStorage.clear()
        this.Message.error("session 已过期，请重新登陆！")
        router.push('/login')
      },1000*60*30)

      // await this.$store.dispatch('setMenuTree')
      await router.push('/container/home')
    }
  },


}
</script>
<style scoped >
#LoginPage{
  width: 500px;
  height: 300px;
  border: 1px solid #eee;
  margin: 100px auto;
  /* text-align: center; */
}
.info-btn{
  width: 100%;
  height: 60px;
  font-size: 1.5em;
  letter-spacing: 8px;
  margin-bottom: 20px;
}
</style>
