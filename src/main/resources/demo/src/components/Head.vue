<template>
  <div id="head-head">
    <el-header>
      <el-dropdown>
        <i class="el-icon-setting"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-button  @click="resetQueryUser" round v-text="getUserName" id="head-btn"></el-button>
    </el-header>
  </div>
</template>

<style lang="less">
#head-head{
  width: 100%;
  height: 40px;
  line-height: 40px;
  text-align: center;
  // background-color: rebeccapurple;
  border: 1px solid #eee;
  border-radius: 4px;
}

</style>

<script>

export default {
  computed: {
    getUserName () {
      const user = JSON.parse(localStorage.getItem('user'))
      return user.userName
    }
  },
  methods: {
    resetQueryUser () {
      this.$store.commit('setResetState')
    }
  }
}
</script>

<style lang="less">
#head-head{
  i {
    width: 50px;
  }
  button {
    width: calc(100% - 50px);
  }

}
</style>
