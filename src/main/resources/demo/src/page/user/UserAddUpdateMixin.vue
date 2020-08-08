<script>
export default {
  data () {
    // var checkUserName = (rule, value, callbak) => {
    //   if (!value) {
    //     return callbak(new Error('用户名不能为空'))
    //   }
    //   if (value.length > 20 || value.length < 6) {
    //     return callbak(new Error('6-20位字母或数字'))
    //   }
    //   callbak()
    // }
    return {
      user: {
        userId: '',
        userLoginName: '',
        userPassword: '',
        userName: '',
        userPhone: '',
        userEmail: '',
        userSex: 1,
        userStatus: 0,
        userRemarks: '',
        checkUserPassword: ''
      },
      rules: {
        // userLoginName: [
        //   { validate: checkUserName, trigger: 'blur', required: true, message: '用户名不能为空' }
        // ],
        // userPassword: [
        //   { required: true, message: '请输入密码', trigger: 'change' },
        //   { min: 6, max: 20, message: '长度为 6 到 20 个字符', trigger: 'blur' }
        // ],
        // userName: [
        //   { required: true, message: '请输入用户名', trigger: 'change' }
        // ],
        // checkUserPassword: [
        //   { required: true, message: '请确认密码', trigger: 'change' }
        // ],
        // userRemarks: [
        //   { max: 500, trigger: 'blur' }
        // ],
        // userSex: [
        //   { required: true }
        // ],
        // userStatus: [
        //   { required: true }
        // ]
      }
    }
  },
  methods: {
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
