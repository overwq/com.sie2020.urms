<template>
  <div id="MenuAdd">
    <el-dialog :visible.sync="dialogShow" center width="700px" title="修改菜单">
      <div class="UserAddSlot"><slot></slot></div>
      <el-form :model="updateMenu" :rules="rules" ref="updateMenu" label-width="200px">
        <el-form-item label="菜单类型" prop="menuType">
          <el-radio-group v-model="updateMenu.menuType">
            <el-radio label="目录">目录</el-radio>
            <el-radio label="菜单">菜单</el-radio>
            <el-radio label="功能">功能</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上级菜单" prop="menuParentId">
          <!-- <el-input v-model="updateMenu.menuParentName"></el-input> -->
          <el-select v-model="updateMenu.menuParentName">
              <el-option v-for="item in options3" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜单名称" prop="menuName">
          <el-input  v-model="updateMenu.menuName"></el-input>
        </el-form-item>
        <el-form-item label="菜单标识" prop="menuOpcode">
          <el-input  v-model="updateMenu.menuOpcode"></el-input>
        </el-form-item>
        <el-form-item label="菜单地址" prop="menuUrl">
          <el-input  v-model="updateMenu.menuUrl"></el-input>
        </el-form-item>
        <el-form-item label="排序号" prop="menuQueueNumber">
          <el-input  v-model="updateMenu.menuQueueNumber"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="menuStatus">
          <el-radio-group v-model="updateMenu.menuStatus">
            <el-radio :label="1">有效</el-radio>
            <el-radio :label="0">无效</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="menuRemarks">
          <el-input type="textarea" v-model="updateMenu.menuRemarks"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="submitForm('updateMenu')">确认修改</el-button>
          <el-button @click="resetForm('updateMenu')">重置</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      </span>
   </el-dialog>
  </div>
</template>

<script>

import { mapMutations, mapGetters } from 'vuex'
import MenuMixin from './MenuMixin'
import { updateMenu, getMenuById } from '../../api/menu'
export default {
  mixins: [MenuMixin],
  data () {
    return {
      updateMenu: {
        menuId: '',
        menuName: '',
        menuStatus: '',
        menuType: '',
        menuParentId: '',
        menuQueueNumber: '',
        menuUrl: '',
        menuOpcode: '',
        menuRemarks: '',
        menuParentName: ''
      }
    }
  },
  props: {
    menuId: {
      type: Number,
      default: 0
    }
  },
  // mounted: function () {
  //   this.getUpdateMenuById()
  // },
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateMenu(JSON.stringify(this.updateMenu)).then(res => {
            this.setCrudState('')
            if (res.data.state !== 402) {
              this.setRefreshTag(true)
              this.$store.dispatch('setMenuTree')
            }
          })
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    getUpdateMenuById () {
      getMenuById(this.menuId).then(res => {
        this.updateMenu = res.data
      })
    }

  },
  computed: {
    ...mapGetters(['getCrudState']),
    dialogShow: {
      get () {
        if (this.getCrudState === 'updateMenu') {
          return true
        }
        return false
      },
      set () {
        this.setCrudState('')
      }
    }
  },

  watch: {
    menuId (val) {
      this.getUpdateMenuById()
      // getMenuById(val).then(res => {
      //   this.updateMenu = res.data
      // })
    }
  }

}
</script>

<style lang="less">
#UserAddSlot {
  width: 100%;
  text-align: center;
  height: 60px;
  line-height: 60px;
  margin-top: -55px;
  font-size: 1.5em;
}
</style>
