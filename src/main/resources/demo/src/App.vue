<template>
  <div id="app">
  <div id="head">
    <router-link to="/login"><el-button type="primary" class="btn-wth">Login</el-button></router-link><span class="span"></span>
    <router-link to="/container/home"><el-button type="success" class="btn-wth">System</el-button></router-link><span class="span"></span>
    <router-link to="/login"><el-button type="danger" class="btn-wth" @click="logout">Logout</el-button></router-link><span class="span"></span>
    <el-button type="info" class="btn-wth" @click="getOpen = true">Dark Modal</el-button>
  </div>
  <div id="view">
    <router-view></router-view>
  </div>
</div>
</template>

<script>
import { Message } from 'element-ui'
export default {
  data () {
    return {
      open: false
    }
  },
  created:function(){
    // document.onmouseenter = (d) => {
    //   // console.log(d)
    //   console.log(1)
    //
    // }
    // document.onmousemove = (l) => {
    //
    //
    //   console.log(l)
    // }

  },
  computed: {
    getOpen: {
      get () {
        return this.open
      },
      set (val) {
        this.open = !this.open
        Message.closeAll()
        if (this.getOpen) {
          document.getElementsByTagName('body')[0].className = 'darkModal'
          Message.success("dark modal 已开启")
        } else {
          document.body.removeAttribute('class','darkModal')
          Message.success("dark modal 已关闭")
        }
      }
    }
  },
  methods: {
    logout () {
      sessionStorage.removeItem('user')
      sessionStorage.removeItem('role')
      sessionStorage.removeItem('permissions')
      sessionStorage.clear()
      Message.closeAll()
      Message.warning('登陆信息已清除 请重新登陆 ！')
    }
  },

}
</script>

<style lang="less">
@import "style/TableStyle.less";
#view{
  width: 100%;
  height: 700px;
  margin: 0 auto;
  // background-color: red;
}
#head{
  width: 100%;
  height: 80px;
  text-align: center;
  line-height: 80px;
  margin-top: 50px;
}
.span{
  display: inline-block;
  min-width: 30px;
  width: 4%;
  max-width: 60px;
  height: 10px;
}
.darkModal{
  // background-color: lightslategrey;
  // background-color: rgb(224, 169, 66);
  // background-color: #EED7C5;
  background-color: #a6a9ad;
  opacity: 0.85;
}
body{
  position: relative;
}
</style>
