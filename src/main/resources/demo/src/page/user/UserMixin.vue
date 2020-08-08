
<script>
export default {
  data () {
    return {
      user: {
        // 分页查询条件
        loginName: '',
        userName: '',
        status: '',
        startTime: '',
        stopTime: '',
        pageSize: 20,
        pageNum: 1
      },
      countUser: {
        loginName: '',
        userName: '',
        status: '',
        startTime: '',
        stopTime: '',
        pageSize: 20,
        pageNum: 1,
        hidden: 0
      },
      resetUser: {
        loginName: '',
        userName: '',
        status: '',
        startTime: '',
        stopTime: '',
        pageSize: 20,
        pageNum: 1
      },
      disableUser: {
        userId: '',
        userStatus: ''
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
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.seTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      value2: ''
    }
  }

}
</script>
