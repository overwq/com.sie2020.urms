<template>
 <div id="MenuHead">
    <el-link :underline="false">菜单名称:</el-link>
    <el-input v-model="menu.menuName" class="input" size="mini"></el-input>
    <el-link :underline="false">状态</el-link>
    <el-select v-model="menu.menuStatus"  @change="sendQueryMenus" placeholder="全部"  style="min-width:100px;max-width:200;width:8%" size="mini" >
      <el-option v-for="item in options1"  :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-link :underline="false">类型</el-link>
    <el-select v-model="menu.menuType" @change="sendQueryMenus" placeholder="全部"  style="min-width:100px;max-width:200;width:8%" size="mini" >
      <el-option v-for="item in options2" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-button type="info" icon="el-icon-search"  class="btn-wth" @click="sendQueryMenus">查询</el-button>
    <el-button type="primary" icon="el-icon-edit"  class="btn-wth" @click="setMenuCrudState('addMenu')">新增</el-button>
    <el-button type="danger" icon="el-icon-delete"  class="btn-wth">删除</el-button>
  </div>
</template>

<script>
import MenuMixin from './MenuMixin'
import { mapMutations, mapGetters } from 'vuex'
import { getMenus, getCount } from '../../api/menu'
export default {
  name: 'MenuHead',
  mixins: [MenuMixin],
  data () {
    return {
      // menu: {}
    }
  },
  created: function () {
    document.onkeypress = (e) => {
      if (e.key === 'Enter') this.sendQueryMenus()
    }
  },
  mounted: function () {
    this.sendQueryMenus()
  },
  methods: {
    ...mapMutations(['setResetState', 'setRefreshTag', 'setCrudState', 'setCheckBoxShowState', 'setTotalCount']),
    setMenuCrudState (val) {
      this.setCrudState(val)
    },
    sendQueryMenus () {
      getMenus(this.menu).then(res => {
        this.getMenuTotalCount()
        this.$emit('query-menus-coming', res.data.data)
        if (res.data.state !== 402) {
          this.setRefreshTag(false)
        }
      })
    },
    getMenuTotalCount () {
      const tempMenu = JSON.parse(JSON.stringify(this.menu))
      getCount(tempMenu).then(res => {
        // console.log(res.data)
        this.setTotalCount(res.data)
      })
    }
  },
  computed: {
    ...mapGetters(['getResetState', 'getCrudState', 'getPageNum', 'getPageSize', 'getRefreshTag']),
    resetState () { return this.getResetState },
    crudState () { return this.getCrudState },
    pageNum () { return this.getPageNum },
    pageSize () { return this.getPageSize },
    refreshTag () { return this.getRefreshTag }
    // menuStateValue () { return this.menu.menuStatus },
    // menuTypeValue () { return this.menu.menuType }
  },
  watch: {
    resetState (val) {
      this.menu = JSON.parse(JSON.stringify(this.resetMenu))
      this.sendQueryMenus()
    },
    pageNum (val) {
      this.menu.pageNum = val
      this.setRefreshTag(true)
    },
    pageSize (val) {
      this.menu.pageSize = val
      this.setRefreshTag(true)
    },
    refreshTag (val) {
      // console.log(val)
      if (val) {
        this.sendQueryMenus()
      }
    }
    // menuStateValue (val) {
    //   this.setRefreshTag(true)
    // },
    // menuTypeValue (val) {
    //   this.setRefreshTag(true)
    // }
  }
}
</script>

<style lang="less">
@import url('../../style/TableStyle.less');
#MenuHead{
  width: 100%;
  height: 60px;
  line-height: 60px;
}

.inline-block{
  display: inline-block;
}

</style>
