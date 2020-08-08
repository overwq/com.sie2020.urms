<template>
  <div id="Aside">
     <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1', '3']">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>系统管理</template>
        <el-menu-item-group>
          <el-menu-item><router-link to="/container/home" tag="li">首页</router-link></el-menu-item>
          <el-menu-item><router-link to="/container/user" tag="li">用户管理</router-link></el-menu-item>
          <el-menu-item><router-link to="/container/role" tag="li">角色管理</router-link></el-menu-item>
          <el-menu-item><router-link to="/container/Menu" tag="li">菜单管理</router-link></el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
  </div>
</template>

<script>
export default {
  name: 'Aside'
}
</script>
<style lang="less">
.el-aside {
    color: #333;
}

</style>
