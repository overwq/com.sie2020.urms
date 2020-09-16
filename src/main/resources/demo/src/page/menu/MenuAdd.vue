<template>
   <div id="MenuAdd">
    <el-dialog :visible.sync="dialogShow" center width="700px" title="添加菜单">
      <div class="UserAddSlot"><slot></slot></div>
      <el-form :model="addMenu" :rules="rules" ref="addMenu" label-width="200px">
        <el-form-item label="菜单类型" prop="menuType">
          <el-radio-group v-model="addMenu.menuType">
            <el-radio label="目录">目录</el-radio>
            <el-radio label="菜单">菜单</el-radio>
            <el-radio label="功能">功能</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上级菜单" prop="menuParentName">
          <!-- <el-input v-model="addMenu.menuParentName"></el-input> -->
          <el-select v-model="addMenu.menuParentId">
              <el-option v-for="item in options2" :key="item.MENU_ID" :label="item.MENU_NAME" :value="item.MENU_ID"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜单名称" prop="menuName">
          <el-input  v-model="addMenu.menuName"></el-input>
        </el-form-item>
        <el-form-item label="菜单标识" prop="menuOpcode">
          <el-input  v-model="addMenu.menuOpcode"></el-input>
        </el-form-item>
        <el-form-item label="菜单地址" prop="menuUrl">
          <el-input  v-model="addMenu.menuUrl"></el-input>
        </el-form-item>
        <el-form-item label="排序号" prop="menuQueueNumber">
          <el-input  v-model="addMenu.menuQueueNumber"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="menuStatus">
          <el-radio-group v-model="addMenu.menuStatus">
            <el-radio :label="1">有效</el-radio>
            <el-radio :label="0">无效</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="menuRemarks">
          <el-input type="textarea" v-model="addMenu.menuRemarks"></el-input>
        </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="submitForm('addMenu')">确认修改</el-button>
          <el-button @click="resetForm('addMenu')">重置</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      </span>
   </el-dialog>
  </div>
</template>

<script>
import MenuMixin from './MenuMixin'
import { addMenu, getOptionValues } from '../../api/menu'
import { mapMutations, mapGetters } from 'vuex'
export default {
  mixins: [MenuMixin],
  data () {
    return {
      addMenu: {
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
  created: function () {
    getOptionValues().then(res => {
      this.options2 = res.data.parentName
      // console.log(JSON.stringify(this.options2))
    })
  },
  methods: {
    ...mapMutations(['setRefreshTag', 'setCrudState']),
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addMenu(this.addMenu).then(res => {
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
    }
  },
  computed: {
    ...mapGetters(['getCrudState']),
    dialogShow: {
      get () {
        if (this.getCrudState === 'addMenu') {
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
    dialogShow3 (val) {
      // console.log(val)
    }
  }

}
</script>

<style lang="less">
.UserAddSlot {
  width: 100%;
  text-align: center;
  height: 60px;
  line-height: 60px;
  margin-top: -55px;
  font-size: 1.5em;
}
</style>
