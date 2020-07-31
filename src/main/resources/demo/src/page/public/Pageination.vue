<template>
  <div class="pageination">
    <div class="pageination-temp">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[20, 15, 10, 5]"
        :page-size="100"
        layout="total,sizes, prev, pager, next, jumper"
        :total="getTotalCount">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapGetters } from 'vuex'
export default {
  data () {
    return {
      data_totalCount: 20,
      currentPage4: 1
    }
  },
  props: {
    noUsingVuex: true
  },
  methods: {
    ...mapMutations(['setPageSize', 'setPageNum']),
    handleSizeChange (val) {
      if (!this.noUsingVuex) {
        this.setPageSize(val)
      } else {
        // console.log("PageInation-size:"+val)
        this.$emit('dialogPageSize', val)
      }
    },
    handleCurrentChange (val) {
      if (!this.noUsingVuex) {
        this.setPageNum(val)
      } else {
        // console.log("PageInation-Num:"+val)
        this.$emit('dialogPageNum', val)
      }
    }
  },
  computed: {
    ...mapGetters(['getTotalCount']),
    c_totalCount: {
      get () {
        return this.getTotalCount
      },
      set (val) {
        this.data_totalCount = this.getTotalCount
      }
    }
  }
}
</script>
<style lang="less">
.pageination{
  width: 100%;
  height: 40px;
  padding-top: 5px;
  position: fixed;
  // bottom: 130px;
  // // top: 835px;
  margin-left: -202px;
  text-align: center;
  border: 1px solid #eee;
  border-radius: 10px;
  z-index: 100;
}

</style>
