<script>
export default {
  // var checkUserName = (rule, value, callbak) => {
  //   if (!value) {
  //     return callbak(new Error('用户名不能为空'))
  //   }
  //   if (value.length > 20 || value.length < 6) {
  //     return callbak(new Error('6-20位字母或数字'))
  //   }
  //   callbak()
  // }
  data () {
    return {
      menu: { // 分页查询条件
        menuId: 0,
        menuName: '',
        menuStatus: '',
        menuType: '',
        pageSize: 20,
        pageNum: 1
      },
      resetMenu: {
        menuId: 0,
        menuName: '',
        menuStatus: '',
        menuType: '',
        pageSize: 15,
        pageNum: 1
      },

      addMenu: {
        menuName: '',
        menuStatus: 1,
        menuType: '',
        pageSize: 15,
        pageNum: 1
      },
      options1: [
        {
          value: '',
          label: '全部'
        },
        {
          value: '1',
          label: '有效'
        },
        {
          value: '0',
          label: '无效'
        },
        {
          value: '2',
          label: '删除'
        }
      ],
      options2: [{
        value: '',
        label: '全部'
      }, {
        value: '目录',
        label: '目录'
      }, {
        value: '菜单',
        label: '菜单'
      }, {
        value: '功能',
        label: '功能'
      }],
      options3: [{
        value: '系统管理',
        label: '系统管理'
      }, {
        value: '用户管理',
        label: '用户管理'
      }, {
        value: '角色管理',
        label: '角色管理'
      }, {
        value: '菜单管理',
        label: '菜单管理'
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
