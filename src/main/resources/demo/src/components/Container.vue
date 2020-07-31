<template>
  <el-container id="container-root">
    <taside></taside>
    <el-container id="container-son">
      <tthead></tthead>

      <el-main id="main">
        <router-view></router-view>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import Aside from './Aside'
import Head from './Head'

export default {
  name: 'Container',
  components: {
    Taside: Aside, Tthead: Head
  }
}
</script>

<style lang="less">
#container-root{
  width: 100%;
  min-width: 1300px;
  height: 100%;
  min-height: 600px;
  max-height: 750px;
  border: 1px solid #eee;
  border-radius: 4px;
  position: absolute;
}
#container-son{
  width: 98%;
  height: 100%;
  min-height: 600px;
  max-height: 750px;
  border: 1px solid #eee;
  // background-color: red;
  border-radius: 4px;
  position: relative;
}
#main{
  position: absolute;
  // background-color: yellow;
  top: 40px;
  padding: 0px;
  margin: 0;
  width: 100%;
  height: calc(100% - 40px);
  border: 1px solid #eee;
  overflow-y: hidden;
}

</style>
