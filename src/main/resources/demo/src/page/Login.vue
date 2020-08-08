<template>
  <div id="LoginPage">
    <el-form :model="user" ref="user" label-width="100px" class="demo-dynamic">
      <el-button type="info" plain class="info-btn">用户权限管理系统</el-button>
      <el-form-item prop="userLoginName" label="用户名" :rules="rules.userLoginName">
        <el-input v-model="user.userLoginName"></el-input>
      </el-form-item>
      <el-form-item prop="userPassword" label="密码" :rules="rules.userPassword">
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
import { Message } from 'element-ui'
export default {
  data () {
    return {
      user: {
        userPassword: '',
        userLoginName: ''
      },
      rules: {
        userLoginName: [
          { max: 50, trigger: 'blur', required: true, message: '用户名不能为空' }
        ],
        userPassword: [
          { required: true, message: '请输入密码', trigger: 'change' },
          { max: 20, message: '长度为 6 到 20 个字符', trigger: 'blur' }
        ]
      }

    }
  },
  created: function () {
    document.onkeypress = (e) => {
      if (e.key === 'Enter') this.submitForm('user')
    }
  },
  mounted: function () {
    const user = localStorage.getItem('user')
    if (user) {
      Message.success('已获取登陆信息 ， 3秒后自动跳转 ！')
      setTimeout(() => {
        router.push('/container/home')
      }, 3000)
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.login()
        } else {
          Message.error('用户名或密码不正确,请重新输入!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    login () {
      login(this.user).then(res => {
        if (res.data && res.data.box) {
          const userMenus = res.data.box.menu
          let permissions = []
          if (userMenus && userMenus.length > 0) {
            userMenus.forEach(t => {
              permissions = [...permissions, t.menuUrl]
            })
          }
          localStorage.setItem('user', JSON.stringify(res.data.box.user))
          localStorage.setItem('menu', JSON.stringify(res.data.box.menu))
          localStorage.setItem('permissions', JSON.stringify(permissions))
          // localStorage.setItem('decode', encodeURI(JSON.stringify(res.data.box.menu)))
          router.push('/container/home')
        }
      })
    }
  },
  computed: {
    decodeUserPassword: {
      get () {
        return this.userPassword
      },
      set (val) {
        this.userPassword = window.btoa(val)
        console.log(this.userPassword)
      }
    }
  }

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
