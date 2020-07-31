<script>
export default {
  data () {
    return {
      role: { // 分页查询条件
        roleId: '',
        roleName: '',
        roleStatus: '',
        roleCode: '',
        pageSize: 15,
        pageNum: 1
      },
      countRole: {
        roleId: '',
        roleName: '',
        roleStatus: '',
        roleCode: '',
        pageSize: 15,
        pageNum: 1
      },

      resetRole: {
        roleName: '',
        roleStatus: '',
        roleCode: '',
        pageSize: 15,
        pageNum: 1
      },
      options1: [{
        value: '',
        label: '全部'
      }, {
        value: '1',
        label: '有效'
      }, {
        value: '0',
        label: '无效'
      }, {
        value: '2',
        label: '删除'
      }],
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
  }
}
</script>
